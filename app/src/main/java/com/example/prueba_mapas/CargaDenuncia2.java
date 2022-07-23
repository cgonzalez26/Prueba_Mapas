package com.example.prueba_mapas;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.prueba_mapas.controllers.denuncias.DenunciaCallback;
import com.example.prueba_mapas.controllers.denuncias.DenunciasController;
import com.example.prueba_mapas.helpers.dialog.DialogHelper;
import com.example.prueba_mapas.models.denuncias.Denuncia;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class CargaDenuncia2 extends AppCompatActivity {
    Denuncia denuncia = new Denuncia();
    private Button boton;
    private EditText input;
    private String direccion;
    private List<Address> address;
    final String errorTitle = this.getString(R.string.text_error);
    final String accept = this.getString(R.string.button_accept);
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carga_denuncia2);

        Bundle parametros = this.getIntent().getExtras();
        String sNombre = parametros.getString("sNombre");
        String sApellido = parametros.getString("sApellido");
        String sNroDocumento = parametros.getString("sNroDocumento");
        String sMail = parametros.getString("sMail");
        String sTelefono = parametros.getString("sTelefono");
        String tRelato = parametros.getString("tRelato");
        String TipoDenunciaId = parametros.getString("TipoDenunciaId");
        //Log.d("datos: ",sNombre);
        denuncia.setId(UUID.randomUUID().toString());
        denuncia.setsNombre(sNombre);
        denuncia.setsApellido(sApellido);
        denuncia.setsNroDocumento(sNroDocumento);
        denuncia.setsMail(sMail);
        denuncia.setsTelefono(sTelefono);
        denuncia.settRelato(tRelato);
        denuncia.setTipoDenunciaId(TipoDenunciaId);

        /*mapa = (MapView)findViewById(R.id.mapa);
        controlMapa = mapa.getController();*/
        input = (EditText)findViewById(R.id.edDireccion);
        boton = (Button)findViewById(R.id.btnBuscar);

 //       mapa.setBuiltInZoomControls(true);
    }

    public void VolverDenuncia1(View vista){
        Intent intento = new Intent(this, CargaDenuncia1.class);
        startActivity(intento);
        finish();
    }

    public void cargarDenuncia(View vista){

        EditText edEntreCalles = (EditText) findViewById(R.id.edEntreCalles);
        EditText edDireccion = (EditText) findViewById(R.id.edDireccion);
        denuncia.setsEntre_Calle(edEntreCalles.getText().toString());
        denuncia.setsDireccion(edDireccion.getText().toString());
        denuncia.setsLatitud("-24.778021");
        denuncia.setsLongitud("-65.405800");

        DenunciasController.getInstance().cargarDenuncia(denuncia,new DenunciaCallback(){
            @Override
            public void onResponse(String id) {
                DialogHelper.dismissLoading();
                String message = "La Denuncia fue registrada correctamente";
                String title = "Carga de Denuncia";
                DialogHelper.info(CargaDenuncia2.this, title, message, accept, false, null);
            }


            @Override
            public void onError(Exception e) {
                //super.onError(e);
                DialogHelper.dismissLoading();
                String errorMessage = "Ocurrió un error guardando Denuncia: Detalle: " + (e != null ? e.getMessage() : "No disponible.");
                DialogHelper.error(CargaDenuncia2.this, errorTitle, errorMessage, accept, true, null);
            }
        });
    }

    public void buscarDireccion(View vista){
        direccion = input.getText().toString();
        //mMap = googleMap;

        if(direccion.equals("")){
            DialogHelper.error(CargaDenuncia2.this, errorTitle,"No hay dirección para buscar", accept, true, null);
        }else{
            //toast("Buscando \""+direccion+"\"");
            Geocoder coder = new Geocoder(getApplicationContext());

            try {
                address = coder.getFromLocationName(direccion, 1);
                Address location = address.get(0);
                int lat = (int) (location.getLatitude()*1E6);
                int lon = (int) (location.getLongitude()*1E6);
                LatLng latlong_buscado = new LatLng(lat, lon);
                mMap.addMarker(new MarkerOptions().position(latlong_buscado).title("Salta"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(latlong_buscado));
                //GeoPoint loc = new GeoPoint(lat,lon);
                //controlMapa.setCenter(loc);
                //controlMapa.setZoom(14);
            } catch (IOException e) {
                //toast("No se ha encontrado la dirección : (");
                DialogHelper.error(CargaDenuncia2.this, errorTitle,"No se ha encontrado la dirección", accept, true, null);
            }
        }
    }
}
