/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.personal.comunitarias.BaseDeDatos.reclamo;

import android.util.Log;

import com.example.personal.comunitarias.BaseDeDatos.niveleducacion.Niveleducacion;
import com.example.personal.comunitarias.Constantes;
import com.example.personal.comunitarias.DatabaseRemote.Conexion;
import com.example.personal.comunitarias.DatabaseRemote._Default;
import com.example.personal.comunitarias.WebService.WebServiceResolver;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Kattya Desiderio
 */
public class Reclamo extends _Default {
    int idreclamo; //pk
    String numero;
    String nombresapellidosdenunciante;
    String tipoidentificacion;
    String numidenti;
    String direccion;
    String email;
    String nombresapellidosdenunciado;
    String telefono;
    String cargo;
    int comparecer; //char(5)
    int documentores; //char(5)
    int identidadreservada;
    int resideextrangero;
    int ciudaddeldenuncianteid; //fk
    int ciudaddeldenunciadoid; //fk
    int institucionimplicadaid; //fk
    int provinciadenuncianteid; //fk
    int provinciadenunciadoid; //fk
    String pedidoDenuncia;

    public Reclamo() {
        super();
        this.idreclamo = -1;
        this.numero="";
        this.nombresapellidosdenunciado = "";
        this.tipoidentificacion = "";
        this.numidenti = "";
        this.direccion = "";
        this.email = "";
        this.nombresapellidosdenunciado = "";
        this.telefono = "";
        this.cargo = "";
        this.comparecer = -1;
        this.documentores = -1;
        this.identidadreservada = -1;
        this.resideextrangero = -1;
        this.ciudaddeldenunciadoid = -1; //fk
        this.ciudaddeldenunciadoid = -1; //fk
        this.institucionimplicadaid = -1; //fk
        this.provinciadenuncianteid = -1; //fk
        this.provinciadenunciadoid = -1; //fk
        this.pedidoDenuncia= "";

    }

    public int getIdreclamo() {
        return idreclamo;
    }

    public void setIdreclamo(int idreclamo) {
        this.idreclamo = idreclamo;
    }

