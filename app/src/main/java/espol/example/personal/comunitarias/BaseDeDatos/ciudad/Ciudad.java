/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.example.personal.comunitarias.BaseDeDatos.ciudad;

import espol.example.personal.comunitarias.Constantes;
import espol.example.personal.comunitarias.WebService.WebServiceResolver;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.util.ArrayList;

/**
 *
 *  @author Sianna Puente
 */
public class Ciudad {
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
