package com.example.prueba_mapas;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.prueba_mapas.common.DbHelper;
import com.example.prueba_mapas.common.dbDenuncias;
import com.example.prueba_mapas.controllers.denuncias.DenunciaCallback;
import com.example.prueba_mapas.controllers.denuncias.DenunciasController;
import com.example.prueba_mapas.helpers.dialog.DialogCallback;
import com.example.prueba_mapas.helpers.dialog.DialogHelper;
import com.example.prueba_mapas.models.denuncias.Denuncia;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class CargaDenuncia2 extends AppCompatActivity implements OnMapReadyCallback {
    Denuncia denuncia = new Denuncia();

   /* current change
    private Button boton;
    private EditText input;
    private String direccion;
    private List<Address> address;
    final String errorTitle = this.getString(R.string.text_error);
    final String accept = this.getString(R.string.button_accept);
    private GoogleMap mMap;*/

    private EditText input;
    private String direccion;
    private Button boton;
    GoogleMap mMap = null;
    List<Address> addressList = null;
    Address address;
    DialogCallback dialogCallback;
    //final String errorTitle = this.getString(R.string.text_error);
    //final String accept = this.getString(R.string.button_accept);


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
        controlMapa = mapa.getController();
        input = (EditText)findViewById(R.id.edDireccion);
        boton = (Button)findViewById(R.id.btnBuscar);*/
        
        initMap();
        //input = (EditText)findViewById(R.id.edDireccion);
        //boton = (Button)findViewById(R.id.btnBuscar);
    }

    private void initMap() {
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(CargaDenuncia2.this);
    }

    public void VolverDenuncia1(View vista){
        Intent intento = new Intent(this, CargaDenuncia1.class);
        startActivity(intento);
        finish();
    }

    public void VolverInicial(View vista){
        Intent intento = new Intent(this, MainActivity.class);
        startActivity(intento);
        finish();
    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

    public void cargarDenuncia(View vista){

        EditText edEntreCalles = (EditText) findViewById(R.id.edEntreCalles);
        EditText edDireccion = (EditText) findViewById(R.id.edDireccion);
        double lat = address.getLatitude();
        double lon = address.getLongitude();
        denuncia.setsEntre_Calle(edEntreCalles.getText().toString());
        denuncia.setsDireccion(edDireccion.getText().toString());
        denuncia.setsLatitud(Double.toString(lat));
        denuncia.setsLongitud(Double.toString(lon));
        String insertedAt = getDateTime();

        //Guardamos en la BD SQLITE
        /*DbHelper dbHelper = new DbHelper(getApplicationContext()); //CargaDenuncia2.this
        SQLiteDatabase db_denuncias = dbHelper.getWritableDatabase();

        dbHelper.registrar(denuncia.getId(),denuncia.getsNroDocumento(),denuncia.getsNombre(),denuncia.getsApellido(),
                denuncia.getsMail(),denuncia.getsTelefono(),denuncia.getsDireccion(),denuncia.getsEntre_Calle(),
                denuncia.getsLongitud(),denuncia.getsLatitud(),denuncia.gettRelato(),denuncia.getTipoDenunciaId(),
                insertedAt);*/
        dbDenuncias db = new dbDenuncias(CargaDenuncia2.this);
        long id = db.registrarDenuncia(denuncia.getsNroDocumento(),denuncia.getsNombre(),denuncia.getsApellido(),
                denuncia.getsMail(),denuncia.getsTelefono(),denuncia.getsDireccion(),denuncia.getsEntre_Calle(),
                denuncia.getsLongitud(),denuncia.getsLatitud(),denuncia.gettRelato(),denuncia.getTipoDenunciaId(),
                insertedAt);
        DialogHelper.dismissLoading();
        String accept = "Aceptar";
        if(id > 0) {
            String message = "La Denuncia fue registrada correctamente";
            String title = "Carga de Denuncia";
            DialogHelper.info(CargaDenuncia2.this, title, message, accept, false, new DialogCallback() {
                @Override
                public void onPositive() {
                    super.onPositive();
                    VolverInicial(vista);
                }
                @Override
                public void onNegative() {
                    super.onNegative();
                }
            });
        }else{
            String errorMessage = "Ocurrió un error guardando Denuncia";
            String errorTitle = "Error";
            DialogHelper.error(CargaDenuncia2.this, errorTitle, errorMessage, accept, true, null);
        }
        //Guardamos en la BD utiliazando la API
        /*DenunciasController.getInstance().cargarDenuncia(denuncia,new DenunciaCallback(){
            @Override
            public void onResponse(String id) {
                DialogHelper.dismissLoading();
                String message = "La Denuncia fue registrada correctamente";
                String title = "Carga de Denuncia";

                //DialogHelper.info(CargaDenuncia2.this, title, message, accept, false, dialogCallback);
                DialogHelper.info(CargaDenuncia2.this, title, message, accept, false, new DialogCallback() {
                    @Override
                    public void onPositive() {
                        super.onPositive();
                        VolverInicial(vista);
                    }

                    @Override
                    public void onNegative() {
                        super.onNegative();
                    }
                });
            }

            @Override
            public void onError(Exception e) {
                //super.onError(e);
                DialogHelper.dismissLoading();
                String errorMessage = "Ocurrió un error guardando Denuncia: Detalle: " + (e != null ? e.getMessage() : "No disponible.");
                DialogHelper.error(CargaDenuncia2.this, errorTitle, errorMessage, accept, true, null);
            }
        });*/
    }

    public void buscarDireccion(View vista){
        input = (EditText)findViewById(R.id.edDireccion);
        direccion = input.getText().toString();
        MarkerOptions userMarkerOption = new MarkerOptions();
        if(direccion.equals("")){
            DialogHelper.error(CargaDenuncia2.this, "Error","No hay dirección para buscar", "Aceptar", true, null);
        }else{
            Geocoder coder = new Geocoder(getApplicationContext());
            try {
                addressList = coder.getFromLocationName(direccion, 1);
                if( addressList != null) {
                    for (int i = 0; i < addressList.size(); i++) {
                        address = addressList.get(i);
                        LatLng latlong_buscado = new LatLng(address.getLatitude(), address.getLongitude());
                        userMarkerOption.position(latlong_buscado);
                        userMarkerOption.title("Ubicación de la Denuncia");
                        userMarkerOption.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));

                        mMap.addMarker(userMarkerOption);
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(latlong_buscado));
                        mMap.animateCamera(CameraUpdateFactory.zoomTo(14));
                    }
                }
            } catch (IOException e) {
                DialogHelper.error(CargaDenuncia2.this, "Error","No se ha encontrado la dirección", "Aceptar", true, null);
            }
        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.setMinZoomPreference(7);
        mMap.setMaxZoomPreference(21);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);

        /*if (lat == null || lng == null) {
            save.setEnabled(false);
            setDefaultLocation();
        }
        if (lat != null && lng != null) {
            setMarker(Float.parseFloat(lat), Float.parseFloat(lng), "Mi posiciòn");
        }*/

        LatLng salta = new LatLng(-24.7892135, -65.4102839);
        //mMap.addMarker(new MarkerOptions().position(salta).title("Salta Capital"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(salta));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(14));
    }
}
