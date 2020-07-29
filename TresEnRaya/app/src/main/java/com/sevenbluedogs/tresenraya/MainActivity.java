package com.sevenbluedogs.tresenraya;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        casillas= new int[9];

        casillas[0]=R.id.a1;
        casillas[1]=R.id.a2;
        casillas[2]=R.id.a3;
        casillas[3]=R.id.b1;
        casillas[4]=R.id.b2;
        casillas[5]=R.id.b3;
        casillas[6]=R.id.c1;
        casillas[7]=R.id.c2;
        casillas[8]=R.id.c3;
    }

    public void empezarPartida(View vista){

        ImageView imagen;

            for(int cadaCasilla: casillas){

                imagen=(ImageView)findViewById((cadaCasilla));

                imagen.setImageResource(R.drawable.casilla);

            }

            jugadores=1;

            if(vista.getId()==R.id.dosjug) {

                jugadores = 2;
            }

        RadioGroup configDificultad=(RadioGroup)findViewById(R.id.configd);

        int id=configDificultad.getCheckedRadioButtonId();

        int dificultad=0;

        if (id==R.id.normal){

            dificultad=1;
        }else if(id==R.id.imposible){

            dificultad=2;

        }

        partida= new Partida(dificultad);

        ( (Button)findViewById(R.id.bJugador1)).setEnabled(false);

        ((RadioGroup)findViewById(R.id.configd)).setAlpha(0);

        ( (Button)findViewById(R.id.dosjug)).setEnabled(false);

    }

    private int jugadores;

    private int[] casillas;

    private Partida partida;


}