package com.sevenbluedogs.rutasmetromadrid;

import androidx.appcompat.app.AppCompatActivity;

import android.location.Location;
import android.os.Bundle;

import java.lang.reflect.Array;

public class Rutas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rutas);
    }

    public void mejorRuta(Location origen, Location destino, Lineas[] lineas){

    }
}