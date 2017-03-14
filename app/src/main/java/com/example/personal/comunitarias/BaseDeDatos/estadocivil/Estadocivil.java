/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.personal.comunitarias.BaseDeDatos.estadocivil;

import com.example.personal.comunitarias.BaseDeDatos.predenuncia.Predenuncia;
import com.example.personal.comunitarias.DatabaseRemote.DB;
import com.example.personal.comunitarias.DatabaseRemote._Default;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Kattya Desiderio
 */
public class Estadocivil extends _Default {
    int idestadocivil; //pk
    String nombre;

    public Estadocivil() {
        idestadocivil=-1;
        nombre="";
    }

    public int getID_DB(String nombre){
        int id_encontrada=-1;
        DB db = new DB();
        try {
            ResultSet resultSet = db.select("SELECT * FROM cpccs.estadocivil WHERE nombre='"+nombre+"'");
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
    public ArrayList<Estadocivil> getListaEstadoCivil(){
        DB db = new DB();
        ArrayList<Estadocivil> lista = new ArrayList<>();
        try {
            ResultSet resultSet = db.select("SELECT * FROM cpccs.estadocivil");
            if (resultSet != null){
                while (resultSet.next()){
                    Estadocivil obj = new Estadocivil();
                    obj.setIdestadocivil(resultSet.getInt("id"));
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

    public int getIdestadocivil() {
        return idestadocivil;
    }

    public void setIdestadocivil(int estadocivil) {
        this.idestadocivil = estadocivil;
    }

    public Estadocivil(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}