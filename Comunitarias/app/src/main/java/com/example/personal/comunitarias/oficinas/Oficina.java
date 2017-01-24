package com.example.personal.comunitarias.oficinas;


import com.google.android.gms.maps.model.LatLng;

public class Oficina {

    private String telefono, ciudad,provincia,direccion;
    private LatLng coordenada;


    public Oficina(String provincia, String ciudad, String telefono, LatLng coordenada , String direccion) {
        this.telefono = telefono;
        this.ciudad = ciudad;
        this.provincia = provincia;
        this.coordenada = coordenada;
        this.direccion =  direccion;
    }

    public Oficina(String ciudad , String telefono, LatLng coordenada){
        this.ciudad = ciudad;
        this.telefono = telefono;
        this.coordenada = coordenada;



    }



    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }


    public LatLng getCoordenada() {
        return coordenada;
    }

    public void setCoordenada(LatLng coordenada) {
        this.coordenada = coordenada;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}

