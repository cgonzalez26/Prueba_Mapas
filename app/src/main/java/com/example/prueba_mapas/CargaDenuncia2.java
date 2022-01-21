package com.example.prueba_mapas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class CargaDenuncia2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carga_denuncia2);
    }

    public void VolverDenuncia1(View vista){
        Intent intento = new Intent(this, CargaDenuncia1.class);
        startActivity(intento);
    }
}
