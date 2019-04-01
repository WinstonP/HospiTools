package com.example.hospitools;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UserRegister extends AppCompatActivity {
    ProfileManager profileManager;
    EditText firstName;
    EditText lastName;
    EditText birthday;
    EditText gender;
    EditText phone;
    EditText patient_uN;
    EditText patientPass;
    EditText confirmPass;
    Button registerButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        profileManager = new ProfileManager(this);
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        birthday = findViewById(R.id.birthday);
        gender = findViewById(R.id.gender);
        phone = findViewById(R.id.number);
        patient_uN = findViewById(R.id.patientUsername);
        patientPass = findViewById(R.id.patientPassword);
        confirmPass = findViewById(R.id.confirm_password);
        registerButton = findViewById(R.id.patientRegister);


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = patient_uN.getText().toString().trim();
                String pwd = patientPass.getText().toString().trim();
                String confirm = confirmPass.getText().toString().trim();

                if (pwd.equals(confirm)) {
                    long val = profileManager.addProfile(user, pwd);
                    if (val > 0) {
                        Toast.makeText(UserRegister.this, "You are registered!", Toast.LENGTH_SHORT).show();
                        Intent moveToLogin = new Intent(UserRegister.this, LoginScreen.class);
                        startActivity(moveToLogin);
                    } else {
                        Toast.makeText(UserRegister.this, "Password not matching", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(UserRegister.this, "Registration error!", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

}
