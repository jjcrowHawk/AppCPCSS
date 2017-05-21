/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.personal.comunitarias.BaseDeDatos.ciudad;

import com.example.personal.comunitarias.DatabaseRemote.Conexion;
import com.example.personal.comunitarias.DatabaseRemote._Default;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 *  @author Sianna Puente
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

        //Establecemos la conexión
        Conexion c = null;
        try {
            c = new Conexion();
            Connection conn= c.getConn();

            //Creamos el query
            Statement st = conn.createStatement();
            ResultSet resultSet = st.executeQuery("SELECT * FROM ciudad WHERE nombre='"+nombre+"';");
            if (resultSet != null) {
                resultSet.next();
                id_encontrada=resultSet.getInt("id");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            this._status = false;
        } catch (SQLException e) {
            e.printStackTrace();
            this._status = false;
        }

        return id_encontrada;
    }


    //Obtener la lista de todas las ciudades de una provincia
    public ArrayList<Ciudad> getListaCiudad_prov(int idProvincia){
        ArrayList<Ciudad> lista = new ArrayList<>();

        //Establecemos la conexión
        Conexion c = null;
        try {
            c = new Conexion();
            Connection conn= c.getConn();

            //Creamos el query
            Statement st = conn.createStatement();
            ResultSet resultSet = st.executeQuery("SELECT * FROM ciudad WHERE provinciaid='"+idProvincia+"';");
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

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            this._status = false;
        } catch (SQLException e) {
            e.printStackTrace();
            this._status = false;
        }
        return lista;
    }

    //Obtener la lista de todos los nombreslas ciudades de una provincia
    public ArrayList<String> getListaNombresCiudad_prov(int idProvincia){
        ArrayList<String> lista = new ArrayList<>();

        //Establecemos la conexión
        Conexion c = null;
        try {
            c = new Conexion();
            Connection conn= c.getConn();

            //Creamos el query
            Statement st = conn.createStatement();
            ResultSet resultSet = st.executeQuery("SELECT * FROM ciudad WHERE provinciaid='"+idProvincia+"';");
            if (resultSet != null){
                while (resultSet.next()){
                    lista.add(resultSet.getString("nombre"));
                }
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            this._status = false;
        } catch (SQLException e) {
            e.printStackTrace();
            this._status = false;
        }
        return lista;
    }

    //Obtener la lista de todas las ciudades
    public ArrayList<Ciudad> getListaCiudad() {
        ArrayList<Ciudad> lista = new ArrayList<>();

        //Establecemos la conexión
        Conexion c = null;
        try {
            c = new Conexion();
            Connection conn= c.getConn();

            //Creamos el query
            Statement st = conn.createStatement();
            ResultSet resultSet = st.executeQuery("SELECT * FROM ciudad;");
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

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            this._status = false;
        } catch (SQLException e) {
            e.printStackTrace();
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
