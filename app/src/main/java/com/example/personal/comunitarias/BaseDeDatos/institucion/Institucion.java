/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.personal.comunitarias.BaseDeDatos.institucion;

/**
 *
 * @author Kattya Desiderio
 */
public class Institucion {
    int idinstitucion; //pk
    String url;
    String descripcion;
    String email;
    String nombre;
    String competencia; //char(5)
    String representante;  //char(5)
    String publica;
    int sectorid; //fk


    public Institucion() {
    }

    public Institucion(String competencia, String descripcion, String email, String nombre, String publica, String representante, String url) {
        this.competencia = competencia;
        this.descripcion = descripcion;
        this.email = email;
        this.nombre = nombre;
        this.publica = publica;
        this.representante = representante;
        this.url = url;
    }

    public int getIdinstitucion() {
        return idinstitucion;
    }

    public void setIdinstitucion(int idinstitucion) {
        this.idinstitucion = idinstitucion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCompetencia() {
        return competencia;
    }

    public void setCompetencia(String competencia) {
        this.competencia = competencia;
    }

    public String getRepresentante() {
        return representante;
    }

    public void setRepresentante(String representante) {
        this.representante = representante;
    }

    public String getPublica() {
        return publica;
    }

    public void setPublica(String publica) {
        this.publica = publica;
    }
    
            
            
}
