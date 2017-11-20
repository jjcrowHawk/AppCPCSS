/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.example.personal.comunitarias.BaseDeDatos.provincia;

import espol.example.personal.comunitarias.Constantes;
import espol.example.personal.comunitarias.WebService.WebServiceResolver;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.util.ArrayList;

/**
 *
 * @author Sianna Puente
 */
public class Provincia {
    int idprovincia; //pk
    String nombre;
    int regionid; //fk

    public Provincia() {
        idprovincia=-1;
        regionid=-1;
        nombre="";
    }

    public Provincia(int idprovincia, String nombre, int regionid) {
        this.idprovincia = idprovincia;
        this.nombre = nombre;
        this.regionid = regionid;
    }

    public Provincia(int idprovincia, String nombre){
        this.idprovincia = idprovincia;
        this.nombre = nombre;
    }

    public int getID_WS(String nombre){
        int id_encontrada=-1;
        try {
            WebServiceResolver ws= new WebServiceResolver(Constantes.WS_PROVINCIAS,null);
            String result=ws.makeGetPetition();
            JSONObject jsonCiudades=new JSONObject(result);
            int registros=Integer.parseInt(jsonCiudades.getString("count"));
            int paginas=registros/10;
            paginas = registros%10>0?paginas+1:paginas;
            for(int i=0;i<paginas;i++){
                WebServiceResolver wsr= new WebServiceResolver(Constantes.WS_PROVINCIAS+"?offset="+i*10,null);
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

    public ArrayList<Provincia> getListaProvinciasWS(){
        ArrayList<Provincia> lista=new ArrayList<>();
        try {
            WebServiceResolver ws= new WebServiceResolver(Constantes.WS_PROVINCIAS,null);
            String result=ws.makeGetPetition();
            JSONObject jsonC=new JSONObject(result);
            int registros=Integer.parseInt(jsonC.getString("count"));
            int paginas=registros/10;
            paginas = registros%10>0?paginas+1:paginas;
            for(int i=0;i<paginas;i++) {
                WebServiceResolver wsr = new WebServiceResolver(Constantes.WS_PROVINCIAS + "?offset=" + i * 10, null);
                String p = wsr.makeGetPetition();
                JSONObject json = new JSONObject(p);
                JSONArray datos = json.getJSONArray("results");
                for (int j = 0; j < datos.length(); j++) {
                    JSONObject item= datos.getJSONObject(j);
                    System.out.println(item.getString("nombre"));
                    lista.add(new Provincia(item.getInt("id"),item.getString("nombre")));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public ArrayList<String> getListaNombreProvincia(){
        ArrayList<String> lista=new ArrayList<String>();
        try {
            WebServiceResolver ws= new WebServiceResolver(Constantes.WS_PROVINCIAS,null);
            String result=ws.makeGetPetition();
            JSONObject jsonC=new JSONObject(result);
            int registros=Integer.parseInt(jsonC.getString("count"));
            int paginas=registros/10;
            paginas = registros%10>0?paginas+1:paginas;
            for(int i=0;i<paginas;i++) {
                WebServiceResolver wsr = new WebServiceResolver(Constantes.WS_PROVINCIAS + "?offset=" + i * 10, null);
                String p = wsr.makeGetPetition();
                JSONObject json = new JSONObject(p);
                JSONArray datos = json.getJSONArray("results");
                for (int j = 0; j < datos.length(); j++) {
                    JSONObject item= datos.getJSONObject(j);
                    lista.add(item.getString("nombre"));
                    System.out.println(item.getString("nombre"));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public Provincia(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdprovincia() {
        return idprovincia;
    }

    public void setIdprovincia(int idprovincia) {
        this.idprovincia = idprovincia;
    }

    public int getRegionid() {
        return regionid;
    }

    public void setRegionid(int regionid) {
        this.regionid = regionid;
    }
}
