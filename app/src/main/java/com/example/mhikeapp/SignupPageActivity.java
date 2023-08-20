package com.example.mhikeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupPageActivity extends AppCompatActivity {
    private EditText emailtext,passwordtext,repasswordtext,fullnametext;
    private Button signup;
    private  DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);
        emailtext = findViewById(R.id.spemailaddress);
        passwordtext =findViewById(R.id.sppassword);
        repasswordtext=findViewById(R.id.sprepassword);
        fullnametext=findViewById(R.id.spfullname);
        signup=findViewById(R.id.signupbutton);
        dbHandler = new DBHandler(SignupPageActivity.this);



        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullName = fullnametext.getText().toString().trim();
                String email = emailtext.getText().toString().trim();
                String password = passwordtext.getText().toString().trim();
                String repassword = repasswordtext.getText().toString().trim();
                if ((fullName.equals("")||fullName==null) || (password.equals("")||password==null) || (repassword.equals("")||repassword==null) ) {
                    Toast.makeText(SignupPageActivity.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!password.equals(repassword)){
                    Toast.makeText(SignupPageActivity.this, "Passwords does not match.", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    dbHandler.addUsers(fullName, email, password);

                    Intent intent = new Intent(SignupPageActivity.this, LandingPageActivity.class);
                    startActivity(intent);
                    Toast.makeText(SignupPageActivity.this, "You have been successfully signed up", Toast.LENGTH_SHORT).show();

                }
                catch (Exception e){
                    e.printStackTrace();
                }


            }
        });


    }
}