/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.personal.comunitarias.BaseDeDatos.estadocivil;

import android.util.Log;

import com.example.personal.comunitarias.Constantes;
import com.example.personal.comunitarias.DatabaseRemote.Conexion;
import com.example.personal.comunitarias.DatabaseRemote._Default;
import com.example.personal.comunitarias.WebService.WebServiceResolver;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    /*
    public int getID_DB(String nombre){
        int id_encontrada=-1;

        //Establecemos la conexión
        Conexion c = null;
        try {
            c = new Conexion();
            Connection conn= c.getConn();

            //Creamos el query
            Statement st = conn.createStatement();
            ResultSet resultSet = st.executeQuery("SELECT * FROM estadocivil WHERE nombre='"+nombre+"';");

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
    */

    public int getID_WS(String nombre){
        int id_encontrada=-1;
        WebServiceResolver ws=new WebServiceResolver(Constantes.WS_ESTADOS_CIVILES,null);
        try {
            String result=ws.makeGetPetition();
            JSONObject json=new JSONObject(result);
            JSONArray arregloDatos=json.getJSONArray("results");
            for(int i=0;i<arregloDatos.length();i++){
                JSONObject item=arregloDatos.getJSONObject(i);
                if(nombre.equals(item.getString("nombre"))){
                    id_encontrada=item.getInt("id");
                    return id_encontrada;
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return id_encontrada;
    }
    //Obtener la lista de estadoCivil
    public ArrayList<Estadocivil> getListaEstadoCivil()  {
        ArrayList<Estadocivil> lista = new ArrayList<>();

        //Establecemos la conexión
        Conexion c = null;
        try {
            c = new Conexion();
            Connection conn= c.getConn();

            //Creamos el query
            Statement st = conn.createStatement();
            ResultSet resultSet = st.executeQuery("SELECT * FROM estadocivil;");

            if (resultSet != null){
                while (resultSet.next()){
                    Estadocivil obj = new Estadocivil();
                    obj.setIdestadocivil(resultSet.getInt("id"));
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

    //Obtener la lista de estadoCivil
    /*public ArrayList<String> getListaEstadoCivilNombres()  {
        ArrayList<String> lista = new ArrayList<>();
        Log.e("Funcion:","ANTES1 de entrar");
        //Establecemos la conexión
        Conexion c = null;

        try {
            c = new Conexion();
            Connection conn= c.getConn();

            //Creamos el query
            Statement st = conn.createStatement();
            ResultSet resultSet = st.executeQuery("SELECT * FROM estadocivil;");
            Log.e("Funcion:","ANTES de entrar");

            if (resultSet != null){
                while (resultSet.next()){
                    Log.e("Funcion:",resultSet.getString("nombre") );
                    lista.add(resultSet.getString("nombre"));
                }
            }
            conn.close();
        } catch (ClassNotFoundException e) {
            Log.e("ERROR: ","EstadoCivil; "+ e);
            e.printStackTrace();
            this._status = false;
        } catch (SQLException e) {
            Log.e("ERROR: ","EstadoCivil; "+ e);
            e.printStackTrace();
            this._status = false;
        }

        return lista;
    }
    */
    public ArrayList<String> getListaEstadoCivilNombres(){
        ArrayList<String> lista=new ArrayList<>();
        WebServiceResolver ws=new WebServiceResolver(Constantes.WS_ESTADOS_CIVILES,null);
        try{
            String result=ws.makeGetPetition();
            JSONObject jsonEstado=new JSONObject(result);
            JSONArray datosEstado=jsonEstado.getJSONArray("results");
            for(int i=0;i<datosEstado.length();i++){
                JSONObject itemEstado= datosEstado.getJSONObject(i);
                lista.add(itemEstado.getString("nombre"));
                System.out.println(itemEstado.getString("nombre"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Log.i("Logging: ",lista.toArray().toString());
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
