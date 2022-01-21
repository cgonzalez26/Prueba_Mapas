package com.example.prueba_mapas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class CargaDenuncia1 extends AppCompatActivity {

    private Spinner spTipoDenuncia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carga_denuncia1);

        spTipoDenuncia = findViewById(R.id.spTipoDenuncia);

        String[] categorias = {"SELECCIONE TIPO","BASURAL", "BACHE","PERDIDA DE AGUA","DESBORDE CLOACAL","OCUPACION INDEBIDA DE LA VIA PUBLICA","OBSTRUCCION DE RAMPAS ACCESIBILIDAD","OTRO"};
        spTipoDenuncia.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, categorias));

    }

    public void CargaDenunciaParte2(View vista){
        Intent intento = new Intent(this, CargaDenuncia2.class);
        startActivity(intento);
    }

    public void VolverInicio(View vista){
        Intent intento = new Intent(this, MainActivity.class);
        startActivity(intento);
    }
}
