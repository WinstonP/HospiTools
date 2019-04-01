package com.example.hospitools;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);


        final EditText firstName  = findViewById(R.id.firstName);
        final EditText lastName = findViewById(R.id.lastName);
        final EditText birthday = findViewById(R.id.birthday);
        final EditText gender = findViewById(R.id.gender);
        final EditText phone = findViewById(R.id.number);
        final TextView welcomeText = findViewById(R.id.WelcomeUser);
    }
}
