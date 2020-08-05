package com.sevenbluedogs.hilospersistenciasonido;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ayuda(View view){
        Intent intent = new Intent(this, AyudaActividad.class);
        startActivity(intent);
    }

    public void dificultad(View view){
        String dificultad=(String) ((Button) view).getText();
        int dificultadInt=1;

        if(dificultad.equals("Standard")) dificultadInt=2;
        if(dificultad.equals("Dificult")) dificultadInt=3;

        Intent in = new Intent(this, Gestion.class);
        in.putExtra("dificultad",dificultadInt);
        startActivity(in);
    }

}