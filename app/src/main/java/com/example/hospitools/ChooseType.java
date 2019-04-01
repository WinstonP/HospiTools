package com.example.hospitools;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ChooseType extends AppCompatActivity {

    private RadioGroup radioGroup;
    private RadioButton patientRadio;
    private RadioButton doctorRadio;
    private RadioButton familyMemberRadio;
    private Button continuebtn;
    private Button backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_type);

        radioGroup = findViewById(R.id.radioGroup);
        patientRadio = findViewById(R.id.patientRadio);
        doctorRadio = findViewById(R.id.doctorRadio);
        familyMemberRadio = findViewById(R.id.familyMemberRadio);
        continuebtn = findViewById(R.id.continuebtn);
        backBtn = findViewById(R.id.btnGoBack);

        continuebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (patientRadio.isChecked()) {
                    Intent intent = new Intent(ChooseType.this, UserRegister.class);
                    startActivity(intent);
                } else if (doctorRadio.isChecked()) {
                    Intent intent = new Intent(ChooseType.this, DoctorRegister.class);
                    startActivity(intent);
                } else if (familyMemberRadio.isChecked()) {
                    Intent intent = new Intent(ChooseType.this, FamilyRegister.class);
                    startActivity(intent);
                }

            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(ChooseType.this,LoginScreen.class);
                startActivity(back);
            }
        });

    }


}

