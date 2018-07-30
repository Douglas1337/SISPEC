package com.example.douglas.myapplication.Telas;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.douglas.myapplication.Classes.Usuario;
import com.example.douglas.myapplication.banco.DatabaseHelper;
import com.example.douglas.myapplication.R;

public class RegistrarActivity extends AppCompatActivity {

    private DatabaseHelper helper;
    private EditText edtLogin, edtPswd1, edtPswd2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        edtLogin = (EditText) findViewById(R.id.edtLogin);
        edtPswd1 = (EditText) findViewById(R.id.edtPswd1);
        edtPswd2 = (EditText) findViewById(R.id.edtPswd2);
        helper = new DatabaseHelper(getApplicationContext());
    }

    public void callRegistar(View v) {
        String login = edtLogin.getText().toString();
        String pswd1 = edtPswd1.getText().toString();
        String pswd2 = edtPswd2.getText().toString();

        if (login.equals("") || pswd1.equals("") || pswd2.equals("")) { //verifica campos em branco
            Toast.makeText(this, "Não podem haver campos vazios para cadastrar um usuário.", Toast.LENGTH_LONG).show();
        } else {
            if (helper.buscaLoginRepetido(login)) { //verifica se tem um login duplicado
                Toast.makeText(this, "Este login já está sendo utilizado, escolha outro.", Toast.LENGTH_LONG).show();
            } else {
                boolean val = validaLoginSenha(login, pswd1, pswd2);
                if (val) { //se as senhas forem iguais
                    Usuario usuario = new Usuario();
                    usuario.setLogin(login);
                    usuario.setSenha(pswd1);
                    long resultado = helper.insereUsuario(usuario);
                    if (resultado != -1) {
                        Toast.makeText(this, "Cadastrado com sucesso.", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(RegistrarActivity.this, LoginActivity.class);
                        startActivity(i);
                    } else {
                        Toast.makeText(this, "Erro ao cadastrar.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, " Confira se ambos os campos de senha estão iguais ou se login tem mais de 6 caracteres", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    private boolean validaLoginSenha(String login, String pswd1, String pswd2) {
        if (pswd1.equals(pswd2) && login.length() > 5) {
            return true;
        } else {
            return false;
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        helper.close();
    }

}
