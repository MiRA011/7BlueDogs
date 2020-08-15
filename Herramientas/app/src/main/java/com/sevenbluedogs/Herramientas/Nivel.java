package com.sevenbluedogs.Herramientas;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class Nivel extends Fragment implements SensorEventListener {
    private SensorManager manejador;
    private Sensor sensor;
    private NivelPantalla pantalla;


    public Nivel() {
        // Required empty public constructor
    }

    @Override
    public final void onCreate(@Nullable Bundle savedInstanceState) {
        manejador = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        sensor = manejador.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        pantalla = new NivelPantalla(getActivity(), getResources().getDimensionPixelSize(R.dimen.maximo));
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return pantalla;
    }

    @Override
    public void onResume() {
        super.onResume();
        manejador.registerListener(this,sensor,SensorManager.SENSOR_DELAY_GAME);

    }

    @Override
    public void onPause() {
        manejador.unregisterListener(this);
        super.onPause();
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        pantalla.angulos(sensorEvent.values);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}