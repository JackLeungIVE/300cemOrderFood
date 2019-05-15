package com.example.user.myapplication;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.user.myapplication.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.Locale;

public class SignUp extends AppCompatActivity {

    MaterialEditText edtPhone, edtName, edtPassword;
    Button btnSignUp, btnChangeLang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //change
        btnChangeLang = (Button)findViewById(R.id.btnchange_language);
        btnChangeLang.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                showChangeLanguageDialog();

            }

            private void showChangeLanguageDialog() {
                final String[] listItem = {"English", "Chinese"};
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(SignUp.this);
                mBuilder.setTitle("Choose Language");
                mBuilder.setSingleChoiceItems(listItem, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (i == 0) {
                            setLocale("en");
                            recreate();
                        } else if (i == 1) {
                            setLocale("za");
                            recreate();
                        }
                        dialogInterface.dismiss();
                    }
                });
                //change
                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });

        edtName = (MaterialEditText) findViewById(R.id.edtName);
        edtPassword = (MaterialEditText) findViewById(R.id.edtPassword);
        edtPhone = (MaterialEditText) findViewById(R.id.edtPhone);

        btnSignUp = (Button) findViewById(R.id.btnSignUp);

        //Init Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog mDialog = new ProgressDialog(SignUp.this);
                mDialog.setMessage("Please waiting");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //Check if already user phone
                        if (dataSnapshot.child(edtPhone.getText().toString()).exists()) {
                            mDialog.dismiss();
                            Toast.makeText(SignUp.this, "Phone Number already reqister", Toast.LENGTH_SHORT).show();
                        } else {
                            mDialog.dismiss();
                            User user = new User(edtName.getText().toString(), edtPassword.getText().toString());
                            table_user.child(edtPhone.getText().toString()).setValue(user);
                            Toast.makeText(SignUp.this, "Sign Up successfully", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });
    }
    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        //save data to shared preference
        SharedPreferences.Editor editor = getSharedPreferences("Setting", MODE_PRIVATE).edit();
        editor.putString("My_Lang", lang);
        editor.apply();
    }

    //change
    public void loadLocale(){
        SharedPreferences prefs = getSharedPreferences("Setting", MODE_PRIVATE);
        String language = prefs.getString("My_Lang", "");
        setLocale(language);
    }
}
