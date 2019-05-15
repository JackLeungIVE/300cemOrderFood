package com.example.user.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PathPermission;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import java.util.Locale;
import java.util.Map;

import static android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity {
    Button btnSignIn, btnSignUp, btnGPS, btnMAP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnSignIn = (Button)findViewById(R.id.btnSignIn);
        btnSignUp = (Button)findViewById(R.id.btnSignUp);
        btnGPS = (Button)findViewById(R.id.btnGPS);
        btnMAP = (Button)findViewById(R.id.btnMAP);


        btnSignIn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnSignUp.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signUp = new Intent(MainActivity.this, SignUp.class);
                startActivity(signUp);
            }
        });

        btnSignIn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signIn = new Intent(MainActivity.this, Signin.class);
                startActivity(signIn);
            }
        });

        btnGPS.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signUp = new Intent(MainActivity.this, SignUp.class);
                startActivity(signUp);
            }
        });

        btnMAP.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent SignUp = new Intent(MainActivity.this, SignUp.class);
                startActivity(SignUp);
            }
        });

    }
}

