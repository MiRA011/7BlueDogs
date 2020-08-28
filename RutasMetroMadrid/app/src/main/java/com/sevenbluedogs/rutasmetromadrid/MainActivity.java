package com.sevenbluedogs.rutasmetromadrid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    public final String[] LINEAS={"Linea 2", "Linea 3", "Linea 6"};
    public Lineas[] lineas;
    private ProgressBar barraProgreso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        barraProgreso = (ProgressBar) findViewById(R.id.barraProgreso);
        barraProgreso.setVisibility(View.VISIBLE);

        Sincroniza comienzo = new Sincroniza();
        comienzo.execute();

    }
    public void comenzar(){
        Bundle miBundle = new Bundle();
        miBundle.putParcelableArray("LINEAS",lineas);
        Intent miIntent = new Intent(this,Buscador.class);
    }

    private class Sincroniza extends AsyncTask<String, Integer, String>{

        @Override
        protected String doInBackground(String... strings) {
            ManejoBBDD bbdd=new ManejoBBDD(getApplicationContext());
            try{
                bbdd.aperturaBBDD(getApplicationContext());
                lineas=bbdd.dameInfoLineas(LINEAS);
                bbdd.cerrarBBDD();
            }catch(Exception Manolo){
                finish();
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            barraProgreso.setProgress(values[0],true);
        }

        @Override
        protected void onPostExecute(String s) {
            comenzar();
        }
    }
    
}