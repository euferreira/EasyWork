package br.com.tcc.easywork.model;

import java.io.Serializable;

public class DadosAparelho implements Serializable {

    private int id;
    private String imei;
    private double latitude;
    private double longitude;
    private int Usuario_ID;
    private static final String NOME_TABELA = "TbDadosAparelho";
    private static final String DadosAparelho_id = "DadosAparelho_id";
    private static final String DadosAparelho_imei = "DadosAparelho_imei";
    private static final String DadosAparelho_latitude = "DadosAparelho_latitude";
    private static final String DadosAparelho_longitude = "DadosAparelho_longitude";
    private static final String Usuario_id = "Usuario_id";

    public static String getDadosAparelho_id() {
        return DadosAparelho_id;
    }

    public static String getDadosAparelho_imei() {
        return DadosAparelho_imei;
    }

    public static String getDadosAparelho_latitude() {
        return DadosAparelho_latitude;
    }

    public static String getDadosAparelho_longitude() {
        return DadosAparelho_longitude;
    }

    public static String getUsuario_id() {
        return Usuario_id;
    }

    public static String getNomeTabela() {
        return NOME_TABELA;
    }

    @Override
    public String toString() {
        return "- " + this.getImei() + "\n- " + this.getLatitude() + " \n- " + this.getLongitude();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getUsuario_ID() {
        return Usuario_ID;
    }

    public void setUsuario_ID(int usuario_ID) {
        Usuario_ID = usuario_ID;
    }

}