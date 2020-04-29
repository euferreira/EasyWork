package br.com.tcc.easywork.model;

import java.io.Serializable;

public class PerfilUsuario implements Serializable {

    private static final String NOME_TABELA = "TbPerfilUsuario";
    private static final String PerfilUsuario_id = "PerfilUsuario_id";
    private static final String PerfilUsuario_descricao = "PerfilUsuario_descricao";
    private static final String PerfilUsuario_ativo = "PerfilUsuario_ativo";
    private static final String PerfilUsuario_deletado = "PerfilUsuario_deletado";
    private int id;
    private String descricao;
    private int ativo;
    private int deletado;

    public static String getNomeTabela() {
        return NOME_TABELA;
    }

    public static String getPerfilUsuario_id() {
        return PerfilUsuario_id;
    }

    public static String getPerfilUsuario_descricao() {
        return PerfilUsuario_descricao;
    }

    public static String getPerfilUsuario_ativo() {
        return PerfilUsuario_ativo;
    }

    public static String getPerfilUsuario_deletado() {
        return PerfilUsuario_deletado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getAtivo() {
        return ativo;
    }

    public void setAtivo(int ativo) {
        this.ativo = ativo;
    }

    public int getDeletado() {
        return deletado;
    }

    public void setDeletado(int deletado) {
        this.deletado = deletado;
    }
}
