package com.esri.arcgisruntime.findplaces;

import android.Manifest;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.esri.arcgisruntime.concurrent.ListenableFuture;
import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.loadable.LoadStatus;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.view.Callout;
import com.esri.arcgisruntime.mapping.view.DefaultMapViewOnTouchListener;
import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;
import com.esri.arcgisruntime.mapping.view.IdentifyGraphicsOverlayResult;
import com.esri.arcgisruntime.mapping.view.LocationDisplay;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.esri.arcgisruntime.mapping.view.ViewpointChangedEvent;
import com.esri.arcgisruntime.mapping.view.ViewpointChangedListener;
import com.esri.arcgisruntime.symbology.SimpleLineSymbol;
import com.esri.arcgisruntime.symbology.SimpleMarkerSymbol;
import com.esri.arcgisruntime.symbology.TextSymbol;
import com.esri.arcgisruntime.tasks.geocode.GeocodeParameters;
import com.esri.arcgisruntime.tasks.geocode.GeocodeResult;
import com.esri.arcgisruntime.tasks.geocode.LocatorTask;
import com.esri.arcgisruntime.util.ListenableList;
import com.example.hospitoolsv2.R;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.esri.arcgisruntime.security.AuthenticationManager.CredentialCache.clear;

public class MainActivity extends AppCompatActivity {

    private MapView mMapView;
    private LocationDisplay mLocationDisplay;
    private GraphicsOverlay graphicsOverlay;
    private LocatorTask locator = new LocatorTask("http://geocode.arcgis.com/arcgis/rest/services/World/GeocodeServer");
    private Spinner spinner;
    private SearchView mSearchView = null;
    private GraphicsOverlay mGraphicsOverlay;
    private LocatorTask mLocatorTask = null;
    private GeocodeParameters mGeocodeParameters = null;


