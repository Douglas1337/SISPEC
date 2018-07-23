package com.example.douglas.myapplication.Telas;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.douglas.myapplication.banco.DatabaseHelper;
import com.example.douglas.myapplication.R;

public class LoginActivity extends AppCompatActivity {

    private DatabaseHelper helper;

    private Button btnEntrar;
    private EditText edtPswd, edtLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnEntrar = (Button) findViewById(R.id.btnEntrar);
        edtLogin = (EditText) findViewById(R.id.edtLogin);
        edtPswd = (EditText) findViewById(R.id.edtPswd);
        helper = new DatabaseHelper(this);
    }

    //logica para o usuário entrar no sistema
    public void callEntrar(View v) {
        String login = edtLogin.getText().toString();
        String senha = edtPswd.getText().toString();
        if (login.equals("") || senha.equals("")) {
            Toast.makeText(this, "Há campos em branco", Toast.LENGTH_LONG).show();
        } else {
                int extra = helper.buscaUsuario(login,senha);
            if ( extra != -1) {
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                i.putExtra("idUsuario",extra); //passando a idUsuario logado para a intent main
                startActivity(i);
            } else {
                Toast.makeText(this, "Usuário ou senha incorretos!", Toast.LENGTH_LONG).show();
            }
        }
    }

    //chama a Activity de Registro de usuário
    public void callRegistrar(View view) {
        Intent i = new Intent(LoginActivity.this, RegistrarActivity.class);
        startActivity(i);
    }

    protected void onDestroy() {
        super.onDestroy();
        helper.close();
    }
}
