/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.personal.comunitarias.BaseDeDatos.ocupacion;

/**
 *
 * @author Kattya Desiderio
 */
public class Ocupacion {
    int idocupacion; //pk
    String nombre;
    String descripcion;

    public Ocupacion() {
    }

    public Ocupacion(String descripcion, String nombre) {
        this.descripcion = descripcion;
        this.nombre = nombre;
    }

    public int getIdocupacion() {
        return idocupacion;
    }

    public void setIdocupacion(int idocupacion) {
        this.idocupacion = idocupacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
