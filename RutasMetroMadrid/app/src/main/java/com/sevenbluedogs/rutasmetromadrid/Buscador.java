package com.sevenbluedogs.rutasmetromadrid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.net.NetworkInterface;
import java.sql.Array;
import java.util.Arrays;

public class Buscador extends AppCompatActivity {

    public Lineas[] lineas;
    public Rutas rutas;
    private ProgressBar barraProgreso;
    EditText origen, destino;
    Button enviar;
    String direccionOrigen, direccionDestino;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscador);

    }

    @Override
    protected void onResume() {
        super.onResume();
        origen = (EditText) findViewById(R.id.origen);
        origen.setText("");
        destino = (EditText) findViewById(R.id.destino);
        destino.setText("");
        enviar = (Button) findViewById(R.id.enviar);
        enviar.setAlpha(1);
        enviar.setEnabled(true);
        if (lineas == null) {
            Bundle miBundle = getIntent().getExtras();
            Parcelable[] datos = miBundle.getParcelableArray("LINEAS");
            lineas = Arrays.copyOf(datos, datos.length, Lineas[].class);
        }

        rutas = new Rutas();
    }

    public void leeDirecciones(View view) {
        enviar.setAlpha(0);
        enviar.setEnabled(false);

        direccionOrigen = origen.getText().toString();
        direccionDestino = destino.getText().toString();

        InputMethodManager introduce = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        introduce.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

        barraProgreso = new ProgressBar(this);
        ejecutaSegundoPlano tarea= new ejecutaSegundoPlano();
        tarea.execute();

    }

    private class ejecutaSegundoPlano extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            Location puntoOringen;
            Location puntoDestino;
            Context context = getApplicationContext();
            ConnectivityManager miManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo estadoRed = miManager.getActiveNetworkInfo();
            if (estadoRed == null || !estadoRed.isConnected() || !estadoRed.isAvailable()) {
                return getString(R.string.error_conexion);
            }
            try {
                puntoOringen = OptimizacionBusqueda.busca(direccionOrigen);
                if (puntoOringen == null) return getString(R.string.error_origen);
                puntoDestino = OptimizacionBusqueda.busca(direccionDestino);
                if (puntoDestino == null) return getString(R.string.error_destino);
            } catch (Exception Manolo) {
                return getString(R.string.error_red);
            }
            rutas.mejorRuta(puntoOringen, puntoDestino, lineas);
            return null;
        }

        protected void onPostExecute(String resultado) {
            barraProgreso = null;
            enviar.setAlpha(1);
            enviar.setEnabled(true);
            muestraRuta();
        }

        public void muestraRuta() {

        }
    }
    public void onBackPressed(){
        if(barraProgreso!=null) barraProgreso=null;
        moveTaskToBack(true);
    }
}