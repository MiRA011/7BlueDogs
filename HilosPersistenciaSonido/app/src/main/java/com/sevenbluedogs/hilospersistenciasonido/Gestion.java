package com.sevenbluedogs.hilospersistenciasonido;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;

public class Gestion extends AppCompatActivity {

    private Partida partida;
    private int dificultad;
    private final int FPS=30;
    private Handler temporizador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion);
        Bundle extras = getIntent().getExtras();
        dificultad = extras.getInt("dificultad");
        partida= new Partida(getApplicationContext(),dificultad);
        temporizador= new Handler();
        temporizador.postDelayed(hilo,30);
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
        partida.toque(x,y);
        return false;
    }

    public void fin(){

        temporizador.removeCallbacks(hilo);

        finish();//destruye la actividad actual
    }
}