package com.example.prueba_mapas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AndroidNetworking.initialize(getApplicationContext());
    }

    public void CargaDenunciaParte1(View vista){
        Intent intento = new Intent(this, CargaDenuncia1.class);
        startActivity(intento);
    }

    public void verMapa(View vista){
        Intent intento = new Intent(this, MapsActivity.class);
        startActivity(intento);
    }

    public void Salir(View vista){
        finish();
    }
}