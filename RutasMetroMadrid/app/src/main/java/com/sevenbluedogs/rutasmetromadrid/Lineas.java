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


    protected Lineas(Parcel in) {
        nombre = in.readString();
        estaciones = in.createTypedArray(Location.CREATOR);
        origenRuta = in.readInt();
        finalRuta = in.readInt();
        datosParadaOrigen = in.readDouble();
        datosParadaDestino = in.readDouble();
    }

    public static final Creator<Lineas> CREATOR = new Creator<Lineas>() {
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
        parcel.writeTypedArray(estaciones, i);
        parcel.writeInt(origenRuta);
        parcel.writeInt(finalRuta);
        parcel.writeDouble(datosParadaOrigen);
        parcel.writeDouble(datosParadaDestino);
    }
}
