package com.example.hospitoolsv2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.zip.DataFormatException;

public class DeleteItemActivity extends AppCompatActivity {
    private static final String ACTIVITY_TAG = "DeleteActivity";
    private DatabaseManager db;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        db = new DatabaseManager(this);
        updateView();
    }

    private void updateView() {
        ArrayList<Patient> patients = db.selectAll();
        RelativeLayout relativeLayout = new RelativeLayout(this);
        ScrollView scrollView = new ScrollView(this);
        RadioGroup radioGroup = new RadioGroup(this);
        for(Patient patient : patients){
            RadioButton radioButton = new RadioButton(this);
            radioButton.setId(patient.getId());
            radioButton.setText(patient.toString());
            radioGroup.addView(radioButton);
        }

        RadioButtonHandler radioButtonHandler  = new RadioButtonHandler();
        radioGroup.setOnCheckedChangeListener(radioButtonHandler);

        Button backButton = new Button(this);
        backButton.setText("Go Back");


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeleteItemActivity.this.finish();
            }
        });

        scrollView.addView(radioGroup);
        relativeLayout.addView(scrollView);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        params.setMargins(0,0,0,50);
        relativeLayout.addView(backButton,params);

        setContentView(relativeLayout);
    }


    private class RadioButtonHandler implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int checkedID) {
            db.deleteByID(checkedID);

            Toast.makeText(DeleteItemActivity.this,"Patient Deleted", Toast.LENGTH_SHORT).show();

            updateView();



        }
    }
}
