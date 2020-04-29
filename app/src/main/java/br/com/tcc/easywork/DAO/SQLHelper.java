package br.com.tcc.easywork.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import br.com.tcc.easywork.model.CategoriaServico;
import br.com.tcc.easywork.model.DadosAparelho;
import br.com.tcc.easywork.model.Endereco;
import br.com.tcc.easywork.model.PerfilUsuario;
import br.com.tcc.easywork.model.Servicos;
import br.com.tcc.easywork.model.UsuarioServico;
import br.com.tcc.easywork.model.Usuarios;

public class SQLHelper extends SQLiteOpenHelper {

    private static SQLHelper instance;
    private static final String NOME_BANCO = "easyworkbd";
    private static final int VERSAO_BANCO = 1;
    private static final String TAG = "SQLHelper";

    private static final String CREATE_TbUsuarios = "CREATE TABLE \"TbUsuarios\" (\n" +
            "\t\"Usuario_id\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
            "\t\"Usuario_nome\"\tTEXT NOT NULL,\n" +
            "\t\"Usuario_login\"\tTEXT NOT NULL UNIQUE,\n" +
            "\t\"Usuario_senha\"\tTEXT NOT NULL,\n" +
            "\t\"Usuario_cpf\"\tINTEGER UNIQUE,\n" +
            "\t\"Usuario_ativo\"\tINTEGER,\n" +
            "\t\"Usuario_email\"\tTEXT,\n" +
            "\t\"Usuario_dataInclusao\"\tTEXT,\n" +
            "\t\"_deleted_\"\tINTEGER,\n" +
            "\t\"PerfilUsuario_id\"\tINTEGER,\n" +
            "\t\"Usuario_telefone\"\tINTEGER,\n" +
            "\tFOREIGN KEY(\"PerfilUsuario_id\") REFERENCES \"TbPerfilUsuario\"\n" +
            ");";

    private static final String CREATE_TbEndereco = "CREATE TABLE \"TbEndereco\" (\n" +
            "\t\"Endereco_id\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
            "\t\"Endereco_bairro\"\tTEXT NOT NULL,\n" +
            "\t\"Endereco_cidade\"\tTEXT NOT NULL,\n" +
            "\t\"Endereco_estado\"\tTEXT NOT NULL,\n" +
            "\t\"Usuario_id\"\tINTEGER,\n" +
            "\tFOREIGN KEY(\"Usuario_id\") REFERENCES \"TbUsuarios\"\n" +
            ");";

    private static final String CREATE_TbDadosAparelho = "CREATE TABLE \"TbDadosAparelho\" (\n" +
            "\t\"DadosAparelho_id\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
            "\t\"DadosAparelho_imei\"\tTEXT NOT NULL,\n" +
            "\t\"DadosAparelho_latitude\"\tREAL,\n" +
            "\t\"DadosAparelho_longitude\"\tREAL,\n" +
            "\t\"Usuario_id\"\tINTEGER,\n" +
            "\tFOREIGN KEY(\"Usuario_id\") REFERENCES \"TbUsuarios\"\n" +
            ");";

    private static final String CREATE_TbCategoriaServico = "CREATE TABLE \"TbCategoriaServicos\" (\n" +
            "\t\"CategoriaServicos_id\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
            "\t\"CategoriaServicos_Area\"\tTEXT,\n" +
            "\t\"CategoriaServicos_SubArea\"\tTEXT,\n" +
            "\t\"CategoriaServicos_Descricao\"\tTEXT\n" +
            ");";

    private static final String CREATE_TbServicos = "CREATE TABLE \"TbServicos\" (\n" +
            "\t\"Servicos_id\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
            "\t\"Servicos_descricao\"\tTEXT NOT NULL,\n" +
            "\t\"Servicos_DataInclusao\"\tTEXT NOT NULL,\n" +
            "\t\"Servicos_Prazo\"\tTEXT,\n" +
            "\t\"Servicos_Ativo\"\tINTEGER NOT NULL,\n" +
            "\t\"Servicos_Deletado\"\tINTEGER,\n" +
            "\t\"CategoriaServico_id\"\tINTEGER NOT NULL,\n" +
            "\tFOREIGN KEY(\"CategoriaServico_id\") REFERENCES \"TbCategoriaServicos\"\n" +
            ");";

