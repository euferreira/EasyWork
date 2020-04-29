package br.com.tcc.easywork.model;

import java.io.Serializable;

public class Servicos implements Serializable {

    private static final String NOME_TABELA = "TbServicos";
    private static final String Servicos_id = "Servicos_id";
    private static final String Servicos_descricao = "Servicos_descricao";
    private static final String Servicos_DataInclusao = "Servicos_DataInclusao";
    private static final String Servicos_Prazo = "Servicos_Prazo";
    private static final String Servicos_Ativo = "Servicos_Ativo";
    private static final String Servicos_Deletado = "Servicos_Deletado";
    private static final String CategoriaServico_id = "CategoriaServico_id";
    private int id;
    private String descricao;
    private String dataInclusao;
    private String prazo;
    private int ativo;
    private int deletado;
    private int idCategoriaServico;

    public static String getNomeTabela() {
        return NOME_TABELA;
    }

    public static String getServicos_id() {
        return Servicos_id;
    }

    public static String getServicos_descricao() {
        return Servicos_descricao;
    }

    public static String getServicos_DataInclusao() {
        return Servicos_DataInclusao;
    }

    public static String getServicos_Prazo() {
        return Servicos_Prazo;
    }

    public static String getServicos_Ativo() {
        return Servicos_Ativo;
    }

    public static String getServicos_Deletado() {
        return Servicos_Deletado;
    }

    public static String getCategoriaServico_id() {
        return CategoriaServico_id;
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

    public String getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(String dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    public String getPrazo() {
        return prazo;
    }

    public void setPrazo(String prazo) {
        this.prazo = prazo;
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

    public int getIdCategoriaServico() {
        return idCategoriaServico;
    }

    public void setIdCategoriaServico(int idCategoriaServico) {
        this.idCategoriaServico = idCategoriaServico;
    }
}
