package com.example.douglas.myapplication.Telas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.douglas.myapplication.Classes.Animal;
import com.example.douglas.myapplication.Classes.Propriedade;
import com.example.douglas.myapplication.R;
import com.example.douglas.myapplication.banco.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;


public class CadastroRebanhoActivity extends AppCompatActivity {
    Spinner spinner;
    private int idUsuario;
    DatabaseHelper helper;
    private String selectItem;
    private EditText numBrinco, numRegistro, raca, pelagem, tipo, origemPai, origemMae;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_rebanho);
        helper = new DatabaseHelper(getApplicationContext());

        numBrinco = (EditText) findViewById(R.id.numBrinco);
        numRegistro = (EditText) findViewById(R.id.numRegistro);
        raca = (EditText) findViewById(R.id.raca);
        pelagem = (EditText) findViewById(R.id.pelagem);
        tipo = (EditText) findViewById(R.id.tipo);
        origemPai = (EditText) findViewById(R.id.origemPai);
        origemMae = (EditText) findViewById(R.id.origemMae);


        Bundle ex = getIntent().getExtras();
        if (ex != null) {
            idUsuario = ex.getInt("idUsuario");
        }
        ArrayList<String> propriedades = new ArrayList<String>();
        propriedades.add("Selecione a propriedade");
        propriedades = helper.propriedadesPorId(idUsuario);
        spinner = (Spinner) findViewById(R.id.spin);
        ArrayAdapter adaptadorPropriedades = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, propriedades);
        spinner.setAdapter(adaptadorPropriedades);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectItem = (String) adapterView.getItemAtPosition(i);// pega a string que foi selecionada no spinner

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void callRegistrar(View view) {
        int idPropriedade = helper.propriedadePorNomeEId(selectItem,idUsuario);
        if(selectItem != "") {
            if (idPropriedade != -1) {//quando retornar um id de Propriedade
                Animal animal = new Animal();
                Propriedade p = new Propriedade();
                p.setId(idPropriedade);
                animal.setNumBrinco(Integer.valueOf(numBrinco.getText().toString()));
                animal.setNumRegistro(Integer.valueOf(numRegistro.getText().toString()));
                animal.setTipo(tipo.getText().toString());
                animal.setRaca(raca.getText().toString());
                animal.setPelagem(pelagem.getText().toString());
                animal.setOrigemMae(origemMae.getText().toString());
                animal.setOrigemPai(origemPai.getText().toString());
                animal.setPropriedade(p);
                long c = helper.cadastraAnimal(animal);
                if (c != -1) {
                    Toast.makeText(this, "Animal cadastrado com sucesso!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Ocorreu um erro ao cadastrar o animal", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, "Ocorreu um erro ao procurar o id da propriedade", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(this, "Selecione a propriedade em que o animal está.", Toast.LENGTH_LONG).show();
        }
    }
}