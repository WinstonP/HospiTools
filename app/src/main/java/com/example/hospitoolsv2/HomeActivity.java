package com.example.hospitoolsv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.esri.arcgisruntime.findplaces.MainActivity;

public class HomeActivity extends AppCompatActivity {

    public static DatabaseManager databaseManager;

Button create_Patient_BTN;
Button view_patient_BTN;
Button find_hospitals_BTN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        databaseManager = new DatabaseManager(this);

        setContentView(R.layout.home_activity);

        create_Patient_BTN = findViewById(R.id.create_btn);
        view_patient_BTN = findViewById(R.id.view_btn);
        find_hospitals_BTN = findViewById(R.id.find_Hospital);



        create_Patient_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent create_Patient = new Intent(HomeActivity.this, CreatePatient.class);
                HomeActivity.this.startActivity(create_Patient);
            }
        });

        find_hospitals_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent find_hospitals = new Intent(HomeActivity.this, MainActivity.class);
                HomeActivity.this.startActivity(find_hospitals);
            }
        });

        view_patient_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent view_patient = new Intent(HomeActivity.this,ViewPatients.class);
                HomeActivity.this.startActivity(view_patient);
            }
        });
    }



}
