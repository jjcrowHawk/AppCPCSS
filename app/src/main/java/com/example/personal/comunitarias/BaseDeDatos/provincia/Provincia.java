/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.personal.comunitarias.BaseDeDatos.provincia;

import com.example.personal.comunitarias.DatabaseRemote.Conexion;
import com.example.personal.comunitarias.DatabaseRemote._Default;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Sianna Puente
 */
public class Provincia extends _Default {
    int idprovincia; //pk
    String nombre;
    int regionid; //fk

    public Provincia() {
        idprovincia=-1;
        regionid=-1;
        nombre="";
    }

    public Provincia(int idprovincia, String nombre, int regionid) {
        this.idprovincia = idprovincia;
        this.nombre = nombre;
        this.regionid = regionid;
    }

    public int getID_DB(String nombre){
        int id_encontrada=-1;

        //Establecemos la conexión
        Conexion c = null;
        try {
            c = new Conexion();
            Connection conn= c.getConn();

            //Creamos el query
            Statement st = conn.createStatement();
            ResultSet resultSet = st.executeQuery("SELECT * FROM provincia WHERE nombre='"+nombre+"';");

            if (resultSet != null) {
                resultSet.next();
                id_encontrada= resultSet.getInt("id");
            }
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            this._status = false;
        } catch (SQLException e) {
            e.printStackTrace();
            this._status = false;
        }
        return id_encontrada;
    }
    //Devuelve la lista de todas las provincias
    public ArrayList<Provincia> getListaProvincia()  {
        ArrayList<Provincia> lista = new ArrayList<>();

        //Establecemos la conexión
        Conexion c = null;
        try {
            c = new Conexion();
            Connection conn= c.getConn();

            //Creamos el query
            Statement st = conn.createStatement();
            ResultSet resultSet = st.executeQuery("SELECT * FROM provincia;");

            if (resultSet != null){
                while (resultSet.next()){
                    Provincia obj= new Provincia();
                    obj.setIdprovincia(resultSet.getInt("id"));
                    obj.setNombre(resultSet.getString("nombre"));
                    lista.add(obj);
                    obj = null;
                }
            }
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            this._status = false;
        } catch (SQLException e) {
            e.printStackTrace();
            this._status = false;
        }

        return lista;
    }

    //Devuelve la lista de todos nombres las provincias
    public ArrayList<String> getListaNombreProvincia(){
        ArrayList<String> lista = new ArrayList<>();

        //Establecemos la conexión
        Conexion c = null;
        try {
            c = new Conexion();
            Connection conn= c.getConn();

            //Creamos el query
            Statement st = conn.createStatement();
            ResultSet resultSet = st.executeQuery("SELECT * FROM provincia;");

            if (resultSet != null){
                while (resultSet.next()){
                    lista.add(resultSet.getString("nombre"));
                }
            }
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            this._status = false;
        } catch (SQLException e) {
            e.printStackTrace();
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
