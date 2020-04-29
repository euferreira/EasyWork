package br.com.tcc.easywork.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.tcc.easywork.DAO.SQLHelper;
import br.com.tcc.easywork.R;
import br.com.tcc.easywork.model.Usuarios;
import br.com.tcc.easywork.util.DadosControler;

public class MainActivity extends AppCompatActivity implements
        View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private DadosControler dadosControler;
    private ArrayList<Usuarios> arrayLisUsuarios;
    private ArrayAdapter<Usuarios> arrayAdapterUsuarios;
    private SQLHelper sqlHelper;
    private static final String TAG = "MainActivity";
    private static final String ARQUIVO_PREFERENCIA = "MinhaPreferencia";

    private EditText edtLogin;
    private EditText edtSenha;
    private CheckBox cbLembrar;
    private Button btnLogar;
    private TextView txtEsqueciSenha;
    private TextView txtNovoCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.inicializarComponentes();


        cbLembrar.setOnCheckedChangeListener(this);
        btnLogar.setOnClickListener(this);
        txtNovoCadastro.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //popularListaPessoa();
    }

    @Override
    public void onClick(View v) {
        try {
            //EVENTOS DE CLICK DO BOTÃO DE LOGIN
            if (v.getId() == btnLogar.getId()) {
                consultaUsuarios();
            }
            else if (v.getId() == txtEsqueciSenha.getId()) {

            }
            else if (v.getId() == txtNovoCadastro.getId()) {
                Intent formCadastro = new Intent(MainActivity.this, FormCadEdicao.class);
                startActivity(formCadastro);
                //Abre o formulario de cadastro;
            }
        } catch (Exception e) {
            Toast.makeText(this, "ERRO: \n" + e, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) { //TRUE para selecionado
            SharedPreferences sharedPreferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            if (edtLogin.getText().toString().equals("") || edtSenha.getText().toString().equals("")){
                Log.i(TAG, "Não possui dados para salvar");
            }else{
                editor.putString("login", edtLogin.getText().toString());
                editor.putString("senha", edtSenha.getText().toString());
                editor.commit();

                edtLogin.setText(edtLogin.getText().toString());
                edtSenha.setText(edtSenha.getText().toString());
            }

            if (sharedPreferences.contains("login") && sharedPreferences.contains("senha")){
                String usuarioDigitado = edtLogin.getText().toString();
                String senhaDigitada = edtSenha.getText().toString();
                String nomeUsuario = sharedPreferences.getString("login", usuarioDigitado);
                String senhaUsuario = sharedPreferences.getString("senha", senhaDigitada);

                edtLogin.setText(nomeUsuario);
                edtSenha.setText(senhaUsuario);
                cbLembrar.setActivated(true);

            }else{

            }

        }else{ //FALSE para desmarcado
        }
    }

    //Método para inicializar os componentes
    private void inicializarComponentes() {
        this.edtLogin = findViewById(R.id.edtLogin);
        this.edtSenha = findViewById(R.id.edtSenha);
        this.cbLembrar = findViewById(R.id.cbLembrar);
        this.btnLogar = findViewById(R.id.btnLogar);
        this.txtEsqueciSenha = findViewById(R.id.txtEsqueciSenha);
        this.txtNovoCadastro = findViewById(R.id.txtNovoCadastro);
    }

    //fazendo a consulta e populando a lista
    //Método necessário para validação de LOGIN e SENHA
    protected void popularListaPessoa() {
        sqlHelper = new SQLHelper(MainActivity.this); //instanciando
        arrayLisUsuarios = sqlHelper.selectAllUsuariosNew();//atribuindo à listagem o resultado do select
        sqlHelper.close();
    }

    protected ArrayList<String> consultaUsuarios() {
        ArrayList<String> listaDeLogin = new ArrayList<String>();
        String usuarioDigitado = edtLogin.getText().toString();
        String senhaDigitada = edtSenha.getText().toString();

        popularListaPessoa();

        int tam = arrayLisUsuarios.size(); //Verifica o tamanho da lista

        if (tam < 0) {
            Log.i(TAG, "Não existem dados na lista");
        } else {
            //se entrou aqui é porque encontrou dados na lista
            try {
                for (int i = 0; i < tam; i++) {
                    listaDeLogin.add(arrayLisUsuarios.get(i).getLogin());
                    listaDeLogin.add(arrayLisUsuarios.get(i).getSenha());
                    //percorre a lista trazendo os logins existentes
                }
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
            }

            //Se o usuário digitar vazio, exibe mensagem de erro
            if (usuarioDigitado.equals("") || senhaDigitada.equals("") ||
                    usuarioDigitado.isEmpty() || senhaDigitada.isEmpty()) {
                Log.e(TAG, "ERRO");

                if (usuarioDigitado.equals("")) {
                    edtLogin.setError("Verifique o campo !");
                    edtLogin.setFocusable(true);
                } else if (senhaDigitada.equals("")) {
                    edtSenha.setError("Verifique o campo !");
                    edtLogin.setFocusable(true);
                }
            } else {
                if (listaDeLogin.contains(usuarioDigitado) || listaDeLogin.contains(senhaDigitada)) {
                    Log.d(TAG, "Usuário Validado");
                    Toast.makeText(this, "Usuário validado", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, TelaPrincipal.class);
                    startActivity(intent);
                } else {
                    Log.e(TAG, "Usuário não está na lista.");
                    Toast.makeText(this, "Usuário não validado", Toast.LENGTH_SHORT).show();
                }
            }
        }
        return listaDeLogin;
    }

}
