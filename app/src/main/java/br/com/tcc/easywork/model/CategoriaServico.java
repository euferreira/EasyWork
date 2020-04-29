package br.com.tcc.easywork.model;

import java.io.Serializable;

public class CategoriaServico implements Serializable {

    private int id;
    private String area;
    private String subarea;
    private String descricao;
    private static final String NOME_TABELA = "TbCategoriaServicos";
    private static final String CategoriaServicos_id = "CategoriaServicos_id";
    private static final String CategoriaServicos_Area = "CategoriaServicos_Area";
    private static final String CategoriaServicos_SubArea = "CategoriaServicos_SubArea";
    private static final String CategoriaServicos_Descricao = "CategoriaServicos_Descricao";

    public void setId(int id) {
        this.id = id;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setSubarea(String subarea) {
        this.subarea = subarea;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public String getArea() {
        return area;
    }

    public String getSubarea() {
        return subarea;
    }

    public String getDescricao() {
        return descricao;
    }

    public static String getNomeTabela() {
        return NOME_TABELA;
    }

    public static String getCategoriaServicos_id() {
        return CategoriaServicos_id;
    }

    public static String getCategoriaServicos_Area() {
        return CategoriaServicos_Area;
    }

    public static String getCategoriaServicos_SubArea() {
        return CategoriaServicos_SubArea;
    }

    public static String getCategoriaServicos_Descricao() {
        return CategoriaServicos_Descricao;
    }

    @Override
    public String toString() {
        return "- " + this.area + "\n- " + this.subarea + " \n- " + this.descricao;
    }
}
