/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.personal.comunitarias.BaseDeDatos.sector;

/**
 *
 * @author Kattya Desiderio
 */
public class Sector {
 int idsector; //pk
 String nombre;
 String descripcion;
 String control; //char(5)
 String mensaje;

    public Sector(String nombre, String descripcion, String control, String mensaje) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.control = control;
        this.mensaje = mensaje;
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

    public String getControl() {
        return control;
    }

    public void setControl(String control) {
        this.control = control;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
         
}
