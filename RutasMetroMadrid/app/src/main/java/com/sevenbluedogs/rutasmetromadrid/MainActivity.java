package com.sevenbluedogs.rutasmetromadrid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    public final String[] LINEAS={"Linea 2", "Linea 3", "Linea 6"};
    Lineas[] lineas;

    private ProgressBar barraProgreso;

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
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}