/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.personal.comunitarias.BaseDeDatos.provincia;

import com.example.personal.comunitarias.BaseDeDatos.ciudad.Ciudad;
import com.example.personal.comunitarias.DatabaseRemote.DB;
import com.example.personal.comunitarias.DatabaseRemote._Default;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Kattya Desiderio
 */
public class Provincia extends _Default {
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

    public int getID_DB(String nombre){
        int id_encontrada=-1;
        DB db = new DB();
        try {
            ResultSet resultSet = db.select("SELECT * FROM cpccs.provincia WHERE nombre='"+nombre+"'");
            if (resultSet != null) {
                resultSet.next();
                id_encontrada= resultSet.getInt("id");
            }
        }catch (Exception ex){
            this._mensagem = ex.getMessage();
            this._status = false;
        }
        return id_encontrada;
    }
    //Devuelve la lista de todas las provincias
    public ArrayList<Provincia> getListaProvincia(){
        DB db = new DB();
        ArrayList<Provincia> lista = new ArrayList<>();
        try {
            ResultSet resultSet = db.select("SELECT * FROM cpccs.provincia");
            if (resultSet != null){
                while (resultSet.next()){
                    Provincia obj= new Provincia();
                    obj.setIdprovincia(resultSet.getInt("id"));
                    obj.setNombre(resultSet.getString("nombre"));
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

    //Devuelve la lista de todos nombres las provincias
    public ArrayList<String> getListaNombreProvincia(){
        DB db = new DB();
        ArrayList<String> lista = new ArrayList<>();
        try {
            ResultSet resultSet = db.select("SELECT * FROM cpccs.provincia");
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
