package com.example.douglas.myapplication.banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.douglas.myapplication.Classes.Animal;
import com.example.douglas.myapplication.Classes.Propriedade;
import com.example.douglas.myapplication.Classes.Usuario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String BANCO_DADOS = "BD_SISPEC";
    private static int VERSAO = 1;

    //TABLE NAMES ----------------------------------------------------------------------------------
    private static final String TABLE_USUARIOS = "usuarios";
    private static final String TABLE_PROPRIEDADES = "propriedades";
    private static final String TABLE_ANIMAIS = "animais";
    private static final String TABLE_ABATES = "abates";
    private static final String TABLE_PROCEDIMENTOS = "procedimentos";
    private static final String TABLE_INSUMOS = "insumos";
    private static final String TABLE_PESAGENS = "pesagens";
    private static final String TABLE_PROCEDIMENTO_INSUMO = "procedimento_insumo";
    private static final String TABLE_ANIMAL_PROCEDIMENTO = "animal_procedimento";
    private static final String TABLE_ANIMAL_PESAGEM = "animal_pesagem";

    // COLUNAS ========================================================================================

    // COLUNAS TABELA USUARIOS
    private static final String KEY_ID_USUARIO = "idUsuario";
    private static final String KEY_LOGIN = "login";
    private static final String KEY_SENHA = "senha";

    //COLUNAS TABELA PROPRIEDADES
    private static final String KEY_ID_PROPRIEDADE = "idPropriedade";
    private static final String KEY_NOME_PROPRIEDADE = "nome";
    private static final String KEY_EXTENSAO_PROPRIEDADE = "extensao";
    private static final String KEY_NOME_PROPRIETARIO = "nomeProprietario";
    private static final String KEY_CPFCNPJ_PROPRIETARIO = "cpfCnpjProprietario";
    private static final String KEY_NOME_MUNICIPIO = "municipio";
    private static final String KEY_NOME_LOCALIDADE = "localidade";
    private static final String KEY_FKUSUARIO = "fkUsuario";

    //COLUNAS TABELA ANIMAIS
    private static final String KEY_ID_ANIMAL = "idAnimal";
    private static final String KEY_NUM_BRINCO = "numBrinco";
    private static final String KEY_NUM_REGISTRO = "numRegistro";
    private static final String KEY_RACA = "raca";
    private static final String KEY_PELAGEM = "pelagem";
    private static final String KEY_TIPO_ANIMAL = "tipo";
    private static final String KEY_ORIGEM_PAI = "origemPai";
    private static final String KEY_ORIGEM_MAE = "origemMae";
    private static final String KEY_FKPROPRIEDADE = "fkPropriedade";
    private static final String KEY_FKABATE = "fkAbate";
    private static final String KEY_FKPROPRIEDADEIDUSUARIO = "fkusuario";

    // COLUNAS DA TABELA DE ABATES
    private static final String KEY_ID_ABATE = "idAbate";
    private static final String KEY_DATA_ABATE = "data";

    // COLUNAS DA TABELA PROCEDIMENTOS
    private static final String KEY_ID_PROCEDIMENTO = "idProcedimento";
    private static final String KEY_TIPO_PROCEDIMENTO = "tipo";
    private static final String KEY_DESCR_PROCEDIMENTO = "descricao";
    private static final String KEY_DATA_PROCEDIMENTO = "data";

    // COLUNAS DA TABELA PESAGEM
    private static final String KEY_ID_PESAGEM = "idPesagem";
    private static final String KEY_PESO = "peso";
    private static final String KEY_DATA_PESAGEM = "data";

    // COLUNAS DA TABELA INSUMO
    private static final String KEY_ID_INSUMOS = "idInsumo";
    private static final String KEY_NOME_INSUMO = "nomeInsumo";
    private static final String KEY_CUSTO_INSUMO = "custoInsumo";
    private static final String KEY_UTILIZACAO_INSUMO = "utilizacaoInsumo";
    private static final String KEY_DATA_COMPRA = "dataCompra";


    // TABELAS DE RELACIONAMENTO N:N

    //TABELA ANIMAL_PESAGEM
    private static final String KEY_ID_ANIMAL_PESAGEM = "id";

    // TABELA INSUMO_PROCEDIMENTO


    //TABELA ANIMAL_PROCEDIMENTO


    //CREATE STATEMENTS ======================================================================================================

    private static final String CREATE_TABLE_USUARIOS = "CREATE TABLE " + TABLE_USUARIOS + "(" +
            KEY_ID_USUARIO + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            KEY_LOGIN + " TEXT NOT NULL," +
            KEY_SENHA + " TEXT NOT NULL);";

    private static final String CREATE_TABLE_PROPRIEDADES = "CREATE TABLE " + TABLE_PROPRIEDADES + "(" +
            KEY_ID_PROPRIEDADE + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            KEY_NOME_PROPRIEDADE + " TEXT," +
            KEY_EXTENSAO_PROPRIEDADE + " TEXT ," +
            KEY_NOME_PROPRIETARIO + " TEXT ," +
            KEY_CPFCNPJ_PROPRIETARIO + " TEXT ," +
            KEY_NOME_MUNICIPIO + " TEXT ," +
            KEY_NOME_LOCALIDADE + " TEXT ," +
            KEY_FKUSUARIO + " INTEGER NOT NULL, " +
            " FOREIGN KEY (" + KEY_FKUSUARIO + ") REFERENCES " + TABLE_USUARIOS + "(" + KEY_ID_USUARIO + "));";

    private static final String CREATE_TABLE_ABATES = "CREATE TABLE " + TABLE_ABATES + "(" +
            KEY_ID_ABATE + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            KEY_DATA_ABATE + " TEXT);";


    private static final String CREATE_TABLE_ANIMAIS = "CREATE TABLE " + TABLE_ANIMAIS + "(" +
            KEY_ID_ANIMAL + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            KEY_NUM_BRINCO + " TEXT ," +
            KEY_NUM_REGISTRO + " TEXT ," +
            KEY_RACA + " TEXT ," +
            KEY_PELAGEM + " TEXT," +
            KEY_TIPO_ANIMAL + " TEXT," +
            KEY_ORIGEM_PAI + " TEXT," +
            KEY_ORIGEM_MAE + " TEXT," +
            KEY_FKPROPRIEDADEIDUSUARIO + " TEXT," +
            KEY_FKPROPRIEDADE + " INTEGER," +
            KEY_FKABATE + " INTEGER," +
            " FOREIGN KEY (" + KEY_FKABATE + ") REFERENCES " + TABLE_ABATES + "(" + KEY_ID_ABATE + ")," +
            " FOREIGN KEY (" + KEY_FKPROPRIEDADEIDUSUARIO + ") REFERENCES " + TABLE_PROPRIEDADES + "(" + KEY_FKUSUARIO + ")," +
            " FOREIGN KEY (" + KEY_FKPROPRIEDADE + ") REFERENCES " + TABLE_PROPRIEDADES + "(" + KEY_ID_PROPRIEDADE + "));";

    private static final String CREATE_TABLE_PROCEDIMENTOS = "CREATE TABLE " + TABLE_PROCEDIMENTOS + "(" +
            KEY_ID_PROCEDIMENTO + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            KEY_TIPO_PROCEDIMENTO + " TEXT ," +
            KEY_DESCR_PROCEDIMENTO + " TEXT ," +
            KEY_DATA_PROCEDIMENTO + " TEXT );";

    private static final String CREATE_TABLE_INSUMOS = "CREATE TABLE " + TABLE_INSUMOS + "(" +
            KEY_ID_INSUMOS + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            KEY_NOME_INSUMO + " TEXT ," +
            KEY_CUSTO_INSUMO + " TEXT ," +
            KEY_DATA_COMPRA + " TEXT ," +
            KEY_UTILIZACAO_INSUMO + " TEXT );";

    private static final String CREATE_TABLE_PESAGENS = "CREATE TABLE " + TABLE_PESAGENS + "(" +
            KEY_ID_PESAGEM + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            KEY_PESO + " TEXT ," +
            KEY_DATA_PESAGEM + " TEXT );";

    private static final String CREATE_TABLE_PROCEDIMENTOS_INSUMOS = "CREATE TABLE " + TABLE_PROCEDIMENTO_INSUMO + "(" +
            KEY_ID_PROCEDIMENTO + " INTEGER PRIMARY KEY NOT NULL," +
            KEY_ID_INSUMOS + " NOT NULL);";

    private static final String CREATE_TABLE_ANIMAL_PROCEDIMENTO = "CREATE TABLE " + TABLE_ANIMAL_PROCEDIMENTO + "(" +
            KEY_ID_PROCEDIMENTO + " INTEGER PRIMARY KEY NOT NULL," +
            KEY_ID_ANIMAL + "INTEGER NOT NULL);";

    private static final String CREATE_TABLE_ANIMAL_PESAGEM = "CREATE TABLE " + TABLE_ANIMAL_PESAGEM + "(" +
            KEY_ID_ANIMAL_PESAGEM + "INTEGER PRIMARY KEY NOT NULL," +
            KEY_ID_PESAGEM + "INTEGER," +
            KEY_ID_ANIMAL + "INTEGER);";

    public DatabaseHelper(Context context) {
        super(context, BANCO_DADOS, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USUARIOS);
        db.execSQL(CREATE_TABLE_PROPRIEDADES);
        db.execSQL(CREATE_TABLE_ANIMAIS);
        db.execSQL(CREATE_TABLE_ABATES);
        db.execSQL(CREATE_TABLE_PESAGENS);
        db.execSQL(CREATE_TABLE_INSUMOS);
        db.execSQL(CREATE_TABLE_PROCEDIMENTOS);
        db.execSQL(CREATE_TABLE_ANIMAL_PROCEDIMENTO);
        db.execSQL(CREATE_TABLE_PROCEDIMENTOS_INSUMOS);
        db.execSQL(CREATE_TABLE_ANIMAL_PESAGEM);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USUARIOS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROPRIEDADES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ANIMAIS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROCEDIMENTOS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PESAGENS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INSUMOS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROCEDIMENTOS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ANIMAL_PROCEDIMENTO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROCEDIMENTO_INSUMO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ANIMAL_PESAGEM);

    }

    /********************************************************************************
     * 08/08/2018 - DOUGLAS ALVES                                                   *
     * ALTERAÇÕES (DATA E NOME):                                                    *
     * INSERE NO BD UM USUARIO RECEBENDO COMO PARAMETRO UM OBJETO DE MESMO NOME     *
     * ******************************************************************************/
    public long insereUsuario(Usuario usuario) {

        ContentValues cv = new ContentValues();
        cv.put("login", usuario.getLogin());
        cv.put("senha", usuario.getSenha());

        long resultado = getWritableDatabase().insert("usuarios", null, cv);

        return resultado;
    }

    /********************************************************************************
     * 16/08/2018 - DOUGLAS ALVES                                                   *
     * ALTERAÇÕES (DATA E NOME):                                                    *
     * BUSCA NO BD UM LOGIN QUE JA ESTÁ SENDO UTILIZADO                             *
     * ******************************************************************************/
    public Boolean buscaLoginRepetido(String login) {
        String sql = "Select idUsuario from usuarios where login='" + login + "';";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        int qtdLogin = cursor.getCount();//conta o retorno de quantos foram selecionados
        if (qtdLogin > 0) {
            return true;
        } else {
            return false;
        }
    }

    /********************************************************************************
     * 16/08/2018 - DOUGLAS ALVES                                                   *
     * ALTERAÇÕES (DATA E NOME):                                                    *
     * BUSCA NO BD USUARIO CADASTRADO PARA FINS DE LOGIN                            *
     * ******************************************************************************/
    public int buscaUsuario(String login, String senha) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("Select idUsuario from usuarios where login=? and senha=? ;",new String[]{login,senha});
        cursor.moveToFirst();
        int a = cursor.getCount();
        if (a == 1) {
            int id = cursor.getInt(0);
            cursor.close();
            return id;
        } else {
            return -1; // retornará -1 se houver usuarios com mesmo login e senha
        }


    }


    /****************************************************
     * 13/08/2018- DOUGLAS ALVES                        *
     * ALTERAÇÕES (DATA E NOME):                        *
     *   VERIFICA SE A PROPRIEDADE TEM NOME DUPLICADO   *
     *   true = OK                                      *
     *   false = Já existe o nome                       *
     ***************************************************/
    public Boolean buscaPropriedadeRepetida(String nome, int idUsuario) {

        boolean retorno = false;
        SQLiteDatabase db = getReadableDatabase();
        db.beginTransaction();
        try {
            String sql = "Select idPropriedade from propriedades where nome='" + nome + "' and fkUsuario =" + idUsuario + ";";

            Cursor cursor = db.rawQuery(sql, null);

            int qtdLogin = cursor.getCount();//conta o retorno de quantos foram selecionados
            if (qtdLogin > 0) {
                retorno = false;
            } else {
                retorno = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
            db.close();
        }
        return retorno;
    }

    /********************************************************************************
     * 10/08/2018 - DOUGLAS ALVES                                                   *
     * ALTERAÇÕES (DATA E NOME):                                                    *
     *                                                                              *
     * INSERE NO BD UMA PROPRIEDADE RECEBENDO COMO PARAMETRO UM OBJETO DE MESMO NOME*
     *******************************************************************************/
    public long inserePropriedade(Propriedade propriedade) {

        ContentValues cv = new ContentValues();
        cv.put("nome", propriedade.getNome());
        cv.put("extensao", propriedade.getExtensao());
        cv.put("nomeProprietario", propriedade.getProprietario());
        cv.put("cpfCnpjProprietario", propriedade.getCpfProprietario());
        cv.put("municipio", propriedade.getMunicipio());
        cv.put("localidade", propriedade.getLocalidade());
        cv.put("fkUsuario", propriedade.getIdUsuario());

        long res = getWritableDatabase().insert("propriedades", null, cv);

        getWritableDatabase().close();

        return res;

    }


    /*****************************************************************************
     * 27/07/2018 - Douglas Alves                                                *
     * ALTERAÇÕES (DATA E NOME):                                                 *
     *                                                                           *
     * RECEBE  O ID DO USUARIO, RETORNA O UMA LISTA DAS PROPRIEDADES             *
     ****************************************************************************/
    public ArrayList<String> propriedadesPorId(Integer id) {
        ArrayList<String> lista = new ArrayList<String>();

        SQLiteDatabase db = getReadableDatabase();
        db.beginTransaction();
        try {
            String query = "SELECT " + KEY_NOME_PROPRIEDADE + " FROM " + TABLE_PROPRIEDADES + " WHERE " + KEY_FKUSUARIO + "= " + id + ";";
            Cursor c = db.rawQuery(query, null);
            if (c.getCount() > 0) {
                while (c.moveToNext()) {
                    String nomeProp = c.getString(c.getColumnIndex("nome"));
                    lista.add(nomeProp);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
            db.close();
        }
        return lista;
    }

    /******************************************************************************
     * 30/07/2018 - Douglas Alves                                                 *
     * RECEBE O NOME DA PROPRIEDADE E O ID DO USUARIO, RETORNA O ID DA PROPRIEDADE*
     *****************************************************************************/
    public int propriedadePorNomeEId(String nomePropriedade, int idUsuario) {
        int ret = 0;

        SQLiteDatabase db = getReadableDatabase();
        db.beginTransaction();
        try {
            String query = "SELECT " + KEY_ID_PROPRIEDADE + " FROM " + TABLE_PROPRIEDADES + " WHERE nome=" + nomePropriedade + " and " + KEY_FKUSUARIO + "=" + idUsuario + ";";
            Cursor cursor = db.rawQuery(query, null);
            int a = cursor.getCount();
            if (a == 1) {
                int id = cursor.getInt(0);
                cursor.close();
                return id;
            } else {
                return -1; // retornará -1 se houver usuarios com mesmo login e senha
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }

        return ret;
    }

    /********************************************************************************
     * 10/08/2018 - DOUGLAS ALVES                                                   *
     * ALTERAÇÕES (DATA E NOME):                                                    *
     *                                                                              *
     * INSERE NO BD UM ANIMAL RECEBENDO COMO PARAMETRO UM OBJETO DE MESMO NOME*
     *******************************************************************************/

    public long cadastraAnimal(Animal animal) {

        ContentValues cv = new ContentValues();
        cv.put("numBrinco",animal.getNumBrinco());
        cv.put("numRegistro",animal.getNumRegistro());
        cv.put("raca",animal.getRaca());
        cv.put("tipo",animal.getTipo());
        cv.put("pelagem",animal.getPelagem());
        cv.put("origemPai",animal.getOrigemPai());
        cv.put("origemMae",animal.getOrigemMae());
        cv.put("fkPropriedade",animal.getPropriedade().getId());

        long res = getWritableDatabase().insert(TABLE_ANIMAIS,null,cv);

        return res;
    }
}