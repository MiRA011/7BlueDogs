package com.sevenbluedogs.tresenraya;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private int jugadores;
    private int[] casillas;
    private Partida partida;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inicializar el array casillas con los identificadores de cada casilla
        casillas= new int[9];
        casillas[0]= R.id.cA1;
        casillas[0]= R.id.cA2;
        casillas[0]= R.id.cA3;
        casillas[0]= R.id.cB1;
        casillas[0]= R.id.cB2;
        casillas[0]= R.id.cB3;
        casillas[0]= R.id.cC1;
        casillas[0]= R.id.cC2;
        casillas[0]= R.id.cC3;
    }

    public void empezarPartida(View view){
        ImageView imagen;

        for (int i : casillas){
            imagen = (ImageView)findViewById((i));
            imagen.setImageResource(R.drawable.casilla);
        }

        int boton =view.getId();
        if(boton==R.id.bJugador1){

        }else if(boton==R.id.bJugador2){

        }
    }
}