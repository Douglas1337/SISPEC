package com.example.douglas.myapplication.Telas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.douglas.myapplication.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    //DEVE SER ALTERADO PRA LOGIN E SENHA SEREM ATIVOS
    public void callEntrar(View v){
        Intent i = new Intent(LoginActivity.this,MainActivity.class);
        startActivity(i);
    }
}
