package br.com.tcc.easywork.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

public class Auxiliadores {

    private AlertDialog alerta;

    public void criaToastLongo(Context context,String mensagem)
    {
        Toast.makeText(context, mensagem, Toast.LENGTH_LONG).show();
    }

    public void criaToastCurto(Context context,String mensagem)
    {
        Toast.makeText(context, mensagem, Toast.LENGTH_SHORT).show();
    }

    public void alert_Simples(final Context context, String tituloDialog, String mensagemDialog)
    {
        //Cria o gerador do AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        //define o titulo
        builder.setTitle(tituloDialog);
        //define a mensagem
        builder.setMessage(mensagemDialog);
        //define um botão como positivo
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                //Toast.makeText(context, "positivo=" + arg1, Toast.LENGTH_SHORT).show();
            }
        });
        //define um botão como negativo.
        builder.setNegativeButton("Sair", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                //Toast.makeText(context, "negativo=" + arg1, Toast.LENGTH_SHORT).show();
            }
        });
        //cria o AlertDialog
        alerta = builder.create();
        //Exibe
        alerta.show();
    }


}
