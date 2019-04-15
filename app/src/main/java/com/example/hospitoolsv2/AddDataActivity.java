package com.example.hospitoolsv2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddDataActivity extends AppCompatActivity {

    private DatabaseManager databaseManager;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        databaseManager = HomeActivity.databaseManager;

        setContentView(R.layout.activity_add_data);


    }

    public void insertPatientData(View view) {

        EditText firstNameET = findViewById(R.id.first_name_ET);
        EditText lastNameET = findViewById(R.id.last_name_et);
        EditText hospitalET = findViewById(R.id.hospital_name_et);
        EditText procedureET = findViewById(R.id.procedure_et);
        EditText waitTimeET = findViewById(R.id.waitTime_et);
        String firstName = firstNameET.getText().toString();
        String lastName = lastNameET.getText().toString();
        String hospital = hospitalET.getText().toString();
        String procedure = procedureET.getText().toString();
        String waitTime = waitTimeET.getText().toString();


        try {
            Data data = new Data(0, firstName, lastName, hospital, procedure, waitTime);
            databaseManager.insertData(data);
            Toast.makeText(this, "Patient Data Added", Toast.LENGTH_SHORT).show();
        } catch (NumberFormatException nfe) {
        }

        firstNameET.setText("");
        lastNameET.setText("");
        hospitalET.setText("");
        procedureET.setText("");
        waitTimeET.setText("");

    }


    public void goBack(View v) {
        this.finish();
    }


}
