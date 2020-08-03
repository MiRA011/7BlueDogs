package com.sevenbluedogs.tresenraya;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends Activity {
    private int[] casillas;
    private Partida partida;

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
        //Borramos las jugadas que haya en el tablero
        ImageView imagen;
        for(int cadaCasilla: casillas){
            imagen=(ImageView)findViewById((cadaCasilla));
            imagen.setImageResource(R.drawable.casilla);
        }

        int jugador=1;
        if(vista.getId()==R.id.bJugador2) {
            jugador = 2;
        }


        RadioGroup configDificultad=(RadioGroup)findViewById(R.id.RadioButton);
        int id=configDificultad.getCheckedRadioButtonId();
        int dificultad=0;
        if (id==R.id.normal){
            dificultad=1;
        }else if(id==R.id.imposible){
            dificultad=2;
        }

        partida= new Partida(dificultad,jugador,casillas);
        ((Button)findViewById(R.id.bJugador1)).setEnabled(false);
        ((RadioGroup)findViewById(R.id.RadioButton)).setAlpha(0);
        ((Button)findViewById(R.id.bJugador2)).setEnabled(false);

    }

   public void nuevaJugada(View view){

       if(!(partida==null)) {
           int casilla = view.getId();
           boolean encontrado=false;
           int i=0;
           while(!encontrado & i<casillas.length){
               if(casilla==casillas[i]){
                   encontrado=true;
                   casilla=i;
               }
               i++;
           }
           int jugador= partida.jugador;
           marcar(casilla);
           int estado=partida.estadoPartida(jugador);
           if(estado>0){
               terminarJuego(estado);
               return;
           }

           if(partida.nJugadores==1){
               marcar(0);
               estado=partida.estadoPartida(2);
               if(estado>0){
                   terminarJuego(estado);
                   return;
               }
           }
       }

   }


    //Marca en el tablero la casilla correspondiente,
    //si es el turno del J1 marca la casilla que recibe por parametros
    public void marcar(int casillaj1){
        ImageView imagen;

        if(partida.jugador==1){
            if(partida.casillaLibre(casillaj1)){
                partida.realizarJugada(casillaj1);
                imagen= (ImageView)findViewById(casillas[casillaj1]);
                imagen.setImageResource(R.drawable.circulo);
            }
        }else if(partida.jugador==2){
            int casilla=casillaj1;
            if(partida.nJugadores==1){
                casilla =partida.ia();
                while(!partida.casillaLibre(casilla)){
                    casilla=partida.ia();
                }
            }
            partida.realizarJugada(casilla);
            imagen= (ImageView)findViewById(casillas[casilla]);
            imagen.setImageResource(R.drawable.aspa);
        }
    }

    public void terminarJuego(int resultado){
        Toast toast ;

        if(resultado==3){
            toast =Toast.makeText(this, R.string.empate, Toast.LENGTH_LONG);
        }
        else if(resultado==1){
            toast = Toast.makeText(this, R.string.circulos_ganan, Toast.LENGTH_LONG);
        }
        else{
            toast = Toast.makeText(this, R.string.cruces_ganan, Toast.LENGTH_LONG);
        }
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();

        partida=null;
        ( (Button)findViewById(R.id.bJugador1)).setEnabled(true);
        ((RadioGroup)findViewById(R.id.RadioButton)).setAlpha(1);
        ( (Button)findViewById(R.id.bJugador2)).setEnabled(true);
    }



}