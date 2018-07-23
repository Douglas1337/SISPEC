package com.example.douglas.myapplication.Telas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.douglas.myapplication.R;

public class MainActivity extends AppCompatActivity {
    private int idUsuario;
    Button btnCadastros;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // pega o idUsuario passado pelo select na tela de login
        Bundle ex = getIntent().getExtras();
        if(ex!=null){
            idUsuario = ex.getInt("idUsuario");
        }

        btnCadastros = (Button) findViewById(R.id.btnCadastros);
    }

    public void callCadastros(View v){
        Intent i = new Intent(MainActivity.this,CadastrosActivity.class);
        i.putExtra("idUsuario",idUsuario);
        startActivity(i);
    }

    public void callNfc(View v){
        Intent i = new Intent(MainActivity.this,NfcActivity.class);
    }


}
