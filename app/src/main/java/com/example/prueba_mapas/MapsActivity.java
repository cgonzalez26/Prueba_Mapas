package com.example.prueba_mapas;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
//import android.location.LocationListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.example.prueba_mapas.helpers.dialog.DialogHelper;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.location.LocationListener;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements
        OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    private GoogleMap mMap;
    private EditText input;
    private String direccion;
    private Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            mMap.setMyLocationEnabled(true);
        }


        // Add a marker in Sydney and move the camera
/*
        LatLng sydney = new LatLng(-24.78237664, -65.41413300);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Salta"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
*/
    }

    public void buscarDireccion(View vista){
        input = (EditText)findViewById(R.id.edDireccion);
        direccion = input.getText().toString();
        MarkerOptions userMarkerOption = new MarkerOptions();
        if(direccion.equals("")){
            DialogHelper.error(MapsActivity.this, "Error","No hay dirección para buscar", "Aceptar", true, null);
        }else{
            Geocoder coder = new Geocoder(getApplicationContext());
            List<Address> addressList = null;
            Address address;
            //DialogHelper.info(CargaDenuncia2.this, "Entro", "VA bien", "Aceptar", false, null);
            try {
                addressList = coder.getFromLocationName(direccion, 4);
                if( addressList != null) {
                    for (int i = 0; i < addressList.size(); i++) {
                        address = addressList.get(i);
                        int lat = (int) (address.getLatitude() * 1E6);
                        int lon = (int) (address.getLongitude() * 1E6);
                        LatLng latlong_buscado = new LatLng(lat, lon);
                        userMarkerOption.position(latlong_buscado);
                        userMarkerOption.title("Direccion de la Denuncia");
                        userMarkerOption.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));

                        mMap.addMarker(userMarkerOption);
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(latlong_buscado));
                        mMap.animateCamera(CameraUpdateFactory.zoomTo(14));
                    }
                }
                /*
                Address location = address.get(0);
                int lat = (int) (location.getLatitude()*1E6);
                int lon = (int) (location.getLongitude()*1E6);
                LatLng latlong_buscado = new LatLng(lat, lon);
                mMap.addMarker(new MarkerOptions().position(latlong_buscado).title("Salta"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(latlong_buscado));
                */
            } catch (IOException e) {
                DialogHelper.error(MapsActivity.this, "Error","No se ha encontrado la dirección", "Aceptar", true, null);
            }
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
