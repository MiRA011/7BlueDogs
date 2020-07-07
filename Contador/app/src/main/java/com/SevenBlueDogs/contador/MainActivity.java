package com.SevenBlueDogs.contador;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
    public int contador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contador=0;
    }

    public void incrementaContador(View vista){
        contador++;
        mostrarResultado();
    }

    public void restaContador(View vista){
        contador--;
        mostrarResultado();

    }

    public void reseteaContador(View vista){
        contador=0;
        mostrarResultado();
    }

    public void mostrarResultado(){
        TextView cuenta = (TextView) findViewById(R.id.tCuenta);
        cuenta.setText(String.valueOf(contador));
    }
}