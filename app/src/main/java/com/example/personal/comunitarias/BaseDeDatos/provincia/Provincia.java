/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.personal.comunitarias.BaseDeDatos.provincia;

/**
 *
 * @author Kattya Desiderio
 */
public class Provincia {
    int idprovincia; //pk
    String nombre;
    int regionid; //fk

    public Provincia() {

    }

    public Provincia(int idprovincia, String nombre, int regionid) {
        this.idprovincia = idprovincia;
        this.nombre = nombre;
        this.regionid = regionid;
    }

    public Provincia(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdprovincia() {
        return idprovincia;
    }

    public void setIdprovincia(int idprovincia) {
        this.idprovincia = idprovincia;
    }

    public int getRegionid() {
        return regionid;
    }

    public void setRegionid(int regionid) {
        this.regionid = regionid;
    }
}
