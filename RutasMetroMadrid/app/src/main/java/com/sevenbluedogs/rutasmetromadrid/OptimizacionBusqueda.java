package com.sevenbluedogs.rutasmetromadrid;

import android.location.Location;

import com.google.android.gms.maps.internal.ILocationSourceDelegate;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

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
        InputStream entradaDatos;
        HttpURLConnection cliente;
        //Establecer conexión
        try{
        URL url=new URL("http://maps.google.com/maps/api/geocode/geocode?address=" + URLEncoder.encode(direccion,"UTF-8"));
        cliente = (HttpURLConnection) url.openConnection();
        cliente.connect();

            try {
                entradaDatos = new BufferedInputStream(cliente.getInputStream());
                leerStreamDatos(entradaDatos);
            } finally {
                cliente.disconnect();
            }

            StringBuilder cadena=new StringBuilder();
            int caracter;

            while((caracter=entradaDatos.read())!=-1){
                cadena.append((char)caracter);
            }

            //transformamos la información obtenda del flujo stream en objeto JSON
            JSONObject objetoJSON =new JSONObject(cadena.toString());
            if(!(objetoJSON.getString("status").equals("OK"))) return null;
            JSONArray direcciones=objetoJSON.getJSONArray("results");
            if(direcciones==null || direcciones.length()==0) return null;
            localizacion=getLocalizacion(direcciones.getJSONObject(0));

        }catch(Exception Manolo){
            //Aqui se capturan todos los errores

        }
        return localizacion;
    }

    private static String leerStreamDatos(InputStream entrada){
        return "";
    }

    private static Location getLocalizacion(JSONObject dire) throws Exception{
        String direccion=dire.getString("formatted_address"); //Extrae dirección
        direccion=new String(direccion.getBytes("ISO-8859-1"), "UTF-8"); //Convierte la dirección extraída a UTF-8
        Location localizacion=new Location(direccion);
        double latitud=dire.getJSONObject("geometry").getJSONObject("location").getDouble("lat"); //Extrae la latitud
        double longitud=dire.getJSONObject("geometry").getJSONObject("location").getDouble("lng"); //Extrae la longitud
        localizacion.setLatitude(latitud);
        localizacion.setLongitude(longitud);
        return localizacion;
    }
}
