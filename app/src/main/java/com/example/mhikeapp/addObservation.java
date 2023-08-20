package com.example.mhikeapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class addObservation extends AppCompatActivity {
    TextView obsdate,obstime;
    EditText obscomment;
    Button addob;
    private  ObservationDB observationDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_observation);
        obsdate = (TextView) findViewById(R.id.obsdate);
        obstime=(TextView) findViewById(R.id.obstime);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            String time = LocalTime.now().toString();
            time=time.substring(0,time.indexOf("."));
            obstime.setText(time);
        }
        String date = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            date = LocalDate.now().toString();
            obsdate.setText(date);
        }



        Spinner dropdown = findViewById(R.id.spinnerobs);

        String[] items = new String[]{"Sighting of Animals","Vegetation","Weather Condition","Locality","Mountain","River","Places to eat"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        addob=(Button) findViewById(R.id.addobsbutton);
        obscomment=(EditText)findViewById(R.id.obscomment);
        observationDB = new ObservationDB(addObservation.this);
        addob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String obsadd = dropdown.getSelectedItem().toString().trim();
                String dateadd = obsdate.getText().toString().trim();
                String timeadd = obstime.getText().toString().trim();
                String comment = "";
                            comment = obscomment.getText().toString().trim()+"";




                if ((timeadd.equals("")||timeadd==null)||(obsadd.equals("")||obsadd==null) ||(dateadd.equals("")||dateadd==null)) {
                    Toast.makeText(addObservation.this, "Please enter all the data required", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{

                    observationDB.addObservation(getIntent().getStringExtra("id"),obsadd,dateadd,timeadd,comment);
                    Toast.makeText(addObservation.this, "Successfully added the observation", Toast.LENGTH_SHORT).show();
                    addObservation.this.finish();

                }
            }
        });


//

    }
}