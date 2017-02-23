/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.personal.comunitarias.BaseDeDatos.ciudad;

import com.example.personal.comunitarias.DatabaseRemote.DB;
import com.example.personal.comunitarias.DatabaseRemote._Default;

import java.sql.ResultSet;

/**
 *
 * @author Kattya Desiderio
 */
public class Ciudad extends _Default {
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

    public int getID_DB(String nombre){
        int id_encontrada=-1;
        DB db = new DB();
        try {
            ResultSet resultSet = db.select("SELECT * FROM cpccs.ciudad WHERE nombre='"+nombre+"'");
            if (resultSet != null) {
                id_encontrada=resultSet.getInt("id");
            }
        }catch (Exception ex){
            this._mensagem = ex.getMessage();
            this._status = false;
        }
        return id_encontrada;
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
