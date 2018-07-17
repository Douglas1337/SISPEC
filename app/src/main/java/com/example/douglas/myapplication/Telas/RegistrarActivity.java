package com.example.douglas.myapplication.Telas;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.douglas.myapplication.Classes.DatabaseHelper;
import com.example.douglas.myapplication.R;

public class RegistrarActivity extends AppCompatActivity {

    private DatabaseHelper helper;
    private EditText edtLogin, edtPswd1, edtPswd2;
    private Button btnEntrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        btnEntrar = (Button) findViewById(R.id.btnEntrar);
        edtLogin = (EditText) findViewById(R.id.edtLogin);
        edtPswd1 = (EditText) findViewById(R.id.edtPswd1);
        edtPswd2 = (EditText) findViewById(R.id.edtPswd2);

    }

    public void callRegistar(View v) {
        String login = edtLogin.getText().toString();
        String pswd1 = edtPswd1.getText().toString();
        String pswd2 = edtPswd2.getText().toString();

        boolean val = validaLoginSenha(login,pswd1,pswd2);


        if (val){ //se as senhas forem iguais
            //SQLiteDatabase db = helper.getWritableDatabase();
            //ContentValues cv = new ContentValues();
            //cv.put("login",login);
            //cv.put("senha", pswd1);
            //Intent i = new Intent(RegistrarActivity.this, LoginActivity.class);
            Toast.makeText(this, "ADICIONA NO BANCO E MANDA PRA OUTRA TELA",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, " Confira se ambos os campos de senha estão iguais ou se login tem mais de 6 caracteres",Toast.LENGTH_LONG).show();
        }
    }

    private boolean validaLoginSenha(String login,String pswd1,String pswd2) {

        if (pswd1.equals(pswd2)&&login.length()>5) {
             return true;
        } else {
            return false;
        }
    }

}
