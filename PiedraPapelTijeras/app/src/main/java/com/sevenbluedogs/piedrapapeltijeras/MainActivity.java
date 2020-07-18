package com.sevenbluedogs.piedrapapeltijeras;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public boolean listoJ1, listoJ2, ganador;
    public int pj1, pj2, nronda, jj1, jj2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nronda=1;
    }

    public void pulsaPiedra(View vista){
        if(!listoJ1){
            jj1=1;
            listoJ1=true;
            TextView listo= (TextView) findViewById(R.id.tListoJ1);
            listo.setText(R.string.listo);
        }else if(listoJ1 && !listoJ2){
            jj2=1;
            listoJ2=true;
            TextView listo= (TextView) findViewById(R.id.tListoJ2);
            listo.setText(R.string.listo);
        }
    }
    public void pulsaPapel(View vista){
        if(!listoJ1){
            jj1=2;
            listoJ1=true;
            TextView listo= (TextView) findViewById(R.id.tListoJ1);
            listo.setText(R.string.listo);
        }else if(listoJ1 && !listoJ2){
            jj2=2;
            listoJ2=true;
            TextView listo= (TextView) findViewById(R.id.tListoJ2);
            listo.setText(R.string.listo);
        }
    }
    public void pulsaTijera(View vista) {
        if (!listoJ1) {
            jj1 = 3;
            listoJ1 = true;
            TextView listo = (TextView) findViewById(R.id.tListoJ1);
            listo.setText(R.string.listo);
        } else if (listoJ1 && !listoJ2) {
            jj2 = 3;
            listoJ2 = true;
            TextView listo = (TextView) findViewById(R.id.tListoJ2);
            listo.setText(R.string.listo);
        }
    }

    public void resolver(View vista){
        if(listoJ1 && listoJ2 && !ganador){
            TextView tGanador = (TextView) findViewById(R.id.tGanador);
            TextView tScoreJ1 = (TextView) findViewById(R.id.tScoreJ1);
            TextView tScoreJ2 = (TextView) findViewById(R.id.tScoreJ2);
            ganador=true;
           if(jj1==jj2){ //empate
               tGanador.setText(R.string.empate);
           }else if(jj1==1 && jj2==2){ //gana j2
               tGanador.setText(R.string.ganaj2);
               pj2++;
               tScoreJ2.setText(getString(R.string.score) + pj2);

           }else if(jj1==1 && jj2==3){ //gana j1
               tGanador.setText(R.string.ganaj1);
               pj1++;
               tScoreJ1.setText(getString(R.string.score) + pj1);

           }else if(jj1==2 && jj2==1){ //gana j1
               tGanador.setText(R.string.ganaj1);
               pj1++;
               tScoreJ1.setText(getString(R.string.score) + pj1);

           }else if(jj1==2 && jj2==3){ //gana j2
               tGanador.setText(R.string.ganaj2);
               pj2++;
               tScoreJ2.setText(getString(R.string.score) + pj2);

           }else if(jj1==3 && jj2==1){ //gana j2
               tGanador.setText(R.string.ganaj2);
               pj2++;
               tScoreJ2.setText(getString(R.string.score) + pj2
               );

           }else if(jj1==3 && jj2==2){ //gana j1
               tGanador.setText(R.string.ganaj1);
               pj1++;
               tScoreJ1.setText(getString(R.string.score) + pj1);
           }
        }

    }

    public void nuevaRonda(View Vista){
        if(ganador){
          nronda++;
          listoJ1 = false;
          listoJ2 = false;
          ganador= false;
          TextView tListoJ1 = (TextView) findViewById(R.id.tListoJ1);
          TextView tListoJ2 = (TextView) findViewById(R.id.tListoJ2);
          TextView tRonda = (TextView) findViewById(R.id.tRonda);
          TextView tGanador= (TextView) findViewById(R.id.tGanador);
          tListoJ1.setText("...");
          tListoJ2.setText("...");
          tRonda.setText(getString(R.string.tcuentaRonda)+nronda);
          tGanador.setText(R.string.ganador);
        }
    }

    public void nuevoJuego(View vista){
        listoJ1 = false;
        listoJ2 = false;
        ganador= false;
        nronda=1;
        pj2=0;
        pj1=0;
        TextView tListoJ1 = (TextView) findViewById(R.id.tListoJ1);
        TextView tListoJ2 = (TextView) findViewById(R.id.tListoJ2);
        TextView tRonda = (TextView) findViewById(R.id.tRonda);
        TextView tGanador = (TextView) findViewById(R.id.tGanador);
        TextView tScoreJ1 = (TextView) findViewById(R.id.tScoreJ1);
        TextView tScoreJ2 = (TextView) findViewById(R.id.tScoreJ2);
        tListoJ1.setText("...");
        tListoJ2.setText("...");
        tRonda.setText(getString(R.string.tcuentaRonda) + nronda);
        tScoreJ1.setText(getString(R.string.score) + pj1);
        tScoreJ2.setText(getString(R.string.score) + pj2);
        tGanador.setText(R.string.ganador);
    }
}