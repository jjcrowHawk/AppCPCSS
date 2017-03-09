/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.personal.comunitarias.BaseDeDatos.region;

import com.example.personal.comunitarias.DatabaseRemote.DB;
import com.example.personal.comunitarias.DatabaseRemote._Default;

import java.sql.ResultSet;

/**
 *
 * @author Kattya Desiderio
 */
public class Region extends _Default {
    int idregion; //pk
    String nombre;
    String descripcion;

    public Region(){
    }

    public Region(String nombre, String descripcion) {
        this.descripcion = descripcion;
        this.nombre = nombre;
    }

    public int getID_DB(String nombre){
        int id_encontrada=-1;
        DB db = new DB();
        try {
            ResultSet resultSet = db.select("SELECT * FROM cpccs.region WHERE nombre='"+nombre+"'");
            if (resultSet != null) {
                resultSet.next();
                id_encontrada=resultSet.getInt("id");
            }
        }catch (Exception ex){
            this._mensagem = ex.getMessage();
            this._status = false;
        }
        return id_encontrada;
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

    public int getIdregion() {
        return idregion;
    }

    public void setIdregion(int idregion) {
        this.idregion = idregion;
    }


}

