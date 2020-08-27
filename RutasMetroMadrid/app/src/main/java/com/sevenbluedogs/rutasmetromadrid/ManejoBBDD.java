package com.sevenbluedogs.rutasmetromadrid;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.location.Location;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

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

    private void copiaBBDD(Context context){
        try {
            InputStream datosEntrada = context.getAssets().open("apradasMetro.db3");
            OutputStream datosSalida=new FileOutputStream(rutaAlmacenamiento);
            byte[] bufferBBDD=new byte[1024];
            int longitud;
            while((longitud=datosEntrada.read(bufferBBDD))>0){
                datosSalida.flush();
                datosSalida.close();
                datosEntrada.close();
            }
        }catch (Exception Manolo){

        }
    }

    public Location datosEstacion(int id){
        Location estacion;
        Cursor miCursor;
        miCursor=bbdd.rawQuery("SELECT * FROM paradas WHERE id=" + id, null);
        miCursor.moveToFirst();
        estacion=new Location(miCursor.getString(1));
        estacion.setLatitude(Double.parseDouble(miCursor.getString(2)));
        estacion.setLongitude((Double.parseDouble(miCursor.getString(3))));
        miCursor.close();
        return estacion;
    }

    public Lineas[] dameInfoLineas(String [] nombresDeLineas){
        Lineas [] lasLineas= new Lineas[nombresDeLineas.length];
        Cursor miCursor=null;

        for (int i =0; i<nombresDeLineas.length;i++){
            lasLineas[i]=new Lineas();
            lasLineas[i].nombre=nombresDeLineas[i];
            miCursor=bbdd.rawQuery("SELECT Id FROM "+nombresDeLineas[i],null);
            lasLineas[i].estaciones= new Location[miCursor.getCount()];
            int contador = 0;

            miCursor.moveToFirst();
            //Guardamos las estaciones de la linea actual
            while(!miCursor.isAfterLast()) {
                int estacion = Integer.parseInt(miCursor.getString(0));
                lasLineas[i].estaciones[contador] = datosEstacion(estacion);
                miCursor.moveToNext();
                contador++;
            }

        }

        if(miCursor!=null && miCursor.isClosed()) miCursor.close();
        return lasLineas;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
