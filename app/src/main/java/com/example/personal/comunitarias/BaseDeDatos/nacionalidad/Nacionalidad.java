/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.personal.comunitarias.BaseDeDatos.nacionalidad;

import com.example.personal.comunitarias.DatabaseRemote.Conexion;
import com.example.personal.comunitarias.DatabaseRemote._Default;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Kattya Desiderio
 */
public class Nacionalidad extends _Default {
    int idnacionalidad; //pk
    String nombre;

    public Nacionalidad() {
        idnacionalidad=-1;
        nombre="";
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
            ResultSet resultSet = st.executeQuery("SELECT * FROM nacionalidad WHERE nombre='"+nombre+"';");

            if (resultSet != null) {
                resultSet.next();
                id_encontrada=resultSet.getInt("id");
            }

            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id_encontrada;
    }

    //Obtener la lista de Nacionalidad
    public ArrayList<Nacionalidad> getListaNacionalidad() {
        ArrayList<Nacionalidad> lista = new ArrayList<Nacionalidad>();

        //Establecemos la conexión
        Conexion c = null;
        try {
            c = new Conexion();
            Connection conn= c.getConn();

            //Creamos el query
            Statement st = conn.createStatement();
            ResultSet resultSet = st.executeQuery("SELECT * FROM nacionalidad;");

            if (resultSet != null){
                while (resultSet.next()){
                    Nacionalidad obj = new Nacionalidad();
                    obj.setIdnacionalidad(resultSet.getInt("id"));
                    obj.setNombre(resultSet.getString("nombre"));
                    lista.add(obj);
                    obj = null;
                }
            }

            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    //Obtener la lista de Nacionalidad
    public ArrayList<String> getListaNacionalidadNombres() {
        ArrayList<String> lista = new ArrayList<String>();

        //Establecemos la conexión
        Conexion c = null;
        try {
            c = new Conexion();
            Connection conn= c.getConn();

            //Creamos el query
            Statement st = conn.createStatement();
            ResultSet resultSet = st.executeQuery("SELECT * FROM nacionalidad;");

            if (resultSet != null){
                while (resultSet.next()){
                    lista.add(resultSet.getString("nombre"));
                }
            }

            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public Nacionalidad(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdnacionalidad() {
        return idnacionalidad;
    }

    public void setIdnacionalidad(int idnacionalidad) {
        this.idnacionalidad = idnacionalidad;
    }
}
