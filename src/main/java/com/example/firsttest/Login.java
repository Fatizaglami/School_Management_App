package com.example.firsttest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Login extends AppCompatActivity implements View.OnClickListener{
 Button register;
 Boolean vis=true;
 LinearLayout logoutvis;
 LinearLayout visLog;
 LinearLayout layout_1;
 TextView nomApp;
 TextView desc;
 LinearLayout layout_2;
 EditText email;
 EditText password;
 LinearLayout btn;
 Button SignIn;
Button okCon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        logoutvis=findViewById(R.id.logoutvis);
        logoutvis.setOnClickListener(this);
        register= findViewById(R.id.register);
        layout_2= findViewById(R.id.layout_2);
        layout_2.setOnClickListener(this);

        btn= findViewById(R.id.btn);
        btn.setOnClickListener(this);

        okCon= findViewById(R.id.okCon);
        SignIn=findViewById(R.id.SignIn);

        SignIn.setOnClickListener(this);



        register.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.register:
                startActivity(new Intent(this, Register.class));
                break;
            case R.id.SignIn:
                layout_2.setVisibility(View.GONE);
                btn.setVisibility(View.GONE);
                logoutvis.setVisibility(View.VISIBLE);


        }

}}