package com.sevenbluedogs.hilospersistenciasonido;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;

public class Gestion extends AppCompatActivity {

    private int botes;
    private Partida partida;
    private int dificultad;
    private final int FPS=30;
    private Handler temporizador;
    private MediaPlayer golpeo;
    private MediaPlayer fin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        botes=0;
        golpeo = MediaPlayer.create(this, R.raw.toque);
        fin = MediaPlayer.create(this,R.raw.finalpartida);
        setContentView(R.layout.activity_gestion);
        Bundle extras = getIntent().getExtras();
        dificultad = extras.getInt("dificultad");
        partida= new Partida(getApplicationContext(),dificultad);
        temporizador= new Handler();
        temporizador.postDelayed(hilo,FPS);
        setContentView(partida);
    }

    private Runnable hilo = new Runnable() {
        @Override
        public void run() {
            if(partida.movimientoBola()){
                fin();
            }else{
                partida.invalidate(); // elimina el contenido de ImageView y llama de nuevo a onDraw()
                temporizador.postDelayed(hilo,FPS);
            }
        }
    };

    public boolean onTouchEvent(MotionEvent evento){
        int x = (int) evento.getX();
        int y = (int) evento.getY();
        if (partida.toque(x,y)) botes++;
        golpeo.start();
        return false;
    }

    public void fin(){
        fin.start();
        temporizador.removeCallbacks(hilo);
        Intent in=new Intent();
        in.putExtra("puntuacion", botes);
        setResult(RESULT_OK, in);
        finish();//destruye la actividad actual
    }
}