/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.personal.comunitarias.BaseDeDatos.niveleducacion;

import com.example.personal.comunitarias.BaseDeDatos.estadocivil.Estadocivil;
import com.example.personal.comunitarias.DatabaseRemote.DB;
import com.example.personal.comunitarias.DatabaseRemote._Default;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Kattya Desiderio
 */
public class Niveleducacion extends _Default {
    int idniveleducacion;
    String nombre;
    String descripcion;

    public Niveleducacion() {
        idniveleducacion=-1;
        nombre="";
        descripcion="";
    }

    public Niveleducacion(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getIdniveleducacion() {
        return idniveleducacion;
    }

    public int getID_DB(String nombre){
        int id_encontrada=-1;
        DB db = new DB();
        try {
            ResultSet resultSet = db.select("SELECT * FROM cpccs.niveleducacion WHERE nombre='"+nombre+"'");
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

    //Obtener la lista de estadoCivil
    public ArrayList<Niveleducacion> getListaNivelEducacion(){
        DB db = new DB();
        ArrayList<Niveleducacion> lista = new ArrayList<>();
        try {
            ResultSet resultSet = db.select("SELECT * FROM cpccs.niveleducacion");
            if (resultSet != null){
                while (resultSet.next()){
                    Niveleducacion obj = new Niveleducacion();
                    obj.setIdniveleducacion(resultSet.getInt("id"));
                    obj.setNombre(resultSet.getString("nombre"));
                    obj.setDescripcion(resultSet.getString("descripcion"));
                    lista.add(obj);
                    obj = null;
                }
            }
        }catch (Exception ex){
            this._mensagem = ex.getMessage();
            this._status = false;
        }
        return lista;
    }

    public void setIdniveleducacion(int idniveleducacion) {
        this.idniveleducacion = idniveleducacion;
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
