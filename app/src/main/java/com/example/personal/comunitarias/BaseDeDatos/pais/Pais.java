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
        try{
            WebServiceResolver ws= new WebServiceResolver(Constantes.WS_PAIS,null);
            String result=ws.makeGetPetition();
            JSONObject jsonC=new JSONObject(result);
            int registros=Integer.parseInt(jsonC.getString("count"));
            int paginas=registros/10;
            paginas = registros%10>0?paginas+1:paginas;
            for(int i=0;i<paginas;i++) {
                WebServiceResolver wsr = new WebServiceResolver(Constantes.WS_PAIS + "?offset=" + i * 10, null);
                String p = wsr.makeGetPetition();
                JSONObject json = new JSONObject(p);
                JSONArray datos = json.getJSONArray("results");
                for (int j = 0; j < datos.length(); j++) {
                    JSONObject itemPais= datos.getJSONObject(i);
                    lista.add(itemPais.getString("nombre"));
                    System.out.println(itemPais.getString("nombre"));
                }
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
