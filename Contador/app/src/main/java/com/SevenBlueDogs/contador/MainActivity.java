package com.SevenBlueDogs.contador;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
    public int contador;
    public TextView cuenta;
    public CheckBox negativos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cuenta=(TextView) findViewById(R.id.tCuenta);
        negativos= (CheckBox) findViewById(R.id.negativo);
        contador=0;
        EventoTeclado teclado= new EventoTeclado();
        EditText n_reset= (EditText) findViewById(R.id.n_reset);
        n_reset.setOnEditorActionListener(teclado);
    }

    class EventoTeclado implements TextView.OnEditorActionListener{

        @Override
        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {

            if(i== EditorInfo.IME_ACTION_DONE){
                reseteaContador(null);
            }

            return false;
        }
    }

    public void incrementaContador(View vista){
        contador++;
        escribir();
    }

    public void restaContador(View vista){
        contador--;
        verificar();
        escribir();
    }

    public void reseteaContador(View vista){
        EditText n_reset= (EditText) findViewById(R.id.n_reset);
        try {
            contador = Integer.parseInt(n_reset.getText().toString());
        }catch (Exception e){
            contador=0;
        }
        verificar();
        n_reset.setText("");
        escribir();
        InputMethodManager teclado= (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        teclado.hideSoftInputFromWindow(n_reset.getWindowToken(),0);

    }

    public void checkbox(View vista){
        verificar();
        escribir();
    }

    public void verificar(){
        if(contador<0 && !negativos.isChecked()){
                contador=0;
        }
    }
    public void escribir(){
        cuenta.setText(String.valueOf(contador));
    }

}