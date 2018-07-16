package com.example.douglas.myapplication.Telas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.douglas.myapplication.R;

public class LoginActivity extends AppCompatActivity {

    Button btnEntrar;
    EditText edtPswd, edtLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnEntrar =(Button) findViewById(R.id.btnEntrar);
        edtLogin = (EditText) findViewById(R.id.edtLogin);
        edtPswd = (EditText) findViewById(R.id.edtPswd);
    }
    //DEVE SER ALTERADO PRA LOGIN E SENHA SEREM ATIVOS
    public void callEntrar(View v){
        Intent i = new Intent(LoginActivity.this,MainActivity.class);
        startActivity(i);
    }


    public void callRegistrar(View view) {
        Intent i = new Intent(LoginActivity.this,RegistrarActivity.class);
        startActivity(i);
    }
}