    private void setupMap() {
        if (mMapView != null) {
            Basemap.Type basemapType = Basemap.Type.STREETS_VECTOR;
            double latitude = 29.951;
            double longitude = -90.0715;
            int levelOfDetail = 10;
            final ArcGISMap map = new ArcGISMap(basemapType, latitude, longitude, levelOfDetail);
            mMapView.setMap(map);

            mMapView.addViewpointChangedListener(new ViewpointChangedListener() {
                @Override
                public void viewpointChanged(ViewpointChangedEvent viewpointChangedEvent) {
                    if (graphicsOverlay == null) {
                        // Create a graphics overlay that will show the places found from a search.
                        graphicsOverlay = new GraphicsOverlay();
                        mMapView.getGraphicsOverlays().add(graphicsOverlay);
                        setupSpinner();
                        setupPlaceTouchListener();
                        setupNavigationChangedListener();
                        mMapView.removeViewpointChangedListener(this);
                    }
                }
            });
        }
    }
    private void setupLocationDisplay() {
        mLocationDisplay = mMapView.getLocationDisplay();
        mLocationDisplay.addDataSourceStatusChangedListener(dataSourceStatusChangedEvent -> {
            if (dataSourceStatusChangedEvent.isStarted() || dataSourceStatusChangedEvent.getError() == null) {
                return;
            }

            int requestPermissionsCode = 2;
            String[] requestPermissions = new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

            if (!(ContextCompat.checkSelfPermission(MainActivity.this, requestPermissions[0]) == PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(MainActivity.this, requestPermissions[1]) == PackageManager.PERMISSION_GRANTED)) {
                ActivityCompat.requestPermissions(MainActivity.this, requestPermissions, requestPermissionsCode);
            } else {
                String message = String.format("Error in DataSourceStatusChangedListener: %s",
                        dataSourceStatusChangedEvent.getSource().getLocationDataSource().getError().getMessage());
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });
        mLocationDisplay.setAutoPanMode(LocationDisplay.AutoPanMode.COMPASS_NAVIGATION);
        mLocationDisplay.startAsync();

    }


    private void setupSpinner() {
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                findPlaces(adapterView.getItemAtPosition(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        findPlaces(spinner.getSelectedItem().toString());
    }

    private void setupPlaceTouchListener() {
        mMapView.setOnTouchListener(new DefaultMapViewOnTouchListener(this, mMapView) {
            @Override
            public boolean onSingleTapConfirmed(MotionEvent motionEvent) {

                mMapView.getCallout().dismiss();

                final android.graphics.Point screenPoint = new android.graphics.Point(Math.round(motionEvent.getX()), Math.round(motionEvent.getY()));

                final ListenableFuture<IdentifyGraphicsOverlayResult> identifyGraphic = mMapView.identifyGraphicsOverlayAsync(graphicsOverlay, screenPoint, 10.0, false, 2);

                identifyGraphic.addDoneListener(() -> {
                    try {
                        IdentifyGraphicsOverlayResult graphicsResult = identifyGraphic.get();
                        List<Graphic> graphicList = graphicsResult.getGraphics();

                        if (!graphicList.isEmpty()){
                            showCalloutAtLocation(graphicList.get(0), mMapView.screenToLocation(screenPoint));
                        }
                    } catch (InterruptedException | ExecutionException exception) {
                        exception.printStackTrace();
                    }
                });
                return super.onSingleTapConfirmed(motionEvent);
            }
        });
    }

    private void setupNavigationChangedListener() {
        mMapView.addNavigationChangedListener(navigationChangedEvent -> {
            if (!navigationChangedEvent.isNavigating()) {
                mMapView.getCallout().dismiss();
                findPlaces(spinner.getSelectedItem().toString());
            }
        });
    }

    private void showCalloutAtLocation(Graphic graphic, Point mapPoint) {
        Callout callout = mMapView.getCallout();
        TextView calloutContent = new TextView(getApplicationContext());

        callout.setLocation(graphic.computeCalloutLocation(mapPoint, mMapView));
        calloutContent.setTextColor(Color.BLACK);
        calloutContent.setText(Html.fromHtml("<b>" + graphic.getAttributes().get("PlaceName").toString() + "</b><br>" + graphic.getAttributes().get("Place_addr").toString()));
        callout.setContent(calloutContent);
        callout.show();
    }

    private void findPlaces(String placeCategory) {
        GeocodeParameters parameters = new GeocodeParameters();
        Point searchPoint;

        if (mMapView.getVisibleArea() != null) {
            searchPoint = mMapView.getVisibleArea().getExtent().getCenter();
            if (searchPoint == null) {
                return;
            }
        } else {
            return;
        }
        parameters.setPreferredSearchLocation(searchPoint);

        parameters.setMaxResults(25);

        List<String> outputAttributes = parameters.getResultAttributeNames();
        outputAttributes.add("Place_addr");
        outputAttributes.add("PlaceName");

        final ListenableFuture<List<GeocodeResult>> results = locator.geocodeAsync(placeCategory, parameters);
        results.addDoneListener(() -> {
            try {
                ListenableList<Graphic> graphics = graphicsOverlay.getGraphics();
                graphics.clear();
                List<GeocodeResult> places = results.get();
                for (GeocodeResult result : places) {

                    SimpleMarkerSymbol placeSymbol = new SimpleMarkerSymbol(SimpleMarkerSymbol.Style.CIRCLE, Color.GREEN, 10);
                    placeSymbol.setOutline(new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, Color.WHITE, 2));
                    Graphic graphic = new Graphic(result.getDisplayLocation(), placeSymbol);
                    java.util.Map<String, Object> attributes = result.getAttributes();

                    for (String key : attributes.keySet()) {
                        String value = attributes.get(key).toString();
                        graphic.getAttributes().put(key, value);
                    }
                    graphics.add(graphic);
                }
            } catch (InterruptedException | ExecutionException exception) {
                exception.printStackTrace();
            }
        });
    }
    private void queryLocator(final String query) {
        if (query != null && query.length() > 0) {
            mLocatorTask.cancelLoad();
            final ListenableFuture<List<GeocodeResult>> geocodeFuture = mLocatorTask.geocodeAsync(query, mGeocodeParameters);
            geocodeFuture.addDoneListener(new Runnable() {
                @Override
                public void run() {
                    try {
                        List<GeocodeResult> geocodeResults = geocodeFuture.get();
                        if (geocodeResults.size() > 0) {
                            displaySearchResult(geocodeResults.get(0));
                        } else {
                            Toast.makeText(getApplicationContext(), getString(R.string.nothing_found) + " " + query, Toast.LENGTH_LONG).show();
                        }
                    } catch (InterruptedException | ExecutionException e) {
                        // ... determine how you want to handle an error
                    }
                    geocodeFuture.removeDoneListener(this); // Done searching, remove the listener.
                }
            });
        }
    }
    private void displaySearchResult(GeocodeResult geocodedLocation) {
        String displayLabel = geocodedLocation.getLabel();
        TextSymbol textLabel = new TextSymbol(18, displayLabel, Color.rgb(192, 32, 32), TextSymbol.HorizontalAlignment.CENTER, TextSymbol.VerticalAlignment.BOTTOM);
        Graphic textGraphic = new Graphic(geocodedLocation.getDisplayLocation(), textLabel);
        Graphic mapMarker = new Graphic(geocodedLocation.getDisplayLocation(), geocodedLocation.getAttributes(),
                new SimpleMarkerSymbol(SimpleMarkerSymbol.Style.SQUARE, Color.rgb(255, 0, 0), 12.0f));
        ListenableList allGraphics = mGraphicsOverlay.getGraphics();
        allGraphics.clear();
        allGraphics.add(mapMarker);
        allGraphics.add(textGraphic);
        mMapView.setViewpointCenterAsync(geocodedLocation.getDisplayLocation());
    }
    private void setupLocator() {
        String locatorService = "https://geocode.arcgis.com/arcgis/rest/services/World/GeocodeServer";
        mLocatorTask = new LocatorTask(locatorService);
        mLocatorTask.addDoneLoadingListener(() -> {
            if (mLocatorTask.getLoadStatus() == LoadStatus.LOADED) {
                mGeocodeParameters = new GeocodeParameters();
                mGeocodeParameters.getResultAttributeNames().add("*");
                mGeocodeParameters.setMaxResults(1);
                mGraphicsOverlay = new GraphicsOverlay();
                mMapView.getGraphicsOverlays().add(mGraphicsOverlay);
            } else if (mSearchView != null) {
                mSearchView.setEnabled(false);
            }
        });
        mLocatorTask.loadAsync();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMapView = findViewById(R.id.mapView);
        setupMap();
        setupLocator();
        setupLocationDisplay();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        MenuItem searchMenuItem = menu.findItem(R.id.search);
        if (searchMenuItem != null) {
            mSearchView = (SearchView) searchMenuItem.getActionView();
            if (mSearchView != null) {
                SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
                mSearchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
                mSearchView.setIconifiedByDefault(false);
            }
        }
        return true;
    }

    @Override
    protected void onPause() {
        if (mMapView != null) {
            mMapView.pause();
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mMapView != null) {
            mMapView.resume();
        }
    }

    @Override
    protected void onDestroy() {
        if (mMapView != null) {
            mMapView.dispose();
        }
        super.onDestroy();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            mLocationDisplay.startAsync();
        } else {
            Toast.makeText(MainActivity.this, getResources().getString(R.string.location_permission_denied), Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            queryLocator(intent.getStringExtra(SearchManager.QUERY));
        }
    }

}
