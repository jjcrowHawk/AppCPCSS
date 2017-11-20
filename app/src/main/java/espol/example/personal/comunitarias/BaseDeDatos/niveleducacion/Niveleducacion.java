/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.example.personal.comunitarias.BaseDeDatos.niveleducacion;

import espol.example.personal.comunitarias.Constantes;
import espol.example.personal.comunitarias.WebService.WebServiceResolver;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.util.ArrayList;

/**
 *
 * @author Kattya Desiderio
 */
public class Niveleducacion {
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

    public int getID_WS(String nombre){
        int id_encontrada=-1;
        try {
            WebServiceResolver ws= new WebServiceResolver(Constantes.WS_NIVELES,null);
            String result=ws.makeGetPetition();
            JSONObject jsonCiudades=new JSONObject(result);
            int registros=Integer.parseInt(jsonCiudades.getString("count"));
            int paginas=registros/10;
            paginas = registros%10>0?paginas+1:paginas;
            for(int i=0;i<paginas;i++){
                WebServiceResolver wsr= new WebServiceResolver(Constantes.WS_NIVELES+"?offset="+i*10,null);
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
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return id_encontrada;
    }

    public ArrayList<String> getListaNivelEducacionNombres() {
        ArrayList<String> lista=new ArrayList<String>();
        try {
            WebServiceResolver ws= new WebServiceResolver(Constantes.WS_NIVELES,null);
            String result=ws.makeGetPetition();
            JSONObject jsonG=new JSONObject(result);
            int registros=Integer.parseInt(jsonG.getString("count"));
            int paginas=registros/10;
            paginas = registros%10>0?paginas+1:paginas;
            for(int i=0;i<paginas;i++){
                WebServiceResolver wsr= new WebServiceResolver(Constantes.WS_NIVELES+"?offset="+i*10,null);
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
