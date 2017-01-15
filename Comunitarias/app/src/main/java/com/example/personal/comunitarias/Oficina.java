package com.example.personal.comunitarias;


import com.google.android.gms.maps.model.LatLng;

public class Oficina {

    private String telefono, provincia;
    private LatLng coordenada;




    public Oficina(String provincia , String telefono, LatLng coordenada){
        this.provincia = provincia;
        this.telefono = telefono;
        this.coordenada = coordenada;



    }



    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }


    public LatLng getCoordenada() {
        return coordenada;
    }

    public void setCoordenada(LatLng coordenada) {
        this.coordenada = coordenada;
    }


}
