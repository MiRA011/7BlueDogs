package com.sevenbluedogs.Herramientas;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Menu extends Fragment {

    private final int[] BOTONESMENU={R.id.linterna, R.id.musica,R.id.nivel};

    private final int[] BOTONESILUMINADOS={R.drawable.linterna2,R.drawable.musica2,R.drawable.nivel2};

    private int boton;

    public Menu() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View mimenu= inflater.inflate(R.layout.fragment_menu, container, false);
        boton = -1;

        if(getArguments()!=null) boton = getArguments().getInt("boton");

        ImageButton botonmenu;


        for(int i=0;i<BOTONESMENU.length;i++){
            botonmenu= (ImageButton) mimenu.findViewById(BOTONESMENU[i]);
            final int queBoton=i;
            if(boton==i){
                botonmenu.setImageResource(BOTONESILUMINADOS[boton]);
            }
            botonmenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Activity estaActividad=getActivity();
                    ((ComunicaMenu)estaActividad).menu(queBoton);
                }
            });
        }
        return mimenu;
    }

}