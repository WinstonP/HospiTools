package com.example.hospitoolsv2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class WaitTimes extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    Spinner hospitalSpinner;
    Spinner proceduresSpinner;
    EditText waitTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_times);

        waitTime = findViewById(R.id.WT_ET);

        hospitalSpinner = findViewById(R.id.hospitals_spinner);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.Hospitals, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hospitalSpinner.setAdapter(adapter1);
        hospitalSpinner.setOnItemSelectedListener(this);

        proceduresSpinner = findViewById(R.id.procedures_spinner);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.Procedures, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        proceduresSpinner.setAdapter(adapter2);
        proceduresSpinner.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
        if (hospitalSpinner.getSelectedItem().toString().equals("Touro") && proceduresSpinner.getSelectedItem().toString().equals("Headache")) {
            waitTime.setText("15 Minutes");
        } else if (hospitalSpinner.getSelectedItem().toString().equals("Touro") && proceduresSpinner.getSelectedItem().toString().equals("Foreign Object")) {
            waitTime.setText("Two Hours");
        } else if (hospitalSpinner.getSelectedItem().toString().equals("Touro") && proceduresSpinner.getSelectedItem().toString().equals("Skin Infection")) {
            waitTime.setText("Three Hours");
        } else if (hospitalSpinner.getSelectedItem().toString().equals("Touro") && proceduresSpinner.getSelectedItem().toString().equals("Back Pain")) {
            waitTime.setText("90 Minutes");
        } else if (hospitalSpinner.getSelectedItem().toString().equals("Touro") && proceduresSpinner.getSelectedItem().toString().equals("Cuts and Contusions")) {
            waitTime.setText("45 Minutes");
        } else if (hospitalSpinner.getSelectedItem().toString().equals("Touro") && proceduresSpinner.getSelectedItem().toString().equals("Upper Respiratory Infection")) {
            waitTime.setText("Two Hours");
        } else if (hospitalSpinner.getSelectedItem().toString().equals("Touro") && proceduresSpinner.getSelectedItem().toString().equals("Broken Bones and Spraons")) {
            waitTime.setText("Two Hours 30 Minutes");
        } else if (hospitalSpinner.getSelectedItem().toString().equals("Touro") && proceduresSpinner.getSelectedItem().toString().equals("Toothaches")) {
            waitTime.setText("90 Minutes");
        } else if (hospitalSpinner.getSelectedItem().toString().equals("Touro") && proceduresSpinner.getSelectedItem().toString().equals("Toothaches")) {
            waitTime.setText("45 Minutes");
        } else if (hospitalSpinner.getSelectedItem().toString().equals("Oschner") && proceduresSpinner.getSelectedItem().toString().equals("Headache")) {
            waitTime.setText("20 Minutes");
        } else if (hospitalSpinner.getSelectedItem().toString().equals("Oschner") && proceduresSpinner.getSelectedItem().toString().equals("Foreign Object")) {
            waitTime.setText("80 Minutes");
        } else if (hospitalSpinner.getSelectedItem().toString().equals("Oschner") && proceduresSpinner.getSelectedItem().toString().equals("Skin Infection")) {
            waitTime.setText("Two Hours Twenty Minutes");
        } else if (hospitalSpinner.getSelectedItem().toString().equals("Oschner") && proceduresSpinner.getSelectedItem().toString().equals("Back Pain")) {
            waitTime.setText("One Hour 20 Minutes");
        } else if (hospitalSpinner.getSelectedItem().toString().equals("Oschner") && proceduresSpinner.getSelectedItem().toString().equals("Cuts and Contusions")) {
            waitTime.setText("30 Minutes");
        } else if (hospitalSpinner.getSelectedItem().toString().equals("Oschner") && proceduresSpinner.getSelectedItem().toString().equals("Upper Respiratory Infection")) {
            waitTime.setText("Two Hours 10 Minutes");
        } else if (hospitalSpinner.getSelectedItem().toString().equals("Oschner") && proceduresSpinner.getSelectedItem().toString().equals("Broken Bones and Spraons")) {
            waitTime.setText("Two Hours 45 Minutes");
        } else if (hospitalSpinner.getSelectedItem().toString().equals("Oschner") && proceduresSpinner.getSelectedItem().toString().equals("Toothaches")) {
            waitTime.setText("75 Minutes");
        } else if (hospitalSpinner.getSelectedItem().toString().equals("Oschner") && proceduresSpinner.getSelectedItem().toString().equals("Toothaches")) {
            waitTime.setText("45 Minutes");}
        else if (hospitalSpinner.getSelectedItem().toString().equals("University") && proceduresSpinner.getSelectedItem().toString().equals("Headache")) {
            waitTime.setText("40 Minutes");
        } else if (hospitalSpinner.getSelectedItem().toString().equals("University") && proceduresSpinner.getSelectedItem().toString().equals("Foreign Object")) {
            waitTime.setText("70 Minutes");
        } else if (hospitalSpinner.getSelectedItem().toString().equals("University") && proceduresSpinner.getSelectedItem().toString().equals("Skin Infection")) {
            waitTime.setText("Two Hours Ten Minutes");
        } else if (hospitalSpinner.getSelectedItem().toString().equals("University") && proceduresSpinner.getSelectedItem().toString().equals("Back Pain")) {
            waitTime.setText("One Hour 15 Minutes");
        } else if (hospitalSpinner.getSelectedItem().toString().equals("University") && proceduresSpinner.getSelectedItem().toString().equals("Cuts and Contusions")) {
            waitTime.setText("35  Minutes");
        } else if (hospitalSpinner.getSelectedItem().toString().equals("University") && proceduresSpinner.getSelectedItem().toString().equals("Upper Respiratory Infection")) {
            waitTime.setText("Two Hours 15 Minutes");
        } else if (hospitalSpinner.getSelectedItem().toString().equals("University") && proceduresSpinner.getSelectedItem().toString().equals("Broken Bones and Spraons")) {
            waitTime.setText("Two Hours 25 Minutes");
        } else if (hospitalSpinner.getSelectedItem().toString().equals("University") && proceduresSpinner.getSelectedItem().toString().equals("Toothaches")) {
            waitTime.setText("80 Minutes");
        } else if (hospitalSpinner.getSelectedItem().toString().equals("University") && proceduresSpinner.getSelectedItem().toString().equals("Toothaches")) {
            waitTime.setText("50 Minutes");}
        Toast.makeText(adapterView.getContext(), text, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
