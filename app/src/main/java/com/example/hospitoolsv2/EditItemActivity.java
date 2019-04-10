package com.example.hospitoolsv2;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class EditItemActivity extends AppCompatActivity {
    DatabaseManager db;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        db = new DatabaseManager(this);
        updateView();
    }

    public void updateView() {
        ArrayList<Patient> patients = db.selectAll();
        if(patients.size() > 0){

            ScrollView scrollView = new ScrollView(this);
            GridLayout gridLayout = new GridLayout(this);
            gridLayout.setRowCount(patients.size());
            gridLayout.setColumnCount(7);


            TextView [] ids = new TextView[patients.size()];
            EditText [][] patientInfo = new EditText[patients.size()][6];
            Button [] buttons = new Button[patients.size()];
            ButtonHandler bh = new ButtonHandler();

            Point size = new Point();
            getWindowManager().getDefaultDisplay().getSize(size);
            int width = size.x;

            int i = 0;

            for(Patient patient : patients){

                ids[i] = new TextView(this);
                ids[i].setGravity(Gravity.CENTER);
                ids[i].setText(" "+patient.getId());

                patientInfo[i][0] = new EditText(this);
                patientInfo[i][1] =new EditText(this);
                patientInfo[i][2] = new EditText(this);
                patientInfo[i][3] =new EditText(this);
                patientInfo[i][4] = new EditText(this);
                patientInfo[i][5] =new EditText(this);

                patientInfo[i][0].setText(patient.getFirstName());
                patientInfo[i][1].setText(" "+ patient.getLastName());
                patientInfo[i][2].setText(" "+ patient.getNumber());
                patientInfo[i][3].setText(" "+ patient.getGender());
                patientInfo[i][4].setText(" "+ patient.getDateOfBirth());
                patientInfo[i][5].setText(" "+ patient.getEmail());

                patientInfo[i][0].setId(10* patient.getId());
                patientInfo[i][1].setId(10* patient.getId()+1);
                patientInfo[i][2].setId(10* patient.getId()+2);
                patientInfo[i][3].setId(10* patient.getId()+3);
                patientInfo[i][4].setId(10* patient.getId()+4);
                patientInfo[i][5].setId(10* patient.getId()+5);

                buttons[i] = new Button(this);
                buttons[i].setText("Update Patient");
                buttons[i].setId(patient.getId());

                buttons[i].setOnClickListener(bh);

                gridLayout.addView(ids[i], width/10, ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(patientInfo[i][0],(int)(width * .10), ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(patientInfo[i][1],(int)(width * .10), ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(patientInfo[i][2],(int)(width * .10), ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(patientInfo[i][3],(int)(width * .10), ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(patientInfo[i][4],(int)(width * .10), ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(patientInfo[i][5],(int)(width * .10), ViewGroup.LayoutParams.WRAP_CONTENT);

                i++;

            }
            scrollView.addView(gridLayout);
            setContentView(scrollView);
        }

    }

    private  class  ButtonHandler implements  View.OnClickListener{
        public void onClick(View view){
            int patientID = view.getId();
            EditText firstNameEdit = findViewById(10*patientID);
            EditText lastNameEdit = findViewById(10*patientID + 1);
            EditText numberEdit = findViewById(10*patientID + 2);
            EditText genderEdit = findViewById(10*patientID + 3);
            EditText dobEdit = findViewById(10*patientID + 4);
            EditText emailEdit = findViewById(10*patientID + 5);

            String firstName = firstNameEdit.getText().toString();
            String lastName = lastNameEdit.getText().toString();
            String number = numberEdit.getText().toString();
            String gender = genderEdit.getText().toString();
            String dob = dobEdit.getText().toString();
            String email = emailEdit.getText().toString();

            db.updateById(patientID,firstName,lastName,number,gender, dob,email);
            Toast.makeText(EditItemActivity.this, "Patient Updated", Toast.LENGTH_SHORT).show();

            updateView();

        }

    }


}
