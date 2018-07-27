package com.example.douglas.myapplication.Telas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.douglas.myapplication.R;
import com.example.douglas.myapplication.banco.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;


public class  CadastroRebanhoActivity extends AppCompatActivity {
    Spinner spinner;
    private int idUsuario;
    DatabaseHelper helper;
    private String selectItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_rebanho);
        helper = new DatabaseHelper(getApplicationContext());
        Bundle ex = getIntent().getExtras();
        if(ex!=null){
            idUsuario = ex.getInt("idUsuario");
        }
        ArrayList<String>propriedades = new ArrayList<String>();

        propriedades = helper.propriedadesPorId(idUsuario);

        spinner = (Spinner)findViewById(R.id.spin);

        ArrayAdapter adaptadorPropriedades = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,propriedades);
        spinner.setAdapter(adaptadorPropriedades);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                 selectItem  = (String) adapterView.getItemAtPosition(i);// pega a string que foi selecionada no spinner

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
