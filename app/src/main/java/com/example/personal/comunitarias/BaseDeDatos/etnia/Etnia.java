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

    public ArrayList<String> getListaNombresEtnia(){
        ArrayList<String> lista= new ArrayList<String>();
        try{
            WebServiceResolver ws= new WebServiceResolver(Constantes.WS_ETNIA,null);
            String result=ws.makeGetPetition();
            JSONObject jsonEtnia=new JSONObject(result);
            int registros=Integer.parseInt(jsonEtnia.getString("count"));
            int paginas=registros/10;
            paginas = registros%10>0?paginas+1:paginas;
            for(int i=0;i<paginas;i++){
                WebServiceResolver wsr= new WebServiceResolver(Constantes.WS_ETNIA+"?offset="+i*10,null);
                String p=wsr.makeGetPetition();
                JSONObject json=new JSONObject(p);
                JSONArray datosEtnias=json.getJSONArray("results");
                for(int j=0;j<datosEtnias.length();j++){
                    JSONObject item= datosEtnias.getJSONObject(j);
                    lista.add(item.getString("nombre"));
                }
            }
        } catch (HttpRequest.HttpRequestException |MalformedURLException| JSONException  e) {
            e.printStackTrace();
        }
        Log.i("Logging: ",lista.toArray().toString());
        return lista;
    }
    public int getID_WS(String nombre){
        int id_encontrada=-1;
        try{
            WebServiceResolver ws= new WebServiceResolver(Constantes.WS_ETNIA,null);
            String result=ws.makeGetPetition();
            JSONObject jsonEtnia=new JSONObject(result);
            int registros=Integer.parseInt(jsonEtnia.getString("count"));
            int paginas=registros/10;
            paginas = registros%10>0?paginas+1:paginas;
            for(int i=0;i<paginas;i++){
                WebServiceResolver wsr= new WebServiceResolver(Constantes.WS_ETNIA+"?offset="+i*10,null);
                String p=wsr.makeGetPetition();
                JSONObject json=new JSONObject(p);
                JSONArray datosEtnias=json.getJSONArray("results");
                for(int j=0;j<datosEtnias.length();j++){
                    JSONObject item= datosEtnias.getJSONObject(j);
                    if(nombre.equals(item.getString("nombre"))){
                        id_encontrada=item.getInt("id");
                        return id_encontrada;
                    }
                }
            }
        }catch(MalformedURLException | JSONException e){
            e.printStackTrace();
        }
        return id_encontrada;
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
