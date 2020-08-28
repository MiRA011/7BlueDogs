package com.sevenbluedogs.rutasmetromadrid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

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
        enviar =  (Button) findViewById(R.id.enviar);
        enviar.setAlpha(1);
        enviar.setEnabled(true);
        if(lineas==null){
            Bundle miBundle = getIntent().getExtras();
            Parcelable[] datos = miBundle.getParcelableArray("LINEAS");
            lineas= Arrays.copyOf(datos, datos.length,Lineas[].class);
        }
    }

    public void leeDirecciones(View view){
        enviar.setAlpha(0);
        enviar.setEnabled(false);

        direccionOrigen=origen.getText().toString();
        direccionDestino=destino.getText().toString();

        InputMethodManager introduce = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        introduce.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);

        barraProgreso= new ProgressBar(this);

    }

}