package com.sevenbluedogs.Herramientas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Toast;

public class ActividadHerramientas extends AppCompatActivity implements ComunicaMenu, ManejaFlashCamara {

    Fragment [] misFragmentos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_herramientas);

        misFragmentos=new Fragment[3];

        misFragmentos[0]=new Linterna();
        misFragmentos[1]=new Musica();
        misFragmentos[2]=new Nivel();

        Bundle extras=getIntent().getExtras();

        menu(extras.getInt("idBoton"));
    }

    @Override
    public void menu(int queboton){

        FragmentManager miManejador=getSupportFragmentManager();
        FragmentTransaction miTransaccion=miManejador.beginTransaction();

        Fragment menuIluminado = new Menu();
        Bundle datos = new Bundle();
        datos.putInt("boton",queboton);
        menuIluminado.setArguments(datos);

        miTransaccion.replace(R.id.menu,menuIluminado);


        miTransaccion.replace(R.id.herramientas, misFragmentos[queboton] );
        miTransaccion.commit();

    }

    @Override
    public void enciendeApaga(boolean estadoFlash) {

        if (estadoFlash){

            Toast.makeText(this,"Flash desactivado", Toast.LENGTH_SHORT).show();

        }else{

            Toast.makeText(this,"Flash activado", Toast.LENGTH_SHORT).show();

        }
    }
}