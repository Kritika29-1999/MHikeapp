package com.example.mhikeapp;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.time.LocalDate;


public class HikeDetails extends AppCompatActivity {
    private EditText hikename,hikelocation,hikelength, hikedesc;
    private Spinner spinner;
    private TextView hikedate;
    private RadioGroup radioGroup;
    private RadioButton radioButton,output;
    private Button addhike;
    private  HikeDB hikedetailsdb;
//    private ImageView image;
//    Bitmap selectedImageBitmap = null;
//    DBBitMapUtility dbBitMapUtility = new DBBitMapUtility();
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
        ((HikeDetails)getActivity()).updteDOB(dob);
    }
}

    private void updteDOB(LocalDate dob) {
        TextView dobText = (TextView) findViewById(R.id.hikedate);
        dobText.setText(dob.toString());
    }
    private void display(String name, String location, String date, String parking, String length, String level, String Description){
        new AlertDialog.Builder(this).setTitle("Details entered").setMessage(
                "Details entered: \n Hike Name: " + name+"\n Hike Location: "+location+"\n Hike Date: "+date+"\n Parking availability: "+parking+
                "\n Hike Length: "+length+"\n Hike Level: "+level+"\n Description: "+Description+"\n") .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                try {

                    hikedetailsdb.addUsers(name, location, date,parking,length, level, Description);

                    Toast.makeText(HikeDetails.this, "Your details have been saved", Toast.LENGTH_SHORT).show();
                    HikeDetails.this.finish();

                }
                catch (Exception e){
                    e.printStackTrace();
                }

            }
        }).setNeutralButton("Edit details", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).show();
    }

    public  void openDateFragment(View view){
    DialogFragment newFragment = new DatePickerFragment();
    newFragment.show(getSupportFragmentManager(),"datePicker");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hike_details);
        hikename = findViewById(R.id.hikename);
        hikelocation = findViewById(R.id.hikelocation);
        hikedate = findViewById(R.id.hikedate);
        radioGroup = findViewById(R.id.Parking);
        output = (RadioButton) findViewById(R.id.Parkingyes);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                                  @Override
                                                  public void onCheckedChanged(RadioGroup group, int checkedId) {
                                                      radioButton = (RadioButton) findViewById(R.id.Parkingno);
                                                      if (radioButton.isChecked())
                                                          output = radioButton;
                                                      else
                                                          output = (RadioButton) findViewById(R.id.Parkingyes);
                                                  }
                                              }
        );
        hikelength = findViewById(R.id.hikelength);
        spinner=findViewById(R.id.spinner1);
        hikedesc=findViewById(R.id.description);
        addhike = findViewById(R.id.hikedbbutton);
        Spinner dropdown = findViewById(R.id.spinner1);

        String[] items = new String[]{"Difficult","Medium","Easy"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
//
        hikedetailsdb = new HikeDB(HikeDetails.this);
//        image.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                uploadimage();
//            }
//
//
//
//        });
//
//
//

        addhike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = hikename.getText().toString().trim();
                String location = hikelocation.getText().toString().trim();
                String date = hikedate.getText().toString().trim();
                String parking = output.getText().toString().trim();
                String length = hikelength.getText().toString().trim();
                String level = spinner.getSelectedItem().toString().trim();
                String desc="";
                desc=hikedesc.getText().toString().trim();

                if ((level.equals("")||level==null) ||(length.equals("")||length==null) ||(name.equals("")||name==null) || (location.equals("")||location==null) || (date.equals("")||date==null) ||(parking.equals("")||parking==null)) {
                    Toast.makeText(HikeDetails.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    display(name,location,date,parking,length,level,desc);
                }







            }
        });


    }
//    void uploadimage() {
//
//        // create an instance of the
//        // intent of the type image
//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//
//        // pass the constant to compare it
//        // with the returned requestCode
//        imageactivity.launch(intent);
//    }
//    ActivityResultLauncher<Intent> imageactivity
//            = registerForActivityResult(
//            new ActivityResultContracts
//                    .StartActivityForResult(),
//            result -> {
//                if (result.getResultCode()
//                        == Activity.RESULT_OK) {
//                    Intent data = result.getData();
//                    // do your operation from here....
//                    if (data != null
//                            && data.getData() != null) {
//                        Uri selectedImageUri = data.getData();
//
//                        try {
//                            selectedImageBitmap
//                                    = MediaStore.Images.Media.getBitmap(
//                                    this.getContentResolver(),
//                                    selectedImageUri);
//                        }
//                        catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                        try {
//                            image.setImageBitmap(
//                                    selectedImageBitmap);
//                        }
//                        catch (Exception e){
//
//                        }
                    }



//                }
//            });

