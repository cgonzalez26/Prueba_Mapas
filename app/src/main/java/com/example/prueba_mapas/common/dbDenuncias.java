package com.example.prueba_mapas.common;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class dbDenuncias extends DbHelper{
    Context context;

    public dbDenuncias(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long registrarDenuncia(String sNroDocumento,String sNombre,String sApellido,String sMail,
                                  String sTelefono,String sDireccion,String sEntre_Calle, String sLongitud,
                                  String sLatitud,String tRelato,String TipoDenunciaId,String InsertedAt){
        long id = 0;
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("sNroDocumento", sNroDocumento);
            values.put("sNombre", sNombre);
            values.put("sApellido", sApellido);
            values.put("sMail", sMail);
            values.put("sTelefono", sTelefono);
            values.put("sDireccion", sDireccion);
            values.put("sEntre_Calle", sEntre_Calle);
            values.put("sLongitud", sLongitud);
            values.put("sLatitud", sLatitud);
            values.put("tRelato", tRelato);
            values.put("TipoDenunciaId", TipoDenunciaId);
            values.put("InsertedAt", InsertedAt);

            id = db.insert(TABLE_NOMBRE, null, values);

        }catch (Exception ex){
            ex.toString();
        }
        return id;
    }
}
