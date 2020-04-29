package br.com.tcc.easywork.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import java.io.IOException;
import java.util.List;

import br.com.tcc.easywork.R;
import br.com.tcc.easywork.model.DadosAparelho;
import br.com.tcc.easywork.model.Endereco;

public class DadosControler {

    private Location location; //devolve a coordenada do GPS
    private Context context;
    private static final String TAG = "DadosControler";

    private double _latitude = 0.0;
    private double _longitude = 0.0;

    private EditText edtCadBairro,edtCadEstado,edtCadCidade;
    private CheckBox cbUsaLocalizacao;

    public DadosControler(View view){
        this.inicializa(view);
    }

    public DadosControler(){}

    /**
     * Retorna a latitude e longitude do dispositivo, deve ser passado como parametro o contexto da activity
     *
     * @param pContext
     * @return Latitude e Longitude
     */
    public String retornaDadosLocalizacao(Context pContext){
        this.context = pContext;
        DadosAparelho dadosAparelho = new DadosAparelho();
        Auxiliadores auxiliadores = new Auxiliadores();
        Endereco _endereco = new Endereco();

        /**
         * Esta verificação é necessária a partir da API 23 do Android.
         * Mesmo que explicitamente declaradas no AndroidManifest, as
         * permissões que o usuário deve autorizar, na API 23 ele pode habilitar
         * ou desabilitar qualquer uma das permissões solicitadas.
         *
         * Logo, necessário fazer uma verificação se possui autorização
         **/
        try {
            if (ActivityCompat.checkSelfPermission((Activity) context, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                auxiliadores.alert_Simples(context,
                        "Atenção",
                        "Para garantir o bom funcionamento do aplicativo," +
                                "recomendamos ligar o GPS");
            } else {
                LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE); //devolve o provedor do GPS
                location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            }
        } catch (SecurityException se) {
            auxiliadores.criaToastLongo(context, "Ocorreu um erro, \n" + se.getMessage());
        }

        try{
            if (location != null) {
                _longitude = location.getLongitude();
                _latitude = location.getLatitude();
            }
            else if (location == null){
                dadosAparelho.setLatitude(0.0);
                dadosAparelho.setLongitude(0.0);
                auxiliadores.criaToastCurto(context, "Não foram obtidos dados do GPS");
            }
        }catch (Exception e){
            auxiliadores.criaToastLongo(context, "Ocorreu um erro, veja mais: \n" + e.getMessage());
        }

        dadosAparelho.setLatitude(_latitude);
        dadosAparelho.setLongitude(_longitude);

        try{
            if (dadosAparelho.getLatitude() == 0.0 && dadosAparelho.getLongitude() == 0.0){
                auxiliadores.criaToastCurto(context, "Não foram obtidos dados do GPS");
            }else{
                Address address = buscarEndereco(_latitude, _longitude); //Adress
                _endereco.setCidade(address.getSubAdminArea().toUpperCase());
                _endereco.setBairro(address.getSubLocality().toUpperCase());
                _endereco.setEstado(address.getAdminArea().toUpperCase()); //tratar exceção
            }
        }catch (IOException io){
            auxiliadores.criaToastLongo(context, "Ocorreu um erro, veja mais em:\n" + io.getStackTrace());
            Log.e(TAG,"Ocorreu um erro, veja mais em:\n" + io.getStackTrace());
        }

        if (cbUsaLocalizacao.isChecked()){
            edtCadBairro.setText(_endereco.getBairro());
            edtCadCidade.setText(_endereco.getCidade());
            edtCadEstado.setText(_endereco.getEstado());
        }

        return _endereco.getCidade() + _endereco.getEstado() + _endereco.getBairro();
    }

