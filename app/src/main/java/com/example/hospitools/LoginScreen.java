package com.example.hospitools;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginScreen extends AppCompatActivity {

    private final static String ACTIVITY_TAG = "MainActivity";
    ProfileManager profileManager;
    EditText userName;
    EditText passwd;
    TextView signUpLink;
    Button loginButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        profileManager = new ProfileManager(this);
        userName = findViewById(R.id.username);
        passwd = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        signUpLink = findViewById(R.id.signUp);

        signUpLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.w(ACTIVITY_TAG, "Sign up link");
                Intent signUpIntent = new Intent(LoginScreen.this, ChooseType.class);
                LoginScreen.this.startActivity(signUpIntent);

            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.w(ACTIVITY_TAG,"top of Login button handler");
               String user = userName.getText().toString().trim();
                Log.w(ACTIVITY_TAG,"button handler, got the user");
                String password = passwd.getText().toString().trim();
                Log.w(ACTIVITY_TAG,"button handler, got trimmed pw");
                Boolean res = profileManager.checkUser(user, password);
                Log.w(ACTIVITY_TAG,"button handler, checked user");
                if (res == true) {
                    Intent login = new Intent(LoginScreen.this, UserActivity.class);
                    startActivity(login);
                    Log.w(ACTIVITY_TAG, "Login button success");
                    Toast.makeText(LoginScreen.this, "Successfully Logged In", Toast.LENGTH_SHORT);
                } else {
                    Log.w(ACTIVITY_TAG, "Login button failed");
                    Toast.makeText(LoginScreen.this, "Login Error", Toast.LENGTH_SHORT);
                }
            }
        });
    }


}
