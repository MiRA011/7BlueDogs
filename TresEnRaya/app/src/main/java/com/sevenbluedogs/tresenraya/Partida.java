package com.sevenbluedogs.tresenraya;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.ImageView;
import java.util.Random;

public class Partida {

    public final int dificultad;
    public int nJugadores;
    public int jugador; //Jugador al que le pertenece el turno
    private final int [] TABLERO;  //Almacena los id de las casillas
    private int [] casillas; //Almacena los resultados en el tablero
    private final int [][] COMBINACIONES ={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public Partida (int dificultad,int nJugadores, int [] TABLERO){
        this.dificultad=dificultad;
        this.nJugadores=nJugadores;
        this.TABLERO=TABLERO;
        jugador=1;

        //Inicializamos las casillas vacias
        casillas = new int[9];
        for (int i =0; i<9; i++ ) {
            casillas[i]=0;
        }
    }

    //Devuelve si existe una casilla vacia con posibilidad de tres en raya para el jugador seleccionado
    //En caso de no existir devuelve -1;
    private int dosEnRaya(int jugador){
        int casilla = -1;
        int contador = 0;

        for(int i=0; i<COMBINACIONES.length;i++){
            for(int pos:COMBINACIONES[i]){
                if(casillas[pos]==jugador){
                    contador++;
                }

                if(casillas[pos]==0) casilla=pos;
            }
            if(contador==2 && casilla!=-1) return casilla;
            casilla=-1;
            contador=0;
        }
        return -1;
    }

    //Simula al J2 con varios niveles de dificultad
    public int ia(){
        int casilla;
        casilla=dosEnRaya(2);
        if(casilla!=-1)return casilla;

        if(dificultad>0) {
            casilla = dosEnRaya(1);
            if (casilla != -1) return casilla;
        }

        if(dificultad==2){
            if(casillas[0]==0){return 0;}
            if(casillas[2]==0){return 2;}
            if(casillas[6]==0){return 6;}
            if(casillas[8]==0){return 8;}
        }

        Random rnd =new Random();
        casilla = rnd.nextInt(9);
        return casilla;
    }

    //Cambia el turno de un jugador a otro
    private void turno(){
        jugador++;
        if(jugador>2){
            jugador=1;
        }
    }

    //Esta funcion devuelve el estado actual de la partida (Debe invocarse antes de cambiar el turno)
    //Si devuelve 0 la partida no ha terminado
    //Si devuelve 1 o 2 ha ganado uno de los dos jugadores
    //Si devuelve 3 la partida ha terminado en empate
    public int estadoPartida(int jug){
        boolean empate = true;
        boolean ult_movimiento=true;

        for(int i=0; i<COMBINACIONES.length;i++){
            for(int pos:COMBINACIONES[i]){

                if(casillas[pos]!=jug)ult_movimiento=false;

                if(casillas[pos]==0)empate=false;
            }
            if(ult_movimiento) return jug;
            ult_movimiento=true;
        }

        if(empate)return 3;

        return 0;
    }

    //Comprueba si la casilla esta libre
    public boolean casillaLibre(int casilla){
        if(casillas[casilla]!=0){
            return false;
        }else{
            return true;
        }
    }

    //Marca en el tablero la casilla correspondiente, y avanza el turno
    public void realizarJugada(int casilla){
        casillas[casilla]=this.jugador;
        turno();
    }

    }
