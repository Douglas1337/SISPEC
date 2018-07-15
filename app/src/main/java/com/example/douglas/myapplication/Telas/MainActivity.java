package com.example.douglas.myapplication.Telas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.douglas.myapplication.R;

public class MainActivity extends AppCompatActivity {

    Button btnCadastros;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCadastros = (Button) findViewById(R.id.btnCadastros);
    }

    public void callCadastros(View v){
        Intent i = new Intent(MainActivity.this,CadastrosActivity.class);
        startActivity(i);
    }

    public void callNfc(View v){
        Intent i = new Intent(MainActivity.this,NfcActivity.class);
    }
}
