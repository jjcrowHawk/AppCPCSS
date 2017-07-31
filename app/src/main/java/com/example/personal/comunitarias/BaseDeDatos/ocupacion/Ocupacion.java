/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.personal.comunitarias.BaseDeDatos.ocupacion;

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
public class Ocupacion extends _Default {
    int idocupacion; //pk
    String nombre;
    String descripcion;

    public Ocupacion() {
        idocupacion=-1;
        nombre="";
        descripcion="";
    }

    public Ocupacion(String descripcion, String nombre) {
        this.descripcion = descripcion;
        this.nombre = nombre;
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
            ResultSet resultSet = st.executeQuery("SELECT * FROM ocupacion WHERE nombre='"+nombre+"';");

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
    */

    public int getID_WS(String nombre){
        int id_encontrada=-1;
        try {
            WebServiceResolver ws= new WebServiceResolver(Constantes.WS_OCUPACIONES,null);
            String result=ws.makeGetPetition();
            JSONObject jsonC=new JSONObject(result);
            int registros=Integer.parseInt(jsonC.getString("count"));
            int paginas=registros/10;
            paginas = registros%10>0?paginas+1:paginas;
            for(int i=0;i<paginas;i++) {
                WebServiceResolver wsr = new WebServiceResolver(Constantes.WS_OCUPACIONES + "?offset=" + i * 10, null);
                String p = wsr.makeGetPetition();
                JSONObject json = new JSONObject(p);
                JSONArray datos = json.getJSONArray("results");
                for (int j = 0; j < datos.length(); j++) {
                    JSONObject item = datos.getJSONObject(j);
                    if (nombre.toLowerCase().equals(item.getString("nombre").toLowerCase())) {
                        id_encontrada = item.getInt("id");
                        return id_encontrada;
                    }

                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return id_encontrada;
    }

    //Obtener la lista de Ocupacion
    public ArrayList<Ocupacion> getListaOcupacion() {
        ArrayList<Ocupacion> lista = new ArrayList<Ocupacion>();

        //Establecemos la conexión
        Conexion c = null;
        try {
            c = new Conexion();
            Connection conn= c.getConn();

            //Creamos el query
            Statement st = conn.createStatement();
            ResultSet resultSet = st.executeQuery("SELECT * FROM ocupacion;");

            if (resultSet != null){
                while (resultSet.next()){
                    Ocupacion obj = new Ocupacion();
                    obj.setIdocupacion(resultSet.getInt("id"));
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

    public ArrayList<String> getListaOcupacionNombres() {
        ArrayList<String> lista=new ArrayList<String>();
        try {
            WebServiceResolver ws= new WebServiceResolver(Constantes.WS_OCUPACIONES,null);
            String result=ws.makeGetPetition();
            JSONObject jsonG=new JSONObject(result);
            int registros=Integer.parseInt(jsonG.getString("count"));
            int paginas=registros/10;
            paginas = registros%10>0?paginas+1:paginas;
            for(int i=0;i<paginas;i++){
                WebServiceResolver wsr= new WebServiceResolver(Constantes.WS_OCUPACIONES+"?offset="+i*10,null);
                String p=wsr.makeGetPetition();
                JSONObject json=new JSONObject(p);
                JSONArray datos=json.getJSONArray("results");
                for(int j=0;j<datos.length();j++){
                    JSONObject item= datos.getJSONObject(j);
                    lista.add(item.getString("nombre"));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public int getIdocupacion() {
        return idocupacion;
    }

    public void setIdocupacion(int idocupacion) {
        this.idocupacion = idocupacion;
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
