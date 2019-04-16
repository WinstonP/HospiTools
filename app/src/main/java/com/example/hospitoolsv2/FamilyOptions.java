package com.example.hospitoolsv2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FamilyOptions extends AppCompatActivity {
    Button viewWaitTimes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_options);

        viewWaitTimes = findViewById(R.id.Potential_wait_times_btn);

        viewWaitTimes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  viewTimes = new Intent(FamilyOptions.this,WaitTimes.class);
           FamilyOptions.this.startActivity(viewTimes);
            }
        });
    }
}
