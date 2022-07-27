package com.example.prueba_mapas.common;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "denuncias.db";
    public static final String TABLE_NOMBRE = "denuncias";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_NOMBRE+ "( " +
                "id integer primary key autoincrement," +
                "sNroDocumento text," +
                "sNombre text," +
                "sApellido text," +
                "sMail text," +
                "sTelefono text," +
                "sDireccion text," +
                "sEntre_Calle text," +
                "sLongitud text," +
                "sLatitud text," +
                "tRelato text," +
                "TipoDenunciaId text," +
                "InsertedAt text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NOMBRE);
        onCreate(sqLiteDatabase);
    }

    public void registrar(String Id, String sNroDocumento,String sNombre,String sApellido,String sMail,
                          String sTelefono,String sDireccion,String sEntre_Calle, String sLongitud,
                          String sLatitud,String tRelato,String TipoDenunciaId,String InsertedAt){
        SQLiteDatabase db = getWritableDatabase();
        //verificar si se abre correctamente la BD
        if( db != null){
            db.execSQL("INSERT INTO "+TABLE_NOMBRE+" VALUES('"+Id+"','"+sNroDocumento+"','"+sNombre+"','" +
                    sApellido+"','"+sMail+"','"+sTelefono+"','"+sDireccion+"','"+sEntre_Calle+"','"+
                    sLongitud+"','"+sLatitud+"','"+tRelato+"','"+TipoDenunciaId+"','"+InsertedAt+"')");
            db.close();
        }
    }
}
