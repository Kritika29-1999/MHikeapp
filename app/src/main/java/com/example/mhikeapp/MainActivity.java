package com.example.mhikeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void redirecttologin(View view) {
        Intent intent = new Intent(MainActivity.this, LoginPage.class);
        startActivity(intent);
    }
    public void redirecttosignup(View view) {
        Intent intent = new Intent(MainActivity.this, SignupPageActivity.class);
        startActivity(intent);
    }
}