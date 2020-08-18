package com.sevenbluedogs.Herramientas;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class Linterna extends Fragment {

    private ImageView linterna;

    //
    private boolean encendida;

    public Linterna() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View miFragmento= inflater.inflate(R.layout.fragment_linterna, container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        linterna = (ImageView)miFragmento.findViewById(R.id.linterna);
        if(encendida){linterna.setImageResource(R.drawable.linterna2);}
        linterna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (encendida){

                    apagaLinterna();

                    encendida =false;

                }else{

                    enciendeLinterna();

                    encendida =true;

                }

            }
        });
        return miFragmento;
    }

    public void  enciendeLinterna(){

        linterna.setImageResource(R.drawable.linterna2);

        Activity estaActividad=getActivity();
        ((ManejaFlashCamara)estaActividad).enciendeApaga(encendida);

    }

    public void apagaLinterna(){

        linterna.setImageResource(R.drawable.linterna);

        Activity estaActividad=getActivity();
        ((ManejaFlashCamara)estaActividad).enciendeApaga(encendida);

    }
}