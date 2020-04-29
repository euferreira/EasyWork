package br.com.tcc.easywork.model;

import java.io.Serializable;

public class Endereco implements Serializable {

    private static final String NOME_TABELA = "TbEndereco";
    private static final String Endereco_id = "Endereco_id";
    private static final String Endereco_bairro = "Endereco_bairro";
    private static final String Endereco_cidade = "Endereco_cidade";
    private static final String Endereco_estado = "Endereco_estado";
    private static final String Usuario_id = "Usuario_id";
    private int id;
    private String bairro;
    private String cidade;
    private String estado;
    private int idUsuario;

    public static String getNomeTabela() {
        return NOME_TABELA;
    }

    public static String getEndereco_id() {
        return Endereco_id;
    }

    public static String getEndereco_bairro() {
        return Endereco_bairro;
    }

    public static String getEndereco_cidade() {
        return Endereco_cidade;
    }

    public static String getEndereco_estado() {
        return Endereco_estado;
    }

    public static String getUsuario_id() {
        return Usuario_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
