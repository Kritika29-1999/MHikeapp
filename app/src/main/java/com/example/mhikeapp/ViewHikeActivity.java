package com.example.mhikeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class ViewHikeActivity extends AppCompatActivity {
    TextView viewname,viewlocation,viewdate,viewparking,viewlevel,viewlength,viewdesc;
    String viewnames,viewlocations,viewdates,viewparkings,viewlevels,viewlengths,viewdescs,viewids;
    ImageView img,img1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_hike);
        viewname = findViewById(R.id.hikenameview);
        viewlocation = findViewById(R.id.hikelocationview);
        viewdate = findViewById(R.id.hikedateview);
        viewparking = findViewById(R.id.parkingview);
        viewlevel = findViewById(R.id.levelview);
        viewlength=findViewById(R.id.hikelengthview);
        viewdesc = findViewById(R.id.descview);
        img=findViewById(R.id.addobs);
        img1=findViewById(R.id.imageView2);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewHikeActivity.this,addObservation.class);
                intent.putExtra("id",viewids);
                ViewHikeActivity.this.startActivity(intent);
            }
        });
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewHikeActivity.this,SeeObservations.class);
                intent.putExtra("id",viewids);
                ViewHikeActivity.this.startActivity(intent);
            }
        });
        getIntentData();

    }
    void getIntentData() {
        if (getIntent().hasExtra("name")) {

            viewnames = getIntent().getStringExtra("name");
            viewlocations = getIntent().getStringExtra("location");
            viewids = getIntent().getStringExtra("id");
            viewdates = getIntent().getStringExtra("date");
            viewparkings = getIntent().getStringExtra("parking");
            viewlengths = getIntent().getStringExtra("length");
            viewlevels = getIntent().getStringExtra("level");
            viewdescs = getIntent().getStringExtra("description");
            viewname.setText(viewnames);
            viewlocation.setText(viewlocations);
            viewlength.setText(viewlengths);
            viewdesc.setText(viewdescs);
            viewdate.setText(viewdates);
            viewparking.setText(viewparkings);
            viewlevel.setText(viewlevels);




        }

        else{
            Toast.makeText(this,"No Data Available",Toast.LENGTH_SHORT).show();
        }
    }
}