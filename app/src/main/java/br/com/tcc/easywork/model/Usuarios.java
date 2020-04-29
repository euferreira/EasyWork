package br.com.tcc.easywork.model;

import java.io.Serializable;

public class Usuarios implements Serializable {

    private static final String NOME_TABELA = "TbUsuarios";
    private static final String Usuario_id = "Usuario_id";
    private static final String Usuario_nome = "Usuario_nome";
    private static final String Usuario_login = "Usuario_login";
    private static final String Usuario_senha = "Usuario_senha";
    private static final String Usuario_cpf = "Usuario_cpf";
    private static final String Usuario_ativo = "Usuario_ativo";
    private static final String Usuario_email = "Usuario_email";
    private static final String Usuario_dataInclusao = "Usuario_dataInclusao";
    private static final String _deleted_ = "_deleted_";
    private static final String PerfilUsuario_id = "PerfilUsuario_id";
    private static final String Usuario_telefone = "Usuario_telefone";
    private int id;
    private String nome;
    private String login;
    private String senha;
    private String cpf;
    private int ativo;
    private String email;
    private String dataInclusao;
    private int deleted;
    private int idPerfilUsuario;
    private String telefone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getAtivo() {
        return ativo;
    }

    public void setAtivo(int ativo) {
        this.ativo = ativo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(String dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public int getIdPerfilUsuario() {
        return idPerfilUsuario;
    }

    public void setIdPerfilUsuario(int idPerfilUsuario) {
        this.idPerfilUsuario = idPerfilUsuario;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public static String getNomeTabela() {
        return NOME_TABELA;
    }

    public static String getUsuario_id() {
        return Usuario_id;
    }

    public static String getUsuario_nome() {
        return Usuario_nome;
    }

    public static String getUsuario_login() {
        return Usuario_login;
    }

    public static String getUsuario_senha() {
        return Usuario_senha;
    }

    public static String getUsuario_cpf() {
        return Usuario_cpf;
    }

    public static String getUsuario_ativo() {
        return Usuario_ativo;
    }

    public static String getUsuario_email() {
        return Usuario_email;
    }

    public static String getUsuario_dataInclusao() {
        return Usuario_dataInclusao;
    }

    public static String get_deleted_() {
        return _deleted_;
    }

    public static String getPerfilUsuario_id() {
        return PerfilUsuario_id;
    }

    public static String getUsuario_telefone() {
        return Usuario_telefone;
    }
}
