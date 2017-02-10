/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.personal.comunitarias.BaseDeDatos.ciudad;

/**
 *
 * @author Kattya Desiderio
 */
public class Ciudad {
    int idciudad; //pk
    String nombre;
    int provinciaid; // fk

    public Ciudad() {
    }

    public Ciudad(int idciudad, String nombre, int provinciaid) {
        this.idciudad = idciudad;
        this.nombre = nombre;
        this.provinciaid = provinciaid;
    }

    public Ciudad(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdciudad() {
        return idciudad;
    }

    public void setIdciudad(int idciudad) {
        this.idciudad = idciudad;
    }

    public int getProvinciaid() {
        return provinciaid;
    }

    public void setProvinciaid(int provinciaid) {
        this.provinciaid = provinciaid;
    }
}
