package com.example.prueba_mapas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class CargaDenuncia1 extends AppCompatActivity {

    private Spinner spTipoDenuncia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carga_denuncia1);

        spTipoDenuncia = findViewById(R.id.spTipoDenuncia);

        /*String[] categorias = {"SELECCIONE TIPO","BASURAL", "BACHE","PERDIDA DE AGUA","DESBORDE CLOACAL","OCUPACION INDEBIDA DE LA VIA PUBLICA","OBSTRUCCION DE RAMPAS ACCESIBILIDAD","OTRO"};
        spTipoDenuncia.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, categorias));*/

    }

    public void CargaDenunciaParte2(View vista){
        Intent intento = new Intent(this, CargaDenuncia2.class);

        //pasamos los datos del Form parte 1
        Bundle extras = new Bundle();
        EditText edNombres = (EditText) findViewById(R.id.edNombres);
        EditText edApellidos = (EditText) findViewById(R.id.editApellidos);
        EditText edNroDocumento = (EditText) findViewById(R.id.edNroDocumento);
        EditText edMail = (EditText) findViewById(R.id.edMail);
        EditText edTelefono = (EditText) findViewById(R.id.edTelefono);
        EditText edRelato = (EditText) findViewById(R.id.edRelato);
        intento.putExtra("sNombre",edNombres.getText().toString());
        intento.putExtra("sApellido",edApellidos.getText().toString());
        intento.putExtra("sNroDocumento",edNroDocumento.getText().toString());
        intento.putExtra("sMail",edMail.getText().toString());
        intento.putExtra("sTelefono",edTelefono.getText().toString());
        intento.putExtra("tRelato",edRelato.getText().toString());
        Spinner spinner = (Spinner) findViewById(R.id.spTipoDenuncia);
        int spinner_pos = spinner.getSelectedItemPosition();
        String[] tipodenuncias_values = getResources().getStringArray(R.array.tipodenuncias_values);
        String code_tipodenuncia = tipodenuncias_values[spinner_pos];
        intento.putExtra("TipoDenunciaId",code_tipodenuncia);

        startActivity(intento);
        finish();
    }

    public void VolverInicio(View vista){
        Intent intento = new Intent(this, MainActivity.class);

        startActivity(intento);
        finish();
    }
}
