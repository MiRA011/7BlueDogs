package com.sevenbluedogs.crud_bbdd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button botonInsertar, botonActualizar, botonBorrar, botonBuscar;

    EditText textoId, textoNombre, textoApellido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botonInsertar = (Button)findViewById(R.id.insertar);
        botonActualizar = (Button)findViewById(R.id.actualizar);
        botonBorrar = (Button)findViewById(R.id.borrar);
        botonBuscar = (Button)findViewById(R.id.buscar);

        textoId = (EditText)findViewById(R.id.id);
        textoNombre = (EditText)findViewById(R.id.nombre);
        textoApellido = (EditText)findViewById(R.id.apellido);

        botonInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        botonActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        botonBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        botonBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

    }
}