    public Reclamo(String nombresapellidosdenunciante, String tipoidentificacion, String numidenti, String direccion, String email, String nombresapellidosdenunciado, String telefono, String cargo, int comparecer, int documentores, int identidadreservada, int resideextrangero) {
        this.nombresapellidosdenunciante = nombresapellidosdenunciante;
        this.tipoidentificacion = tipoidentificacion;
        this.numidenti = numidenti;
        this.direccion = direccion;
        this.email = email;
        this.nombresapellidosdenunciado = nombresapellidosdenunciado;
        this.telefono = telefono;
        this.cargo = cargo;
        this.comparecer = comparecer;
        this.documentores = documentores;
        this.identidadreservada = identidadreservada;
        this.resideextrangero = resideextrangero;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNombresapellidosdenunciante() {
        return nombresapellidosdenunciante;
    }

    public void setNombresapellidosdenunciante(String nombresapellidosdenunciante) {
        this.nombresapellidosdenunciante = nombresapellidosdenunciante;
    }

    public String getTipoidentificacion() {
        return tipoidentificacion;
    }

    public void setTipoidentificacion(String tipoidentificacion) {
        this.tipoidentificacion = tipoidentificacion;
    }

    public String getNumidenti() {
        return numidenti;
    }

    public void setNumidenti(String numidenti) {
        this.numidenti = numidenti;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombresapellidosdenunciado() {
        return nombresapellidosdenunciado;
    }

    public void setNombresapellidosdenunciado(String nombresapellidosdenunciado) {
        this.nombresapellidosdenunciado = nombresapellidosdenunciado;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public int getComparecer() {
        return comparecer;
    }

    public void setComparecer(int comparecer) {
        this.comparecer = comparecer;
    }

    public int getDocumentores() {
        return documentores;
    }

    public void setDocumentores(int documentores) {
        this.documentores = documentores;
    }

    public int getIdentidadreservada() {
        return identidadreservada;
    }

    public void setIdentidadreservada(int identidadreservada) {
        this.identidadreservada = identidadreservada;
    }

    public int getResideextrangero() {
        return resideextrangero;
    }

    public void setResideextrangero(int resideextrangero) {
        this.resideextrangero = resideextrangero;
    }

    public int getCiudaddeldenunciadoid() {
        return ciudaddeldenunciadoid;
    }

    public void setCiudaddeldenunciadoid(int ciudaddeldenunciadoid) {
        this.ciudaddeldenunciadoid = ciudaddeldenunciadoid;
    }

    public int getCiudaddeldenuncianteid() {
        return ciudaddeldenuncianteid;
    }

    public void setCiudaddeldenuncianteid(int ciudaddeldenuncianteid) {
        this.ciudaddeldenuncianteid = ciudaddeldenuncianteid;
    }

    public int getInstitucionimplicadaid() {
        return institucionimplicadaid;
    }

    public void setInstitucionimplicadaid(int institucionimplicadaid) {
        this.institucionimplicadaid = institucionimplicadaid;
    }

    public int getProvinciadenunciadoid() {
        return provinciadenunciadoid;
    }

    public void setProvinciadenunciadoid(int provinciadenunciadoid) {
        this.provinciadenunciadoid = provinciadenunciadoid;
    }

    public int getProvinciadenuncianteid() {
        return provinciadenuncianteid;
    }

    public void setProvinciadenuncianteid(int provinciadenuncianteid) {
        this.provinciadenuncianteid = provinciadenuncianteid;
    }

    public String getPedidoDenuncia() {
        return pedidoDenuncia;
    }

    public void setPedidoDenuncia(String pedidoDenuncia) {
        this.pedidoDenuncia = pedidoDenuncia;
    }

    /*
    public void Guardar_Reclamo() {
        Log.d("myTag", "Entre al query");
        String comando = "";
        if (this.getIdreclamo() == -1) {
        //comando = String.format(" INSERT INTO cpccs.reclamo (nombresapellidosdenunciante, tipoidentificacion, numidenti ,direccion,email,nombresapellidosdenunciado, telefono, cargo,comparecer,documentores,identidadreservada,resideextrangero,ciudaddeldenuncianteid,ciudaddeldenunciadoid,insttitucionimplicadaid,provinciadenuncianteid,provinciadenunciadoid)VALUES('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s',%d,%d,%d,%d,%d);",
          //"SIANNA", "0125455", "12345", "mapasingue", "jlcosta", "Domenica Vera", "1245", "12345", "1", "1", "1", "1", 1, 1, 1, 1, 1);

            if(getListaReclamoWS().size()==0){
                this.idreclamo=1;
            }else{
                this.idreclamo= getListaReclamoWS().get(getListaReclamoWS().size()-1).getIdreclamo()+1;
            }

            comando = String.format(" INSERT INTO reclamo (id,numero,fechaingreso,nombresapellidosdenunciante, tipoidentificacion, numidenti ,direccion,email,nombresapellidosdenunciado, telefono, cargo,comparecer,documentores,identidadreservada,resideextrangero,ciudaddenuncianteid,ciudaddenunciadoid,institucionimplicadaid,provinciadenuncianteid,provinciadenunciandoid) Values (%d,%d,now(),'%s','%s','%s','%s','%s','%s','%s','%s','%d','%d','%d','%d',%d,%d,%d,%d,%d);",
                    this.idreclamo,1,
                    this.getNombresapellidosdenunciante(),this.getTipoidentificacion(),this.getNumidenti(),this.getDireccion(),this.getEmail(),this.getNombresapellidosdenunciado(),this.getTelefono(),this.getCargo(),this.getComparecer(),this.getDocumentores(),this.getIdentidadreservada(),this.getResideextrangero(),this.getCiudaddeldenuncianteid(),this.getCiudaddeldenunciadoid(),this.getInstitucionimplicadaid(),this.getProvinciadenuncianteid(),this.getProvinciadenunciadoid());
            Log.d("Enviado  : ", this.idreclamo+","+this.getPedidoDenuncia()+this.getNombresapellidosdenunciante() + this.getTipoidentificacion() + this.getNumidenti() + this.getDireccion()+ this.getEmail() + this.getNombresapellidosdenunciado() + this.getTelefono() + this.getCargo() + ","+this.getComparecer() + ","+this.getDocumentores() + ","+this.getIdentidadreservada() + ","+this.getResideextrangero() + ","+this.getCiudaddeldenuncianteid() + ","+this.getCiudaddeldenunciadoid() + ","+this.getInstitucionimplicadaid() + ","+this.getProvinciadenuncianteid() + ","+this.getProvinciadenunciadoid());
        }

        //Establecemos la conexión
        try {
            Conexion c = new Conexion();
            Connection conn= c.getConn();

            //Creamos el query
            Statement st = conn.createStatement();
            int resultSet = st.executeUpdate(comando);
            Log.e("Reclamo result:",""+resultSet);

            conn.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            this._status = false;
        } catch (SQLException e) {
            e.printStackTrace();
            this._status = false;
        }
    }
    */

    public void Guardar_ReclamoWS(){
        if(this.getIdreclamo()==-1) {
            if(getListaReclamoWS().size()==0){
                this.idreclamo=1;
            }else{
                this.idreclamo= getListaReclamoWS().get(getListaReclamoWS().size()-1).getIdreclamo()+1;
            }
            Map<String, String> datos = new HashMap<String, String>();
            datos.put("nombres_apellidos_denunciante", this.nombresapellidosdenunciante);
            datos.put("tipo_identificacion", this.tipoidentificacion);
            datos.put("numero_identificacion", this.numidenti);
            datos.put("direccion", this.direccion);
            datos.put("email", this.email);
            datos.put("nombres_apellidos_denunciado", this.nombresapellidosdenunciado);
            datos.put("telefono", this.telefono);
            datos.put("cargo", this.cargo);
            datos.put("comparecer", (this.comparecer == 1 ? "true" : "false"));
            datos.put("documentores", (this.documentores == 1 ? "true" : "false"));
            datos.put("identidad_reservada", (this.identidadreservada == 1 ? "true" : "false"));
            datos.put("reside_extranjero", (this.resideextrangero == 1 ? "true" : "false"));
            datos.put("id",""+this.idreclamo);
            datos.put("ciudad_del_denunciante",""+this.ciudaddeldenuncianteid);
            datos.put("ciudad_del_denunciado",""+this.ciudaddeldenunciadoid);
            datos.put("institucion_implicada",""+this.institucionimplicadaid);
            datos.put("provincia_denunciante",""+this.provinciadenuncianteid);
            datos.put("provincia_denunciado",""+this.provinciadenunciadoid);
            WebServiceResolver ws=new WebServiceResolver(Constantes.WS_RECLAMOS,datos);
            System.out.println(ws.makePostPetition());

        }

    }


    //Obtener la lista de Reclamo
    /*
    public ArrayList<Reclamo> getListaReclamo()  {
        ArrayList<Reclamo> lista = new ArrayList<>();

        //Establecemos la conexión
        Conexion c = null;
        try {
            c = new Conexion();
            Connection conn= c.getConn();

            //Creamos el query
            Statement st = conn.createStatement();
            ResultSet resultSet = st.executeQuery("SELECT * FROM reclamo;");

            if (resultSet != null){
                while (resultSet.next()){
                    Reclamo obj = new Reclamo();
                    obj.setIdreclamo(resultSet.getInt("id"));
                    obj.setNumero(resultSet.getString("numero"));
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
    */

    public ArrayList<Reclamo> getListaReclamoWS(){
        ArrayList<Reclamo> lista = new ArrayList<>();
        WebServiceResolver ws= new WebServiceResolver(Constantes.WS_RECLAMOS,null);
        String result=ws.makeGetPetition();
        System.out.println(result);
        try {
            JSONObject json=new JSONObject(result);
            JSONArray arregloDatos= json.getJSONArray("results");
            for(int i=0;i<arregloDatos.length();i++){
                JSONObject item=arregloDatos.getJSONObject(i);
                Reclamo rec= new Reclamo();
                rec.setIdreclamo(item.getInt("id"));
                lista.add(rec);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
