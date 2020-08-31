package com.sevenbluedogs.rutasmetromadrid;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;

public class Lineas implements Parcelable {

    public String nombre;
    public Location[] estaciones;
    public int origenRuta;
    public int finalRuta;
    public double datosParadaOrigen;
    public double datosParadaDestino;


    public Lineas(){}

    protected Lineas(Parcel in) {
        nombre = in.readString();
        estaciones = in.createTypedArray(Location.CREATOR);
        origenRuta = in.readInt();
        finalRuta = in.readInt();
        datosParadaOrigen = in.readDouble();
        datosParadaDestino = in.readDouble();
    }

    public void distancias (Location origen, Location destino){
        datosParadaOrigen =origen.distanceTo(estaciones[0]);
        datosParadaDestino =destino.distanceTo(estaciones[0]);

        //Buscamos las estaciones mas cercanas al origen y destino que nos proporcionan por parametros
        for(int i = 1; i<estaciones.length;i++){

            if(origen.distanceTo(estaciones[i])<datosParadaOrigen){
                origenRuta=i;
                datosParadaOrigen =origen.distanceTo(estaciones[i]);
            }

            if(destino.distanceTo(estaciones[i])<datosParadaDestino){
                finalRuta=i;
                datosParadaDestino =destino.distanceTo(estaciones[i]);
            }
            
        }

    }

    public static final Parcelable.Creator<Lineas> CREATOR = new Parcelable.Creator<Lineas>() {
        @Override
        public Lineas createFromParcel(Parcel in) {
            return new Lineas(in);
        }

        @Override
        public Lineas[] newArray(int size) {
            return new Lineas[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nombre);
        parcel.writeTypedArray(estaciones, 0);
        parcel.writeInt(origenRuta);
        parcel.writeInt(finalRuta);
        parcel.writeDouble(datosParadaOrigen);
        parcel.writeDouble(datosParadaDestino);
    }
}
