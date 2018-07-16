package com.example.douglas.myapplication.Telas;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.example.douglas.myapplication.Classes.DatabaseHelper;
import com.example.douglas.myapplication.R;

public class RegistrarActivity extends AppCompatActivity {

    private DatabaseHelper helper;
    private EditText edtLogin, edtPswd1, edtPswd2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);


    }

    public void callRegistar(View v){
        Intent i = new Intent(RegistrarActivity.this, LoginActivity.class);

    }

}
