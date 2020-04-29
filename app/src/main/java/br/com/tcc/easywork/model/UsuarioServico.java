package br.com.tcc.easywork.model;

import java.io.Serializable;

public class UsuarioServico implements Serializable {

    private static final String TbUsuarioServicos = "TbUsuarioServicos";
    private static final String Usuario_id = "Usuario_id";
    private static final String Servicos_id = "Servicos_id";
    private int idUsuario;
    private int idServicos;

    public static String getTbUsuarioServicos() {
        return TbUsuarioServicos;
    }

    public static String getUsuario_id() {
        return Usuario_id;
    }

    public static String getServicos_id() {
        return Servicos_id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdServicos() {
        return idServicos;
    }

    public void setIdServicos(int idServicos) {
        this.idServicos = idServicos;
    }
}
