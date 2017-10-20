/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.personal.comunitarias.BaseDeDatos.ciudad;

import android.support.annotation.NonNull;

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
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

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

    public int getID_WS(String nombre){
        int id_encontrada=-1;
        try {
            WebServiceResolver ws= new WebServiceResolver(Constantes.WS_CIUDADES,null);
            String result=ws.makeGetPetition();
            JSONObject jsonCiudades=new JSONObject(result);
            int registros=Integer.parseInt(jsonCiudades.getString("count"));
            int paginas=registros/10;
            paginas = registros%10>0?paginas+1:paginas;
            for(int i=0;i<paginas;i++){
                WebServiceResolver wsr= new WebServiceResolver(Constantes.WS_CIUDADES+"?offset="+i*10,null);
                String p=wsr.makeGetPetition();
                JSONObject json=new JSONObject(p);
                JSONArray datosCiudades=json.getJSONArray("results");
                for(int j=0;j<datosCiudades.length();j++){
                    JSONObject item= datosCiudades.getJSONObject(j);
                    if(nombre.equals(item.getString("nombre"))){
                        id_encontrada=item.getInt("id");
                        return id_encontrada;
                    }

                }
            }
        }
        catch(MalformedURLException | JSONException e){
            e.printStackTrace();
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

    public ArrayList<Ciudad> getListaCiudadesWS(){
        ArrayList<Ciudad> lista= new ArrayList<>();
        try {
            WebServiceResolver ws= new WebServiceResolver(Constantes.WS_CIUDADES,null);
            String result=ws.makeGetPetition();
            JSONObject jsonG=new JSONObject(result);
            int registros=Integer.parseInt(jsonG.getString("count"));
            int paginas=registros/10;
            paginas = registros%10>0?paginas+1:paginas;
            for(int i=0;i<paginas;i++){
                WebServiceResolver wsr= new WebServiceResolver(Constantes.WS_CIUDADES+"?offset="+i*10,null);
                String p=wsr.makeGetPetition();
                JSONObject json=new JSONObject(p);
                JSONArray datos=json.getJSONArray("results");
                for(int j=0;j<datos.length();j++){
                    JSONObject item= datos.getJSONObject(j);
                    System.out.println("ciudad: "+item.getString("nombre"));
                    lista.add(new Ciudad(item.getInt("id"),item.getString("nombre"),item.getInt("provincia")));
                }
            }
        } catch (JSONException|MalformedURLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public ArrayList<String> getListaNombresCiudad_prov(int idProvincia){
        ArrayList<String> lista = new ArrayList<>();
        try {
            WebServiceResolver ws= new WebServiceResolver(Constantes.WS_CIUDADES,null);
            String result=ws.makeGetPetition();
            JSONObject jsonG=new JSONObject(result);
            int registros=Integer.parseInt(jsonG.getString("count"));
            int paginas=registros/10;
            paginas = registros%10>0?paginas+1:paginas;
            for(int i=0;i<paginas;i++){
                WebServiceResolver wsr= new WebServiceResolver(Constantes.WS_CIUDADES+"?offset="+i*10,null);
                String p=wsr.makeGetPetition();
                JSONObject json=new JSONObject(p);
                JSONArray datos=json.getJSONArray("results");
                for(int j=0;j<datos.length();j++){
                    JSONObject item= datos.getJSONObject(j);
                    if(idProvincia==item.getInt("provincia")){
                        lista.add(item.getString("nombre"));
                    }

                }
            }
        } catch (JSONException|MalformedURLException e) {
            e.printStackTrace();
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
