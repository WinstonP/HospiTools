package com.example.hospitoolsv2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CreatePatient extends AppCompatActivity {

    private DatabaseManager databaseManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        databaseManager = HomeActivity.databaseManager;

        setContentView(R.layout.activity_create_patient);
    }

    public void insertPatient(View view) {

        EditText firstNameET = findViewById(R.id.first_name_ET);
        EditText lastNameET = findViewById(R.id.last_name_et);
        EditText numberET = findViewById(R.id.number_et);
        EditText genderET = findViewById(R.id.gender_et);
        EditText dateOfBirthET = findViewById(R.id.date_of_birth_et);
        EditText emailET = findViewById(R.id.patient_email_eT);
        String firstName = firstNameET.getText().toString();
        String lastName = lastNameET.getText().toString();
        String number = numberET.getText().toString();
        String gender = genderET.getText().toString();
        String dateOfBirth = dateOfBirthET.getText().toString();
        String email = emailET.getText().toString();

        try {
            Patient patient = new Patient(0, firstName, lastName, number, gender, dateOfBirth, email);
            databaseManager.insertPatient(patient);
            Toast.makeText(this, "Patient Created", Toast.LENGTH_SHORT).show();
        } catch (NumberFormatException nfe) {

        }

        firstNameET.setText("");
        lastNameET.setText("");
        numberET.setText("");
        genderET.setText("");
        dateOfBirthET.setText("");
        emailET.setText("");
    }


    public void goBack(View v) {
        this.finish();
    }


}
