package com.SevenBlueDogs.contador;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
    public int contador;
    public TextView cuenta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cuenta=(TextView) findViewById(R.id.tCuenta);
        contador=0;
    }

    public void incrementaContador(View vista){
        contador++;
        cuenta.setText(String.valueOf(contador));
    }

    public void restaContador(View vista){
        contador--;
        if(contador<0){
            CheckBox negativos= (CheckBox) findViewById(R.id.negativo);
            if(!negativos.isChecked()){
                contador=0;
            }
        }
        cuenta.setText(String.valueOf(contador));


    }

    public void reseteaContador(View vista){
        EditText n_reset= (EditText) findViewById(R.id.n_reset);

        try {
            contador = Integer.parseInt(n_reset.getText().toString());
        }catch (Exception e){
            contador=0;
        }
        n_reset.setText("");
        cuenta.setText(String.valueOf(contador));
    }

    public void checkbox(View vista){
        if(contador<0){
            CheckBox negativos= (CheckBox) findViewById(R.id.negativo);
            if(!negativos.isChecked()){
                contador=0;
                cuenta.setText(String.valueOf(contador));
            }
        }


    }

}