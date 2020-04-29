package br.com.tcc.easywork.fragments;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.core.Tag;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import br.com.tcc.easywork.DAO.SQLHelper;
import br.com.tcc.easywork.R;
import br.com.tcc.easywork.model.DadosAparelho;
import br.com.tcc.easywork.model.Endereco;
import br.com.tcc.easywork.model.PerfilUsuario;
import br.com.tcc.easywork.model.Usuarios;
import br.com.tcc.easywork.util.Auxiliadores;
import br.com.tcc.easywork.util.DadosControler;
import br.com.tcc.easywork.view.FormCadEdicao;

public class TabFragmentEndereco extends Fragment
        implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private static String ARG_POSITION = "arg_position";
    private EditText edtCadBairro, edtCadEstado, edtCadCidade;
    private EditText edtCadNome, edtCadCPF, edtCadEmail;
    private EditText edtCadTelefone, edtCadLogin, edtCadSenha;
    private CheckBox cbUsaLocalizacao;
    private FloatingActionButton fab;
    private ContentValues valuesUsuario;
    private ContentValues valuesEndereco;
    private ContentValues valuesPerfilUsuario;
    private Endereco enderecoHandler;
    private static final String TAG = "TabFragmentEndereco";

    public TabFragmentEndereco() {
    }

    public static TabFragmentEndereco novaInstancia(int posicao) {
        Bundle parametros = new Bundle();
        parametros.putInt(ARG_POSITION, posicao);

        TabFragmentEndereco frag = new TabFragmentEndereco();
        frag.setArguments(parametros);
        return frag;
    }

    private void inicializa(View view) {
        edtCadBairro = (EditText) view.findViewById(R.id.edtCadBairro);
        edtCadEstado = (EditText) view.findViewById(R.id.edtCadEstado);
        edtCadCidade = (EditText) view.findViewById(R.id.edtCadCidade);
        cbUsaLocalizacao = (CheckBox) view.findViewById(R.id.cbUsaLocalizacao);
        fab = (FloatingActionButton) view.findViewById(R.id.fab);

        edtCadNome = getActivity().findViewById(R.id.edtCadNome);
        edtCadCPF = getActivity().findViewById(R.id.edtCadCPF);
        edtCadEmail = getActivity().findViewById(R.id.edtCadEmail);
        edtCadTelefone = getActivity().findViewById(R.id.edtCadTelefone);
        edtCadLogin = getActivity().findViewById(R.id.edtCadLogin);
        edtCadSenha = getActivity().findViewById(R.id.edtCadSenha);
    }

    private void insertUsuario() {
        String nome = edtCadNome.getText().toString();
        String login = edtCadLogin.getText().toString();
        String senha = edtCadSenha.getText().toString();
        String CPF = edtCadCPF.getText().toString();
        String email = edtCadEmail.getText().toString();
        String telefone = edtCadTelefone.getText().toString();

        if (nome.equals("") || nome.isEmpty()) {
            edtCadNome.setError("Verifique o campo !");
        } else if (login.equals("") || login.isEmpty()) {
            edtCadLogin.setError("Verifique o campo !");
        } else if (senha.equals("") || senha.isEmpty()) {
            edtCadLogin.setError("Verifique o campo !");
        } else if (CPF.equals("") || CPF.isEmpty()) {
            edtCadCPF.setError("Verifique o campo !");
        }

        try {
            Usuarios usuarios = new Usuarios();

            usuarios.setNome(nome);
            usuarios.setLogin(login);
            usuarios.setSenha(senha);
            usuarios.setCpf(CPF);
            usuarios.setAtivo(0); //usuario ativo, 0 - ativo, 1 - inativo
            usuarios.setEmail(email);
            Date date = new Date();
            SimpleDateFormat formataData = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            String dataFormatada = formataData.format(date);
            usuarios.setDataInclusao(dataFormatada); //Inclui a data e hora do cadastro
            usuarios.setDeleted(0); //deletado, 0 - ativo, 1 - inativo
            usuarios.setTelefone(telefone);

            //Verifica primeiro se existe um login na lista, para depois cadastrar
            SQLHelper sqlHelper = new SQLHelper(getActivity());
            ArrayList<Usuarios> arrayListUsuario = sqlHelper.selectAllUsuariosNew(); //retorna o total de registro de usuários
            ArrayList<String> arrayListLogin = new ArrayList<String>(); //vai retornar os logins existentes em arrayListUsuario
            int tamanhoListaUsuarios = arrayListUsuario.size();
            if (tamanhoListaUsuarios > 0) {
                //Se entrar, é porque tem registro
                try {
                    for (int i = 0; i < tamanhoListaUsuarios; i++) {
                        arrayListLogin.add(arrayListUsuario.get(i).getLogin());
                        //percorre a lista trazendo os logins existentes
                    }
                } catch (IndexOutOfBoundsException e) {
                    e.printStackTrace();
                }

                //Verifica na lista se contém o login digitado,
                //Se sim, retorna pro usuário.
                if (arrayListLogin.contains(login)) {
                    Log.e("", "Login já cadastrado !");
                    edtCadLogin.setError("Nome de login já cadastrado !");
                    edtCadLogin.setFocusable(true);
                }
                //Se não tiver, adiciona na tabela
                else {
                    try {
                        valuesUsuario = new ContentValues();
                        valuesUsuario.put("Usuario_nome", usuarios.getNome());
                        valuesUsuario.put("Usuario_login", usuarios.getLogin());
                        valuesUsuario.put("Usuario_senha", usuarios.getSenha());
                        valuesUsuario.put("Usuario_cpf", usuarios.getCpf());
                        valuesUsuario.put("Usuario_ativo", usuarios.getAtivo());
                        valuesUsuario.put("Usuario_email", usuarios.getEmail());
                        valuesUsuario.put("Usuario_dataInclusao", usuarios.getDataInclusao());
                        valuesUsuario.put("_deleted_", usuarios.getDeleted());
                        //valuesUsuario.put("PerfilUsuario_id", 1);
                        valuesUsuario.put("Usuario_telefone", usuarios.getTelefone());

                        //Retorna do banco o insert, se for positivo, prossegue
                        if (sqlHelper.inserirUsuario(valuesUsuario) > 0) {
                            Log.i(TAG, "Gravou os endereços");
                            Toast.makeText(getActivity(), "GRAVOU USUARIOS", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insertEndereco() {
        String bairro = edtCadBairro.getText().toString();
        String cidade = edtCadCidade.getText().toString();
        String estado = edtCadEstado.getText().toString();

        if (bairro.equals("") || bairro.isEmpty()){
            edtCadBairro.setError("Verifique o campo !");
        }
        else if (cidade.equals("") || bairro.isEmpty()){
            edtCadCidade.setError("Verifique o campo !");
        }
        else if (estado.equals("") || estado.isEmpty()){
            edtCadEstado.setError("Verifique o campo !");
        }

        try {
            valuesEndereco = new ContentValues();
            Endereco enderecoUsuario = new Endereco();
            enderecoUsuario.setBairro(bairro);
            enderecoUsuario.setCidade(cidade);
            enderecoUsuario.setEstado(estado);

            ArrayList<Integer> arrayListId = new ArrayList<>();
            SQLHelper sqlHelper = new SQLHelper(getActivity());
            ArrayList<Usuarios> arrayListUsuario = sqlHelper.selectAllUsuariosNew();
            int tamListaUsuario = arrayListUsuario.size();

            for (int i = 0; i < tamListaUsuario; i++) {
                arrayListId.add(arrayListUsuario.get(i).getId());
            }
            if (arrayListId.size() > 0) {
                int lista = arrayListId.get(arrayListId.size() - 1);
                Toast.makeText(getActivity(),
                        "Ultimo ID salvo : " + lista
                                + "LISTA DE ID: " + tamListaUsuario,
                        Toast.LENGTH_LONG).show();
                enderecoUsuario.setIdUsuario(lista);
            }

            valuesEndereco.put("Endereco_bairro", enderecoUsuario.getBairro());
            valuesEndereco.put("Endereco_cidade", enderecoUsuario.getCidade());
            valuesEndereco.put("Usuario_id", enderecoUsuario.getIdUsuario());
            valuesEndereco.put("Endereco_estado", enderecoUsuario.getEstado());

            if (sqlHelper.inserirEndereco(valuesEndereco) > 0) {
                Toast.makeText(getActivity(), "Foi !", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insertDadosAparelho(){
        DadosControler dadosControler = new DadosControler();
        DadosAparelho dadosAparelho = new DadosAparelho();

        String dadosImei = dadosControler.retornaID(getActivity());
        double latitude = dadosControler.retornaLatitude();
        double longitude = dadosControler.retornaLongitude();

        Toast.makeText(getActivity(),
                "IMEI: " + dadosImei + "\n" +
                        "Latitude: " + latitude + "\n" +
                        "Longitude: " + longitude,
                Toast.LENGTH_SHORT).show();

        dadosAparelho.setLatitude(latitude);
        dadosAparelho.setLongitude(longitude);
        dadosAparelho.setImei(dadosImei);

        ArrayList<Integer> arrayListId = new ArrayList<>();
        SQLHelper sqlHelper = new SQLHelper(getActivity());
        ArrayList<Usuarios> arrayListUsuario = sqlHelper.selectAllUsuariosNew();
        int tamListaUsuario = arrayListUsuario.size();

        for (int i = 0; i < tamListaUsuario; i++) {
            arrayListId.add(arrayListUsuario.get(i).getId());
        }
        if (arrayListId.size() > 0) {
            int lista = arrayListId.get(arrayListId.size() - 1);
            Toast.makeText(getActivity(),
                    "Ultimo ID salvo : " + lista
                            + "LISTA DE ID: " + tamListaUsuario,
                    Toast.LENGTH_LONG).show();
            dadosAparelho.setUsuario_ID(lista);
        }

        ContentValues contentValues = new ContentValues();
        contentValues.put("DadosAparelho_imei", dadosAparelho.getImei());
        contentValues.put("DadosAparelho_latitude", dadosAparelho.getLatitude());
        contentValues.put("DadosAparelho_longitude", dadosAparelho.getLongitude());
        contentValues.put("Usuario_id", dadosAparelho.getUsuario_ID());

        if (sqlHelper.inserirDadosAparelho(contentValues) > 0){
            Toast.makeText(getActivity(), "CADASTRADO", Toast.LENGTH_LONG).show();
            AlertDialog alertDialog;
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Cadastro realizado !");
            builder.setMessage("Seu cadastro foi realizado com sucesso !");
            builder.setPositiveButton("Voltar a tela de login", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    getActivity().finish();
                }
            });
            alertDialog = builder.create();
            alertDialog.show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceBundle) {
        View fragView = inflater.inflate(R.layout.fragment_endereco, container, false);
        inicializa(fragView);
        fab.setOnClickListener(this);
        cbUsaLocalizacao.setOnCheckedChangeListener(this);
        return fragView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onClick(View v) {
        try {
            if (v.getId() == R.id.fab) {
                insertUsuario();
                insertEndereco();
                insertDadosAparelho();
            }
        } catch (Exception e) {
            Log.e(TAG, "ERRO: " + e.getMessage());
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (cbUsaLocalizacao.isChecked()) {

            DadosControler dadosControler = new DadosControler(getView());
            dadosControler.retornaDadosLocalizacao(getActivity());

        }
    }
}