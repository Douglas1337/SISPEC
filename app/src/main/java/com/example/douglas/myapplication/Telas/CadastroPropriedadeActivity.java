package com.example.douglas.myapplication.Telas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.douglas.myapplication.Classes.Propriedade;
import com.example.douglas.myapplication.Classes.Usuario;
import com.example.douglas.myapplication.R;
import com.example.douglas.myapplication.banco.DatabaseHelper;

public class CadastroPropriedadeActivity extends AppCompatActivity {

    private int idUsuario;

    private EditText txtNomePropriedade, txtExtensaoPropriedade, txtNomeProprietario, txtCpfCnpjProprietario, txtMunicipio, txtLocalidade;

    DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_propriedade);
        Bundle ex = getIntent().getExtras();
        if (ex != null) {
            idUsuario = ex.getInt("idUsuario"); // recebe o id do usuário para cadastro no bd
        }
        txtNomePropriedade = (EditText) findViewById(R.id.txtNomePropriedade); // campo obrigatório
        txtExtensaoPropriedade = (EditText) findViewById(R.id.txtExtensaoPropriedade);
        txtNomeProprietario = (EditText) findViewById(R.id.txtNomeProprietario);
        txtCpfCnpjProprietario = (EditText) findViewById(R.id.txtCpfCnpjProprietario);
        txtMunicipio = (EditText) findViewById(R.id.txtMunicipio);
        txtLocalidade = (EditText) findViewById(R.id.txtLocalidade);//obrigatório
    }


    public void cadastraPropriedade(View view) {

        helper = new DatabaseHelper(getApplicationContext());

        if (verificaCampos()) {
            Propriedade p = new Propriedade();
            p.setNome(txtNomePropriedade.getText().toString());
            p.setExtensao(Double.valueOf(txtExtensaoPropriedade.getText().toString()));
            p.setProprietario(txtNomeProprietario.getText().toString());
            p.setLocalidade(txtLocalidade.getText().toString());
            p.setCpfProprietario(txtCpfCnpjProprietario.getText().toString());
            p.setMunicipio(txtMunicipio.getText().toString());
            p.setIdUsuario(idUsuario);
            if (helper.buscaPropriedadeRepetida(p.getNome(),idUsuario)) {
                long cadastra = helper.inserePropriedade(p);
                if (cadastra != -1) {
                    Toast.makeText(this, "Propriedade cadastrada!", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(CadastroPropriedadeActivity.this, CadastrosActivity.class);
                    i.putExtra("idUsuario", idUsuario);
                    startActivity(i);
                } else {
                    Toast.makeText(this, "Erro ao cadastrar a propriedade !", Toast.LENGTH_LONG).show();
                }
            }else{
<<<<<<< HEAD
                Toast.makeText(this, "Propriedade com o mesmo nome já cadastrada!", Toast.LENGTH_LONG).show();
=======
                Toast.makeText(this, "Nome já cadastrado, insira outro.", Toast.LENGTH_LONG).show();
>>>>>>> 14e1f0d3c1befe8ae6b9ccbb032657593ab7b6f2
            }
        } else {
            Toast.makeText(this, "Campos NOME DA PROPRIEDADE e LOCALIDADE são obrigatórios", Toast.LENGTH_LONG).show();
        }
        helper.close();

    }

    private boolean verificaCampos() {//verifica se os campos de nome da propriedade ou localidade estão vazios
        String nomePropriedade = txtNomePropriedade.getText().toString();
        String localidade = txtLocalidade.getText().toString();

        if (!nomePropriedade.equals("") && !localidade.equals("")) {
            return true;
        } else {
            return false;
        }
    }


}
