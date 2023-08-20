package com.example.mhikeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.service.controls.actions.FloatAction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

public class SeeObservations extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;
    ArrayList<ObservationPojo> dataholder = new ArrayList<>();
    BottomAppBar bottomAppBar;
    myadapterobs adapter;
    HikeDB hikeDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_observations);
        recyclerView = (RecyclerView) findViewById(R.id.recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        String  hikeid = getIntent().getStringExtra("id");
        Cursor cursor = new ObservationDB(this).readalldate( hikeid);
        while(cursor.moveToNext()){
            ObservationPojo hikePojo = new ObservationPojo(cursor.getString(1),cursor.getString(0),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5));
            dataholder.add(hikePojo);
        }
        adapter= new myadapterobs(dataholder);
        recyclerView.setAdapter(adapter);





    }


}