    public double retornaLatitude(){
        DadosAparelho dadosAparelho = new DadosAparelho();
        Auxiliadores auxiliadores = new Auxiliadores();
        Endereco _endereco = new Endereco();

        /**
         * Esta verificação é necessária a partir da API 23 do Android.
         * Mesmo que explicitamente declaradas no AndroidManifest, as
         * permissões que o usuário deve autorizar, na API 23 ele pode habilitar
         * ou desabilitar qualquer uma das permissões solicitadas.
         *
         * Logo, necessário fazer uma verificação se possui autorização
         **/
        try {
            if (ActivityCompat.checkSelfPermission((Activity) context, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                auxiliadores.alert_Simples(context,
                        "Atenção",
                        "Para garantir o bom funcionamento do aplicativo," +
                                "recomendamos ligar o GPS");
            } else {
                LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE); //devolve o provedor do GPS
                location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            }
        } catch (SecurityException se) {
            auxiliadores.criaToastLongo(context, "Ocorreu um erro, \n" + se.getMessage());
        }

        try{
            if (location != null) {
                _latitude = location.getLatitude();
            }
            else if (location == null){
                dadosAparelho.setLatitude(0.0);
                dadosAparelho.setLongitude(0.0);
                auxiliadores.criaToastCurto(context, "Não foram obtidos dados do GPS");
            }
        }catch (Exception e){
            auxiliadores.criaToastLongo(context, "Ocorreu um erro, veja mais: \n" + e.getMessage());
        }

        dadosAparelho.setLatitude(_latitude);

        return dadosAparelho.getLatitude();
    }

    public double retornaLongitude(){
        DadosAparelho dadosAparelho = new DadosAparelho();
        Auxiliadores auxiliadores = new Auxiliadores();

        /**
         * Esta verificação é necessária a partir da API 23 do Android.
         * Mesmo que explicitamente declaradas no AndroidManifest, as
         * permissões que o usuário deve autorizar, na API 23 ele pode habilitar
         * ou desabilitar qualquer uma das permissões solicitadas.
         *
         * Logo, necessário fazer uma verificação se possui autorização
         **/
        try {
            if (ActivityCompat.checkSelfPermission((Activity) context, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                auxiliadores.alert_Simples(context,
                        "Atenção",
                        "Para garantir o bom funcionamento do aplicativo," +
                                "recomendamos ligar o GPS");
            } else {
                LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE); //devolve o provedor do GPS
                location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            }
        } catch (SecurityException se) {
            auxiliadores.criaToastLongo(context, "Ocorreu um erro, \n" + se.getMessage());
        }

        try{
            if (location != null) {
                _longitude = location.getLongitude();
            }
            else if (location == null){
                dadosAparelho.setLatitude(0.0);
                dadosAparelho.setLongitude(0.0);
                auxiliadores.criaToastCurto(context, "Não foram obtidos dados do GPS");
            }
        }catch (Exception e){
            auxiliadores.criaToastLongo(context, "Ocorreu um erro, veja mais: \n" + e.getMessage());
        }

        dadosAparelho.setLongitude(_longitude);

        return dadosAparelho.getLongitude();
    }

    private Address buscarEndereco(double latitude, double longitude)
            throws IOException {
        Geocoder geocoder; //permite consultar a base de endereços do google
        Address address = null;
        List<Address> addresses;

        geocoder = new Geocoder((Activity) context);
        addresses = geocoder.getFromLocation(latitude, longitude, 1);
        if (addresses.size() > 0) {
            address = addresses.get(0);
        }

        return address;
    }

    public String retornaID(Context pContext) {
        this.context = pContext;
        String idAparelho = "0";
        TelephonyManager telephonyManager = (TelephonyManager) pContext.getSystemService(context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(pContext, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            //Tratar alerta de permissão de usuário
        } else {
            idAparelho = telephonyManager.getDeviceId();
        }
        return idAparelho;
    }

    private void inicializa(View view) {
        edtCadBairro = (EditText) view.findViewById(R.id.edtCadBairro);
        edtCadEstado = (EditText) view.findViewById(R.id.edtCadEstado);
        edtCadCidade = (EditText) view.findViewById(R.id.edtCadCidade);
        cbUsaLocalizacao = (CheckBox) view.findViewById(R.id.cbUsaLocalizacao);
    }

}
