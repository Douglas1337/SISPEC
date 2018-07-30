package com.example.douglas.myapplication.Telas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.douglas.myapplication.R;

import java.util.ArrayList;
import java.util.List;


public class CadastroRebanhoActivity extends AppCompatActivity {
    Spinner spinner;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_rebanho);

        List<String> propriedades = new ArrayList<>();
        propriedades.add("Selecione a propriedade");

        spinner = (Spinner)findViewById(R.id.spin);

        ArrayAdapter adaptadorPropriedades = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,propriedades);
        spinner.setAdapter(adaptadorPropriedades);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
