package com.example.personal.comunitarias.BaseDeDatos.pais;

import android.util.Log;

import com.example.personal.comunitarias.Constantes;
import com.example.personal.comunitarias.WebService.HttpRequest;
import com.example.personal.comunitarias.WebService.WebServiceResolver;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.util.ArrayList;

/**
 * Created by pc on 30/7/2017.
 */

public class Pais {
    private int id;
    private String nombre;

    public Pais(){
        this.id=-1;
        this.nombre="";
    }

    public static ArrayList<String> getListaNombresPais(){
        ArrayList<String> lista= new ArrayList<String>();
        WebServiceResolver ws=new WebServiceResolver(Constantes.WS_ETNIA,null);
        try{
            String result=ws.makeGetPetition();
            JSONObject jsonPais=new JSONObject(result);
            JSONArray datosPais=jsonPais.getJSONArray("results");
            for(int i=0;i<datosPais.length();i++){
                JSONObject itemPais= datosPais.getJSONObject(i);
                lista.add(itemPais.getString("nombre"));
                System.out.println(itemPais.getString("nombre"));
            }
        } catch (HttpRequest.HttpRequestException |JSONException|MalformedURLException e) {
            e.printStackTrace();
        }
        Log.i("Logging: ",lista.toArray().toString());
        return lista;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
