package com.sevenbluedogs.hilospersistenciasonido;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int record;

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

        if(dificultad.equals(getString(R.string.medio))) dificultadInt=2;
        if(dificultad.equals(getString(R.string.dificil))) dificultadInt=3;

        Intent in = new Intent(this, Gestion.class);
        in.putExtra("dificultad",dificultadInt);
        //startActivity(in);
        startActivityForResult(in,1);
    }
    @Override
    protected void onActivityResult(int peticion, int codigo, Intent puntuacion) {

        super.onActivityResult(peticion, codigo, puntuacion);
        if (peticion != 1 || codigo!=RESULT_OK) return;
        int resultado=-1;
        try{

            resultado=puntuacion.getIntExtra("puntuacion", 0);

        }catch(Exception manolo){

            System.out.println("ESTA MIERDA NO FUNCIONA POR QUE HA PETAO LA PARTE DEL GET INT " + resultado);
            System.out.println(manolo.toString());

        }
        if(resultado>record){
            record=resultado;
            TextView caja=(TextView)findViewById(R.id.texto_puntuacion);
            caja.setText("Puntuacion: " + record);
            guardaRecord();
        }
            String texto = "¡Haz hecho " + resultado + " toques!";

            Toast toast = Toast.makeText(this,texto,Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
    }
    @Override
    public void onResume(){
        super.onResume();
        leeRecord();
    }


    private void guardaRecord(){
        SharedPreferences datos = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor miEditor=datos.edit();
        miEditor.putInt("record",record);
        miEditor.apply();
    }

    private void leeRecord(){
        SharedPreferences datos = PreferenceManager.getDefaultSharedPreferences(this);
        record=datos.getInt("record",record);
        TextView puntuacion= (TextView) findViewById(R.id.texto_puntuacion);
        puntuacion.setText("Puntuación: "+record);
    }
}