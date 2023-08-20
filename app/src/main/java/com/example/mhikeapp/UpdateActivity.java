package com.example.mhikeapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;

public class UpdateActivity extends AppCompatActivity {
    EditText hikename,location,length,description;
    TextView date;
    private RadioGroup radioGroup;
    Spinner dropdown;
    private RadioButton output;
    Button update, delete;
    String name,loc,datehike,parkingavailability,len,descp,lev,id;
    RadioGroup parking;
    RadioButton parkingyes,parkingno;
    public  static class  DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            LocalDate d = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                d = LocalDate.now();
            }
            int year = 0;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                year = d.getYear();
            }
            int month = 0;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                month = d.getMonthValue();
            }
            int day = 0;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                day = d.getDayOfMonth();
            }
            return new DatePickerDialog(getActivity(), this, year, --month, day);

        }

        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            LocalDate dob = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                dob = LocalDate.of(year,++month,day);
            }
            ((UpdateActivity)getActivity()).updteDOB(dob);
        }
    }

    private void updteDOB(LocalDate dob) {
        TextView dobText = (TextView) findViewById(R.id.hikedate2);
        dobText.setText(dob.toString());
    }

    public  void openDateFragment(View view){
        DialogFragment newFragment = new UpdateActivity.DatePickerFragment();
        newFragment.show(getSupportFragmentManager(),"datePicker");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        hikename = findViewById(R.id.hikename2);
        location = findViewById(R.id.hikelocation2);
        date = findViewById(R.id.hikedate2);
        radioGroup = findViewById(R.id.Parking2);
        parkingyes = findViewById(R.id.Parkingyes2);
        parkingno=findViewById(R.id.Parkingno2);
        output = (RadioButton) findViewById(R.id.Parkingyes2);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                                  @Override
                                                  public void onCheckedChanged(RadioGroup group, int checkedId) {
                                                      parkingno = (RadioButton) findViewById(R.id.Parkingno2);
                                                      if (parkingno.isChecked()) {
                                                          output = parkingno;
                                                      }
                                                      else {
                                                          output = (RadioButton) findViewById(R.id.Parkingyes2);
                                                      }

                                                  }
                                              }
        );
        length = findViewById(R.id.hikelength2);
        description=findViewById(R.id.description2);
        //get the spinner from the xml.
         dropdown = findViewById(R.id.spinner2);

//create a list of items for the spinner.
        String[] items = new String[]{"Difficult","Medium","Easy"};
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
//set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);

        update=findViewById(R.id.updatebutton);
        delete=findViewById(R.id.deletebutton);
        getIntentData();
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HikeDB hikeDB = new HikeDB(UpdateActivity.this);
                name=hikename.getText().toString().trim();
                loc=location.getText().toString().trim();
                datehike=date.getText().toString().trim();
                parkingavailability = output.getText().toString().trim();
                len=length.getText().toString().trim();
                descp=description.getText().toString().trim();
                lev=dropdown.getSelectedItem().toString().trim();
                        hikeDB.updatedata(id,name,loc,datehike,parkingavailability,len,lev,descp);
                UpdateActivity.this.finish();
                Toast.makeText(UpdateActivity.this, "You have successfully updated the hike", Toast.LENGTH_SHORT).show();



            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                display(id);

            }
        });

    }
    private void display(String idtodelete){
        new AlertDialog.Builder(this).setTitle("Delete The Hike").setMessage(
                "Are you sure you want to delete this hike?") .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                try {
                    HikeDB hikeDB=new HikeDB(UpdateActivity.this);
                    hikeDB.deleteHike(idtodelete);
                    UpdateActivity.this.finish();
                    Toast.makeText(UpdateActivity.this, "You have successfully deleted the hike", Toast.LENGTH_SHORT).show();





                }
                catch (Exception e){
                    e.printStackTrace();
                }

            }
        }).setNeutralButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).show();
    }

    void getIntentData() {
        if (getIntent().hasExtra("name")) {

            name = getIntent().getStringExtra("name");
            loc = getIntent().getStringExtra("location");
            id = getIntent().getStringExtra("id");
            datehike = getIntent().getStringExtra("date");
            parkingavailability = getIntent().getStringExtra("parking");
            loc = getIntent().getStringExtra("location");
            len = getIntent().getStringExtra("length");
            lev = getIntent().getStringExtra("level");
            descp = getIntent().getStringExtra("description");
            hikename.setText(name);
            location.setText(loc);
            length.setText(len);
            description.setText(descp);
            date.setText(datehike);
            System.out.println(parkingavailability);
            System.out.println(lev);
            if (parkingavailability.trim().equals("Yes"))
                parkingyes.setChecked(true);
            else
                parkingno.setChecked(true);
            if (lev.trim().equals("Difficult"))
                dropdown.setSelection(0);
            else if (lev.trim().equals("Medium"))
                dropdown.setSelection(1);
            else
                dropdown.setSelection(2);



    }

        else{
            Toast.makeText(this,"No Data Available",Toast.LENGTH_SHORT).show();
        }
    }
}