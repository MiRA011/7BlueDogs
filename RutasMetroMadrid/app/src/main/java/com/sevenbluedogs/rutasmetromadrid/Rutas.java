package com.sevenbluedogs.rutasmetromadrid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Rutas extends AppCompatActivity {

    public String linea;
    public Location[] paradas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rutas);

        Intent miIntento=this.getIntent();
        String linea=miIntento.getStringExtra("LINEAS");
        ((TextView)findViewById(R.id.linea)).setText(linea);

        Bundle miBundle=getIntent().getExtras();
        Parcelable[] datos=miBundle.getParcelableArray("PARADAS");
        Location[] ruta= Arrays.copyOf(datos, datos.length,Location[].class);

        LinearLayout rutaContenedor=(LinearLayout)findViewById(R.id.pantalla);
        LayoutInflater inflador=(LayoutInflater)this.getSystemService(LAYOUT_INFLATER_SERVICE);
    }

    public void mapa (View vista){
        Bundle miBundle=getIntent().getExtras();
        Intent miIntento=new Intent(this, MapsActivity.class);
        miIntento.putExtras(miBundle);
        startActivity(miIntento);
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

        //Esto lo he escrito yo por que lo que ponia el tio estaba mal por logica
        paradas=new Location[mejorLinea.finalRuta-mejorLinea.origenRuta+1];
        for(int i=0; i<paradas.length;i++){
            paradas[i]=mejorLinea.estaciones[mejorLinea.origenRuta+i];
        }
        //todomal
    }

}