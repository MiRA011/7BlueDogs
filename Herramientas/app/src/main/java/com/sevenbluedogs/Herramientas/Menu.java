package com.sevenbluedogs.Herramientas;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class Menu extends Fragment {

    public final int[] BOTONESMENU={R.id.linterna, R.id.musica,R.id.nivel};

    public Menu() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mimenu= inflater.inflate(R.layout.fragment_menu, container, false);

        ImageButton botonmenu;

        for(int i=0;i<BOTONESMENU.length;i++){

            botonmenu= (ImageButton) mimenu.findViewById(BOTONESMENU[i]);

            final int queBoton=i;

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