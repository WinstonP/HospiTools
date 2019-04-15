package com.example.hospitoolsv2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewData extends AppCompatActivity {

    private final static String ACTIVITY_TAG = "ViewPatients";
    private DatabaseManager databaseManager;
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);


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
        ArrayList<Data> dataArrayList = databaseManager.selectAllData();
        int numData = dataArrayList.size();
        Log.w(ACTIVITY_TAG, "update views");
        if (numData> 0) {
            scrollView.removeAllViewsInLayout();
            Log.w(ACTIVITY_TAG, "remove previous views");

            GridLayout gridLayout = new GridLayout(this);
            gridLayout.setRowCount(numData);
            gridLayout.setColumnCount(1);
            gridLayout.setPadding(10, 10, 0, 0);

            TextView[] labels = new TextView[numData];
            Log.w(ACTIVITY_TAG, "array of labels");


            for (int i = 0; i < dataArrayList.size(); i++) {
                String patients = dataArrayList.get(i).toString();
                Log.w(ACTIVITY_TAG, "update view loop");
                labels[i] = new TextView(this);
                labels[i].setText(patients);
                labels[i].setTextSize(24);
                labels[i].setPadding(10, 10, 10, 0);
                gridLayout.addView(labels[i]);
            }
            scrollView.addView(gridLayout);
        }
    }}