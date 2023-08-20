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

public class LandingPageActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;
    ArrayList<HikePojo> dataholder = new ArrayList<>();
    BottomAppBar bottomAppBar;
    myadapter adapter;
    HikeDB hikeDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
        recyclerView = (RecyclerView) findViewById(R.id.recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Cursor cursor = new HikeDB(this).readalldate();
        while(cursor.moveToNext()){
            HikePojo hikePojo = new HikePojo(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7));
            dataholder.add(hikePojo);
        }
        adapter= new myadapter(dataholder);
        recyclerView.setAdapter(adapter);
        bottomAppBar=(BottomAppBar)findViewById(R.id.bottomAppBar);
        floatingActionButton=(FloatingActionButton)findViewById(R.id.addhikefloating);
        bottomAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor = new HikeDB(LandingPageActivity.this).readalldate();
                JSONArray jsonArray = new JSONArray();
                while(cursor.moveToNext()){
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("name",cursor.getString(1));
                        jsonObject.put("location",cursor.getString(2));
                        jsonArray.put(jsonObject);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
                JSONObject jsonObjectfinal = new JSONObject();
                try {
                    jsonObjectfinal.put("userId","001207067");
                    jsonObjectfinal.put("detailList",jsonArray);
                    sendResponse(jsonObjectfinal);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LandingPageActivity.this,HikeDetails.class);
                startActivity(intent);
            }
        });
        bottomAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                display();
                return true;
            }

            private void display(){
                new AlertDialog.Builder(LandingPageActivity.this).setTitle("Delete All the Hikes").setMessage(
                        "Are you sure you want to delete all the hikes enetered?") .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        try {
                            HikeDB hikeDB=new HikeDB(LandingPageActivity.this);
                            hikeDB.deleteAll();

                            Intent intent = new Intent(LandingPageActivity.this, LandingPageActivity.class);
                            startActivity(intent);
                            Toast.makeText(LandingPageActivity.this, "You have successfully deleted all the hikes", Toast.LENGTH_SHORT).show();





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


        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView=(SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private String sendResponse(JSONObject jsonObject) throws IOException {
        String s =  jsonObject.toString();
        Intent i = new Intent(LandingPageActivity.this,JsonActivity.class);
        i.putExtra("jsonstring",s);
        startActivity(i);
        return "";

    }
}