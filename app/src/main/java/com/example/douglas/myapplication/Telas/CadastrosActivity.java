package com.example.douglas.myapplication.Telas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.douglas.myapplication.R;

public class CadastrosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastros);
    }

    public void callCadastroPropriedade(View v){
        Intent i = new Intent(CadastrosActivity.this,CadastroPropriedadeActivity.class);
        startActivity(i);
    }


    public void callCadastroRebanho(View v) {
        Intent i = new Intent(CadastrosActivity.this,CadastroRebanhoActivity.class);
        startActivity(i);
    }
}


