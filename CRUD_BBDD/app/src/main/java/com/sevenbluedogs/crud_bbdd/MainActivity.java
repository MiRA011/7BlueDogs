package com.sevenbluedogs.crud_bbdd;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

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

        final BBDD_Helper helper = new BBDD_Helper(this);

        botonInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = helper.getWritableDatabase();

                ContentValues values = new ContentValues();
                values.put(Estructura_BBDD.NOMBRE_COLUMNA1, textoId.getText().toString());
                values.put(Estructura_BBDD.NOMBRE_COLUMNA2, textoNombre.getText().toString());
                values.put(Estructura_BBDD.NOMBRE_COLUMNA3, textoApellido.getText().toString());

                long newRowId = db.insert(Estructura_BBDD.TABLE_NAME,  null, values);

                Toast.makeText(getApplicationContext(), "Se guardó el registro con clave:" + newRowId, Toast.LENGTH_LONG).show();
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
                SQLiteDatabase db = helper.getReadableDatabase();

                String[] projection = {
                        Estructura_BBDD.NOMBRE_COLUMNA2,
                        Estructura_BBDD.NOMBRE_COLUMNA3,
                };

                String selection = Estructura_BBDD.NOMBRE_COLUMNA1 + " = ?";
                String[] selectionArgs = { textoId.getText().toString() };
                //String sortOrder = Estructura_BBDD.NOMBRE_COLUMNA2 + " DESC";

                try{
                Cursor cursor = db.query(
                        Estructura_BBDD.TABLE_NAME,   // The table to query
                        projection,             // The array of columns to return (pass null to get all)
                        selection,              // The columns for the WHERE clause
                        selectionArgs,          // The values for the WHERE clause
                        null,                   // don't group the rows
                        null,                   // don't filter by row groups
                        null             // The sort order
                );

                cursor.moveToFirst();
                textoNombre.setText(cursor.getString(0));
                textoApellido.setText(cursor.getString(1));
                cursor.close();
                }catch(Exception manolo){
                    Toast.makeText(getApplicationContext(), manolo.toString(), Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}
