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

public class DataEditor extends AppCompatActivity {
    DatabaseManager db;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DatabaseManager(this);
        updateView();
    }

    public void updateView() {
        ArrayList<Data> dataArrayList = db.selectAllData();
        if (dataArrayList.size() > 0) {

            ScrollView scrollView = new ScrollView(this);
            GridLayout gridLayout = new GridLayout(this);
            gridLayout.setRowCount(dataArrayList.size());
            gridLayout.setColumnCount(7);

            TextView[] ids = new TextView[dataArrayList.size()];
            EditText[][] dataInfo = new EditText[dataArrayList.size()][5];
            Button[] buttons = new Button[dataArrayList.size()];
            ButtonHandler bh = new ButtonHandler();

            Point size = new Point();
            getWindowManager().getDefaultDisplay().getSize(size);
            int width = size.x;

            int i = 0;

            for (Data data : dataArrayList) {
                ids[i] = new TextView(this);
                ids[i].setGravity(Gravity.LEFT);
                ids[i].setTextSize(10);
                ids[i].setText("" + data.getDataID());


                dataInfo[i][0] = new EditText(this);
                dataInfo[i][1] = new EditText(this);
                dataInfo[i][2] = new EditText(this);
                dataInfo[i][3] = new EditText(this);
                dataInfo[i][4] = new EditText(this);

                dataInfo[i][0].setText(data.getFirstName());
                dataInfo[i][0].setTextSize(10);
                dataInfo[i][1].setText("" + data.getLastName());
                dataInfo[i][1].setTextSize(10);
                dataInfo[i][2].setText("" + data.getHospital());
                dataInfo[i][2].setTextSize(10);
                dataInfo[i][3].setText("" + data.getProcedure());
                dataInfo[i][3].setTextSize(10);
                dataInfo[i][4].setText("" + data.getWaitTime());
                dataInfo[i][4].setTextSize(10);

                dataInfo[i][0].setId(10 * data.getDataID());
                dataInfo[i][1].setId(10 * data.getDataID() + 1);
                dataInfo[i][2].setId(10 * data.getDataID() + 2);
                dataInfo[i][3].setId(10 * data.getDataID() + 3);
                dataInfo[i][4].setId(10 * data.getDataID() + 4);

                buttons[i] = new Button(this);
                buttons[i].setText("Update\n" + data.getFirstName());
                buttons[i].setId(data.getDataID());

                buttons[i].setOnClickListener(bh);

                gridLayout.addView(ids[i], (int) (width * .025), ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(dataInfo[i][0], (int) (width * .15), ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(dataInfo[i][1], (int) (width * .15), ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(dataInfo[i][2], (int) (width * .15), ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(dataInfo[i][3], (int) (width * .15), ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(dataInfo[i][4], (int) (width * .15), ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(buttons[i], (int) (width * .25), ViewGroup.LayoutParams.WRAP_CONTENT);

                i++;

            }

            scrollView.addView(gridLayout);
            setContentView(scrollView);

        }
    }

    private class ButtonHandler implements View.OnClickListener{
        public void onClick(View view){
            int dataID = view.getId();
            EditText firstNameET = findViewById(10 * dataID);
            EditText lastNameET = findViewById(10 * dataID +1);
            EditText hospitalNameET = findViewById(10 * dataID+2);
            EditText procedureNameET = findViewById(10 * dataID+3);
            EditText waitTimeET = findViewById(10 * dataID+4);

            String firstName = firstNameET.getText().toString();
            String lastName = lastNameET.getText().toString();
            String hospitalName = hospitalNameET.getText().toString();
            String procedureName = procedureNameET.getText().toString();
            String waitTime = waitTimeET.getText().toString();

            try{
                db.updateDataById(dataID,firstName,lastName,hospitalName,procedureName,waitTime);
                Toast.makeText(DataEditor.this, "Patient Updated", Toast.LENGTH_SHORT).show();
            } catch (NumberFormatException nfe) {
                Toast.makeText(DataEditor.this, "Patient Not Updated", Toast.LENGTH_SHORT).show();
            }
            updateView();


        }

    }
}
