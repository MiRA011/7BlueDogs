package com.sevenbluedogs.rutasmetromadrid;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ManejoBBDD extends SQLiteOpenHelper {

    String rutaAlmacenamiento;
    SQLiteDatabase bbdd;

    public ManejoBBDD(@Nullable Context context) {
        super(context, "paradasMetro.db3", null, 1);
        rutaAlmacenamiento=context.getFilesDir().getParentFile().getPath() + "paradasMetro.db3";
    }

    public void aperturaBBDD(Context context){

        try{
            //Solo funciona cuando no accedemos por primera vez a la base de datos.
            bbdd=SQLiteDatabase.openDatabase(rutaAlmacenamiento, null, SQLiteDatabase.OPEN_READONLY);

        }catch(Exception manolo){

            //copiaBBDD(context);
            bbdd=SQLiteDatabase.openDatabase(rutaAlmacenamiento, null, SQLiteDatabase.OPEN_READONLY);

        }

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
