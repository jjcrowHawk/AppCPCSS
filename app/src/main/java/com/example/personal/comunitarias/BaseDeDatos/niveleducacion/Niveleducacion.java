/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.personal.comunitarias.BaseDeDatos.niveleducacion;

import com.example.personal.comunitarias.AsynchronousTask;
import com.example.personal.comunitarias.DatabaseRemote.Conexion;
import com.example.personal.comunitarias.DatabaseRemote._Default;
import com.example.personal.comunitarias.WebService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

        //Establecemos la conexión
        Conexion c = null;
        try {
            c = new Conexion();
            Connection conn= c.getConn();

            //Creamos el query
            Statement st = conn.createStatement();
            ResultSet resultSet = st.executeQuery("SELECT * FROM niveleducacion WHERE nombre='"+nombre+"';");

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

    //Obtener la lista de estadoCivil
    public ArrayList<Niveleducacion> getListaNivelEducacion()  {
        ArrayList<Niveleducacion> lista = new ArrayList<>();

        //Establecemos la conexión
        Conexion c = null;
        try {
            c = new Conexion();
            Connection conn= c.getConn();

            //Creamos el query
            Statement st = conn.createStatement();
            ResultSet resultSet = st.executeQuery("SELECT * FROM niveleducacion;");

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

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            this._status = false;
        } catch (SQLException e) {
            e.printStackTrace();
            this._status = false;
        }

        return lista;
    }

    //Obtener la lista de estadoCivil
    /*
    public ArrayList<String> getListaNivelEducacionNombres()  {
        ArrayList<String> lista = new ArrayList<>();

        //Establecemos la conexión
        Conexion c = null;
        try {
            c = new Conexion();
            Connection conn= c.getConn();

            //Creamos el query
            Statement st = conn.createStatement();
            ResultSet resultSet = st.executeQuery("SELECT * FROM niveleducacion;");

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
    */
    public ArrayList<String> getListaNivelEducacionNombres() {
        final ArrayList<String> lista=new ArrayList<String>();
        WebService ws=new WebService("http://custom-env.6v3gjmadmw.sa-east-1.elasticbeanstalk.com/niveles-educacion/", new AsynchronousTask() {
            @Override
            public void processFinish(String result) {
                try {
                    JSONObject jsonEducacion=new JSONObject(result);
                    JSONArray datosEducacion=jsonEducacion.getJSONArray("results");
                    for(int i=0;i<datosEducacion.length();i++){
                        JSONObject itemEducacion= datosEducacion.getJSONObject(i);
                        lista.add(itemEducacion.getString("nombre"));
                        System.out.println(itemEducacion.getString("nombre"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, "GET");
        ws.execute("");
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
