package com.example.personal.comunitarias.BaseDeDatos.etnia;

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

public class Etnia {
    private int id;
    private String descripcion;

    public Etnia(){
        this.id=-1;
        this.descripcion="";
    }

    public static ArrayList<String> getListaNombresEtnia(){
        ArrayList<String> lista= new ArrayList<String>();
        WebServiceResolver ws=new WebServiceResolver(Constantes.WS_ETNIA,null);
        try{
            String result=ws.makeGetPetition();
            JSONObject jsonEtnia=new JSONObject(result);
            JSONArray datosEtnia=jsonEtnia.getJSONArray("results");
            for(int i=0;i<datosEtnia.length();i++){
                JSONObject itemEtnia= datosEtnia.getJSONObject(i);
                lista.add(itemEtnia.getString("nombre"));
                System.out.println(itemEtnia.getString("nombre"));
            }
        } catch (HttpRequest.HttpRequestException |MalformedURLException| JSONException  e) {
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
