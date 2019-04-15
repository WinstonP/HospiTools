package com.example.hospitoolsv2;

import android.content.ClipData;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.TextView;


import java.util.ArrayList;

public class ViewPatients extends AppCompatActivity {
    private final static String ACTIVITY_TAG = "ViewPatients";
    private DatabaseManager databaseManager;
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_patient_activity);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        databaseManager = HomeActivity.databaseManager;
        scrollView = findViewById(R.id.scroll);
        updateView();
    }

    protected void onStart() {
        super.onStart();
        updateView();
    }


    public void updateView() {
        Log.w(ACTIVITY_TAG, "top of update");
        ArrayList<Patient> patientList = databaseManager.selectAll();
        int numPatients = patientList.size();
        Log.w(ACTIVITY_TAG, "update views");
        if (numPatients > 0) {
            scrollView.removeAllViewsInLayout();
            Log.w(ACTIVITY_TAG, "remove previous views");

            GridLayout gridLayout = new GridLayout(this);
            gridLayout.setRowCount(numPatients);
            gridLayout.setColumnCount(1);
            gridLayout.setPadding(10, 10, 0, 0);

            TextView[] labels = new TextView[numPatients];
            Log.w(ACTIVITY_TAG, "array of labels");


            for (int i = 0; i < patientList.size(); i++) {
                String patients = patientList.get(i).toString();
                Log.w(ACTIVITY_TAG, "update view loop");
                labels[i] = new TextView(this);
                labels[i].setText(patients);
                labels[i].setTextSize(24);
                labels[i].setPadding(10, 10, 10, 0);
                gridLayout.addView(labels[i]);
            }
            scrollView.addView(gridLayout);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.view_data:
                Intent viewData = new Intent(this, ViewData.class);
                this.startActivity(viewData);
                return true;

            case R.id.patient_data:
                Intent addDataIntent = new Intent(this, AddDataActivity.class);
                this.startActivity(addDataIntent);
                return true;

            case R.id.patient_edit:
                Intent editItemIntent = new Intent(this, EditItemActivity.class);
                this.startActivity(editItemIntent);
                return true;

            case R.id.patient_delete:
                Intent deleteItemIntent = new Intent(this, DeleteItemActivity.class);
                this.startActivity(deleteItemIntent);
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }

    }
}
