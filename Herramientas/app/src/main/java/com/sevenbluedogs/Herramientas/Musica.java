package com.sevenbluedogs.Herramientas;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class Musica extends Fragment {

    public boolean encendida;
    private ImageView altavoz;

    public Musica() {

    }

    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View miFragmento= inflater.inflate(R.layout.fragment_musica, container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        altavoz=(ImageView)miFragmento.findViewById(R.id.musica);
        if(encendida)altavoz.setImageResource(R.drawable.musica2);

        altavoz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (encendida) apagaMusica();
                else enciendeMusica();
                /*MediaPlayer sonido = MediaPlayer.create(getContext(),R.raw.fast);
                sonido.start();*/
            }
        });
        return miFragmento;
    }
    public void enciendeMusica(){

        altavoz.setImageResource(R.drawable.musica2);
        Intent miReproductor=new Intent(getActivity(), ServicioMusica.class);
        getActivity().startService(miReproductor);
        encendida= !encendida;

    }

    public void apagaMusica(){

        altavoz.setImageResource(R.drawable.musica);
        Intent miReproductor=new Intent(getActivity(), ServicioMusica.class);
        getActivity().stopService(miReproductor);
        encendida= !encendida;
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}