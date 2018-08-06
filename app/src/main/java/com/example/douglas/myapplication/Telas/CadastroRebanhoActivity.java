package com.example.douglas.myapplication.Telas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;

import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.douglas.myapplication.Classes.Animal;
import com.example.douglas.myapplication.Classes.Propriedade;
import com.example.douglas.myapplication.R;
import com.example.douglas.myapplication.banco.DatabaseHelper;

import java.sql.Array;
import java.util.ArrayList;


public class CadastroRebanhoActivity extends AppCompatActivity {
    private Spinner spinner, raca, pelagem,tipo;
    private int idUsuario;
    DatabaseHelper helper;
    private String selectItem, selectItemRaca, selectItemPelagem, selectItemTipo;
    private EditText numBrinco, numRegistro,origemPai, origemMae;


    private String[] racas = {"Raça não definida", "Gir", "Nelore", "Nelore Mocho", "Tabapuã", "Guzerá", "Indu Brasil", "Sindi", "Brahman", "Shorthon",
            "Polled Hereford", "Hereford", "Aberdeen Angus", "Red Angus", "Red Poll", "Devon", "Charolês", "Chianina", "Marchigiana",
            "Blonde D'aquitane", "Premontês", "Simental", "Normanda", "Flechwiech", "Caracu", "Pardo Suíço", "Santa Gertrudis", "Brangus",
            "Ibagé", "Braford", "Canchum", "Pilangueiras", "Santa Gabriela", "Tropicana", "Beefalo", "Bonsmara", "Pampa", "Zebu"};

    private String[] pelagens = {"Pelagem não definida", "Preto", "Branco", "Cinza", "Vermelho Escuro", "Pardo", "Malhado", "Brasino Claro", "Brasino Escuro",
            "Barroso", "Osco", "Salina", "Preto e Braco", "Vermelho e Branco"};

    private String[] tipos = {"Tipo não definido","Bezerro (1 a 12 meses)","Bezerra (1 a 12 meses)","Novilho (13 a 24 meses)","Novilha (13 a 24 meses)","Novilho (24 a 36 meses)",
            "Novilha (24 a 36 meses)","Boi (36 + meses","Vaca 36 + meses", "Vaca Descarte","Touro","Vaca de Cria","Boi de engorde"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_rebanho);
        helper = new DatabaseHelper(getApplicationContext());
        numBrinco = (EditText) findViewById(R.id.numBrinco);
        numRegistro = (EditText) findViewById(R.id.numRegistro);
        origemPai = (EditText) findViewById(R.id.origemPai);
        origemMae = (EditText) findViewById(R.id.origemMae);
        spinner = (Spinner) findViewById(R.id.spin);
        pelagem = (Spinner) findViewById(R.id.pelagem);
        tipo = (Spinner) findViewById(R.id.tipo);
        raca = (Spinner) findViewById(R.id.raca);

        Bundle ex = getIntent().getExtras();
        if (ex != null) {
            idUsuario = ex.getInt("idUsuario");
        }
        ArrayList<String> propriedades = new ArrayList<String>();
        propriedades.add("Selecione a propriedade");
        propriedades = helper.propriedadesPorId(idUsuario);

        ArrayAdapter adaptadorPropriedades = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, propriedades);
        ArrayAdapter adpRacas = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, racas);
        ArrayAdapter adpPelagem = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, pelagens);
        ArrayAdapter adpTipo = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,tipos);

        spinner.setAdapter(adaptadorPropriedades);
        raca.setAdapter(adpRacas);
        pelagem.setAdapter(adpPelagem);
        tipo.setAdapter(adpTipo);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectItem = (String) adapterView.getItemAtPosition(i);// pega a string que foi selecionada no spinner
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        raca.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectItemRaca = (String) adapterView.getItemAtPosition(i);// pega a string que foi selecionada no spinner
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        pelagem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectItemPelagem = (String) adapterView.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        tipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectItemTipo = (String) adapterView.getItemAtPosition(i);// pega a string que foi selecionada no spinner
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void callRegistrar(View view) {
        int idPropriedade = helper.propriedadePorNomeEId(selectItem, idUsuario);
        if (selectItem != "") {
            if (idPropriedade != -1) {//quando retornar um id de Propriedade
                Animal animal = new Animal();
                Propriedade p = new Propriedade();
                p.setId(idPropriedade);
                animal.setNumBrinco(Integer.valueOf(numBrinco.getText().toString()));
                animal.setNumRegistro(Integer.valueOf(numRegistro.getText().toString()));
                animal.setTipo(selectItemTipo.toString());
                animal.setRaca(selectItemRaca.toString());
                animal.setPelagem(selectItemPelagem.toString());
                animal.setOrigemMae(origemMae.getText().toString());
                animal.setOrigemPai(origemPai.getText().toString());
                animal.setPropriedade(p);
                long c = helper.cadastraAnimal(animal);
                if (c != -1) {
                    Toast.makeText(this, "Animal cadastrado com sucesso!", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(CadastroRebanhoActivity.this,CadastroRebanhoActivity.class);
                    i.putExtra("idUsuario",idUsuario);
                    startActivity(i);
                } else {
                    Toast.makeText(this, "Ocorreu um erro ao cadastrar o animal", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, "Ocorreu um erro ao procurar o id da propriedade", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Selecione a propriedade em que o animal está.", Toast.LENGTH_LONG).show();
        }
    }
}