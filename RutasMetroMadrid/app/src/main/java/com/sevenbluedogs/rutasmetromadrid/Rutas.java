package com.sevenbluedogs.rutasmetromadrid;

import androidx.appcompat.app.AppCompatActivity;

import android.location.Location;
import android.os.Bundle;

import java.lang.reflect.Array;

public class Rutas extends AppCompatActivity {

    public String linea;
    public Location[] paradas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rutas);
    }

    public void mejorRuta(Location origen, Location destino, Lineas[] lasLineas){
        Lineas mejorLinea=null;

        for(Lineas linea: lasLineas){
            linea.distancias(origen,destino);

            if(mejorLinea==null || linea.sumaDistMetros()<mejorLinea.sumaDistMetros()){
                mejorLinea=linea;
            }
        }

        if(mejorLinea==null || origen.distanceTo(destino)<mejorLinea.sumaDistMetros()){
            this.linea=null;
            paradas = new Location[2];
            paradas[0] = origen;
            paradas[1] = destino;
            return;
        }

    }

}