    private static final String CREATE_TbPerfilUsuario = "CREATE TABLE \"TbPerfilUsuario\" (\n" +
            "\t\"PerfilUsuario_id\"\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
            "\t\"PerfilUsuario_descricao\"\tTEXT,\n" +
            "\t\"PerfilUsuario_ativo\"\tINTEGER,\n" +
            "\t\"PerfilUsuario_deletado\"\tINTEGER\n" +
            ");";

    private static final String CREATE_TbUsuarioServicos = "CREATE TABLE \"TbUsuarioServicos\" (\n" +
            "\t\"Usuario_id\"\tINTEGER NOT NULL,\n" +
            "\t\"Servicos_id\"\tINTEGER NOT NULL,\n" +
            "\tFOREIGN KEY(\"Servicos_id\") REFERENCES \"TbServicos\",\n" +
            "\tPRIMARY KEY(\"Servicos_id\",\"Usuario_id\"),\n" +
            "\tFOREIGN KEY(\"Usuario_id\") REFERENCES \"TbUsuarios\"\n" +
            ");";

    public SQLHelper(Context ctx) {
        super(ctx, NOME_BANCO, null, VERSAO_BANCO);
    }

    public static SQLHelper getInstance(Context ctx) {
        if (instance == null) {
            instance = new SQLHelper(ctx);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TbUsuarios);
            db.execSQL(CREATE_TbEndereco);
            db.execSQL(CREATE_TbDadosAparelho);
            db.execSQL(CREATE_TbCategoriaServico);
            db.execSQL(CREATE_TbServicos);
            db.execSQL(CREATE_TbPerfilUsuario);
            db.execSQL(CREATE_TbUsuarioServicos);

            Log.i(TAG, "Criou as tabelas do banco !");
        } catch (SQLException se) {
            Log.e(TAG, "OCORREU UM ERRO AO CRIAR AS TABELAS. " + se.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * Salva os registros no banco
     *
     * @param cv
     * @return insert
     */
    public long inserirCategoriaServico(ContentValues cv) {
        long retorno = 0;
        return retorno;
    }

    public long inserirDadosAparelho(ContentValues cv) {
        SQLiteDatabase db = this.getWritableDatabase();
        long insert = 0;
        try {
            insert = db.insert("TbDadosAparelho", null, cv);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return insert;
    }

    public long inserirEndereco(ContentValues cv) {
        SQLiteDatabase db = this.getWritableDatabase();
        long insert = 0;
        try {
            insert = db.insert("TbEndereco", null, cv);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return insert;
    }

    public long inserirPerfilUsuario(ContentValues cv) {
        long retorno = 0;
        return retorno;
    }

    public long inserirServicos(ContentValues cv) {
        long retorno = 0;
        return retorno;
    }

    public long inserirUsuario(ContentValues cv) {
        SQLiteDatabase db = this.getWritableDatabase();
        long insert = db.insert(Usuarios.getNomeTabela(), null, cv);
        return insert;
    }

    public long inserirUsuarioServico(ContentValues cv) {
        long retorno = 0;
        return retorno;
    }

    // SELECT
    public ArrayList<Usuarios> selectAllUsuariosNew() {
        String[] colunas = {"Usuario_id",
                "Usuario_nome",
                "Usuario_login",
                "Usuario_senha",
                "Usuario_cpf",
                "Usuario_ativo",
                "Usuario_email",
                "Usuario_dataInclusao",
                "_deleted_",
                "PerfilUsuario_id",
                "Usuario_telefone"
        };
        Cursor cursor = getWritableDatabase().query("TbUsuarios", colunas, null, null, null, null, null);

        //array que vai armazenar os registros
        ArrayList<Usuarios> listPessoa = new ArrayList<Usuarios>();

        //enquanto houver registros, adiciona a pessoa a listagem
        while (cursor.moveToNext()) {
            Usuarios usuarios = new Usuarios();
            usuarios.setId(cursor.getInt(0));
            usuarios.setNome(cursor.getString(1));
            usuarios.setLogin(cursor.getString(2));
            usuarios.setSenha(cursor.getString(3));
            usuarios.setCpf(cursor.getString(4));
            usuarios.setAtivo(cursor.getInt(5));
            usuarios.setEmail(cursor.getString(6));
            usuarios.setDataInclusao(cursor.getString(7));
            usuarios.setDeleted(cursor.getInt(8));
            usuarios.setIdPerfilUsuario(cursor.getInt(9));
            usuarios.setTelefone(cursor.getString(10));
            //adicionando o elemento completo na listagem:
            listPessoa.add(usuarios);
        }
        return listPessoa; //volta a listagem completa
    }
}
