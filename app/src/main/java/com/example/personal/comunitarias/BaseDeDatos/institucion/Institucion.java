/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.personal.comunitarias.BaseDeDatos.institucion;

import com.example.personal.comunitarias.AsynchronousTask;
import com.example.personal.comunitarias.DatabaseRemote.Conexion;
import com.example.personal.comunitarias.DatabaseRemote._Default;
import com.example.personal.comunitarias.WebService;
import com.example.personal.comunitarias.WebServiceResolver;

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
public class Institucion extends _Default {
    int idinstitucion; //pk
    String url;
    String descripcion;
    String email;
    String nombre;
    String competencia; //char(5)
    String representante;  //char(5)
    String publica;
    int sectorid; //fk


    public Institucion() {
        idinstitucion=-1;
        nombre="";
    }

    public Institucion(String competencia, String descripcion, String email, String nombre, String publica, String representante, String url) {
        this.competencia = competencia;
        this.descripcion = descripcion;
        this.email = email;
        this.nombre = nombre;
        this.publica = publica;
        this.representante = representante;
        this.url = url;
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
            ResultSet resultSet = st.executeQuery("SELECT * FROM institucion WHERE nombre='"+nombre+"';");
            if (resultSet != null) {
                resultSet.next();
                id_encontrada=resultSet.getInt("id");
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

    public ArrayList<Institucion> getListaInstitucion(){
        ArrayList<Institucion> lista = new ArrayList<Institucion>();

        //Establecemos la conexión
        Conexion c = null;
        try {
            c = new Conexion();
            Connection conn= c.getConn();

            //Creamos el query
            Statement st = conn.createStatement();
            ResultSet resultSet = st.executeQuery("SELECT * FROM institucion;");
            if (resultSet != null){
                while (resultSet.next()){
                    Institucion obj = new Institucion();
                    obj.setIdinstitucion(resultSet.getInt("id"));
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

    /*
    public ArrayList<String> getListaInstitucionNombres(){
        ArrayList<String> lista = new ArrayList<String>();
        //Establecemos la conexión
        Conexion c = null;
        try {
            c = new Conexion();
            Connection conn= c.getConn();

            //Creamos el query
            Statement st = conn.createStatement();
            ResultSet resultSet = st.executeQuery("SELECT * FROM institucion;");
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
    */
    public ArrayList<String> getListaInstitucionNombres(){
        ArrayList<String> lista=new ArrayList<String>();
        WebServiceResolver ws=new WebServiceResolver("http://custom-env.6v3gjmadmw.sa-east-1.elasticbeanstalk.com/instituciones/",null);
        String result=ws.makeGetPetition();
        try {
            JSONObject jsonInstitucion=new JSONObject(result);
            JSONArray datosInstitucion=jsonInstitucion.getJSONArray("results");
            for(int i=0;i<datosInstitucion.length();i++){
                JSONObject itemInsititucion= datosInstitucion.getJSONObject(i);
                lista.add(itemInsititucion.getString("nombre"));
                System.out.println(itemInsititucion.getString("nombre"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public int getSectorid() {
        return sectorid;
    }

    public void setSectorid(int sectorid) {
        this.sectorid = sectorid;
    }

    public int getIdinstitucion() {
        return idinstitucion;
    }

    public void setIdinstitucion(int idinstitucion) {
        this.idinstitucion = idinstitucion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCompetencia() {
        return competencia;
    }

    public void setCompetencia(String competencia) {
        this.competencia = competencia;
    }

    public String getRepresentante() {
        return representante;
    }

    public void setRepresentante(String representante) {
        this.representante = representante;
    }

    public String getPublica() {
        return publica;
    }

    public void setPublica(String publica) {
        this.publica = publica;
    }
    
            
            
}
