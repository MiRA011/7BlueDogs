package com.sevenbluedogs.Herramientas;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class Musica extends Fragment {


    public Musica() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View miFragmento= inflater.inflate(R.layout.fragment_musica, container, false);
        ImageView altavoz = miFragmento.findViewById(R.id.musica);
        ;
        altavoz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer sonido = MediaPlayer.create(getContext(),R.raw.fast);
                sonido.start();
            }
        });
        return miFragmento;
    }
}