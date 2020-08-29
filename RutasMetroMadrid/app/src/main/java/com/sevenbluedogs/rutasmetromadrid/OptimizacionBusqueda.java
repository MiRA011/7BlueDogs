package com.sevenbluedogs.rutasmetromadrid;

import android.location.Location;

import com.google.android.gms.maps.internal.ILocationSourceDelegate;

public class OptimizacionBusqueda {

    public static Location busca(String direccion){
        Location centroCiudad = new Location("");
        centroCiudad.setLatitude(40.4381311);
        centroCiudad.setLongitude(-3.8196205);

        direccion = direccion + " , Madrid";
        Location localizacion=null;

        try{
            localizacion=consultaLocalizacion("calle "+direccion, centroCiudad);
            return  localizacion;
        }catch (Exception e) {
            return null;
        }
    }


    private static Location consultaLocalizacion(String direccion, Location centroCiudad){
        Location localizacion = null;
        return localizacion;
    }
}
