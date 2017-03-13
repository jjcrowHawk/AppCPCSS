/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.personal.comunitarias.BaseDeDatos.ciudad;

import com.example.personal.comunitarias.BaseDeDatos.predenuncia.Predenuncia;
import com.example.personal.comunitarias.DatabaseRemote.DB;
import com.example.personal.comunitarias.DatabaseRemote._Default;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Kattya Desiderio
 */
public class Ciudad extends _Default {
    int idciudad; //pk
    String nombre;
    int provinciaid; // fk

    public Ciudad() {
        idciudad=-1;
        nombre="";
        provinciaid=-1;
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
                resultSet.next();
                id_encontrada=resultSet.getInt("id");
            }
        }catch (Exception ex){
            this._mensagem = ex.getMessage();
            this._status = false;
        }
        return id_encontrada;
    }


    //Obtener la lista de todas las ciudades de una provincia
    public ArrayList<Ciudad> getListaCiudad_prov(int idProvincia){
        DB db = new DB();
        ArrayList<Ciudad> lista = new ArrayList<>();
        try {
            ResultSet resultSet = db.select("SELECT * FROM cpccs.ciudad WHERE provinciaid='"+idProvincia+"'");
            if (resultSet != null){
                while (resultSet.next()){
                    Ciudad obj= new Ciudad();
                    obj.setIdciudad(resultSet.getInt("id"));
                    obj.setNombre(resultSet.getString("nombre"));
                    obj.setProvinciaid(resultSet.getInt("provinciaid"));
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

    //Obtener la lista de todos los nombreslas ciudades de una provincia
    public ArrayList<String> getListaNombresCiudad_prov(int idProvincia){
        DB db = new DB();
        ArrayList<String> lista = new ArrayList<>();
        try {
            ResultSet resultSet = db.select("SELECT * FROM cpccs.ciudad WHERE provinciaid='"+idProvincia+"'");
            if (resultSet != null){
                while (resultSet.next()){
                    lista.add(resultSet.getString("nombre"));
                }
            }
        }catch (Exception ex){
            this._mensagem = ex.getMessage();
            this._status = false;
        }
        return lista;
    }

    //Obtener la lista de todas las ciudades
    public ArrayList<Ciudad> getListaCiudad(){
        DB db = new DB();
        ArrayList<Ciudad> lista = new ArrayList<>();
        try {
            ResultSet resultSet = db.select("SELECT * FROM cpccs.ciudad");
            if (resultSet != null){
                while (resultSet.next()){
                    Ciudad obj= new Ciudad();
                    obj.setIdciudad(resultSet.getInt("id"));
                    obj.setNombre(resultSet.getString("nombre"));
                    obj.setProvinciaid(resultSet.getInt("provinciaid"));
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
