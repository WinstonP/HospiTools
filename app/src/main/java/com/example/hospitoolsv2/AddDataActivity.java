package com.example.hospitoolsv2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class AddDataActivity extends AppCompatActivity {

    private DatabaseManager databaseManager;


    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    databaseManager = HomeActivity.databaseManager;

    setContentView(R.layout.activity_add_data);
}

    public void goBack(View v) {
        this.finish();
    }


}
