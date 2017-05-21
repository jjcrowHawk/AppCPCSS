/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.personal.comunitarias.BaseDeDatos.predenuncia;

import android.util.Log;

import com.example.personal.comunitarias.DatabaseRemote.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Sianna Puente
 */
public class Predenuncia extends _Default {
    int idpredenuncia; //pk
    String tipodenuncia; //no se sabe que va
    String generodenunciante;
    String descripcioninvestigacion;
    int niveleducaciondenunciateid ; //fk
    int ocupaciondenuncianteid; //fk
    int nacionalidaddenuncianteid; //fk
    int estadocivildenuncianteid; //fk
    int institucionimplicadaid; //fk
    String generodenunciado;
    String funcionariopublico; //char(5)
    String unidaddireccionafectada; //char(5)

    public Predenuncia (){
        super();
        this.idpredenuncia=-1;
        this.tipodenuncia="";
        this.generodenunciante="";
        this.descripcioninvestigacion="";
        this.niveleducaciondenunciateid=-1;
        this.ocupaciondenuncianteid=-1;
        this.nacionalidaddenuncianteid=-1;
        this.estadocivildenuncianteid=-1;
        this.institucionimplicadaid=-1;
        this.generodenunciado="";
        this.funcionariopublico="";

    }

    //Obtener la lista de todas las pre-denuncias
    public ArrayList<Predenuncia> getListaPredenuncia(){

        ArrayList<Predenuncia> lista = new ArrayList<>();

        //Establecemos la conexión
        Conexion c = null;
        try {
            c = new Conexion();
            Connection conn= c.getConn();

            //Creamos el query
            Statement st = conn.createStatement();
            ResultSet resultSet = st.executeQuery("SELECT * FROM predenuncia;");

            if (resultSet != null){
                while (resultSet.next()){
                    Predenuncia obj = new Predenuncia();
                    obj.setIdpredenuncia(resultSet.getInt("id"));
                    obj.setTipodenuncia(resultSet.getString("tipodenuncia"));
                    obj.setDescripcioninvestigacion(resultSet.getString("descripcioninvestigacion"));
                    obj.setGenerodenunciado(resultSet.getString("generodenunciado"));
                    obj.setFuncionariopublico(resultSet.getString("funcionariopublico"));
                    obj.setGenerodenunciante(resultSet.getString("generodenunciante"));
                    obj.setNiveleducaciondenunciateid(resultSet.getInt("niveleducaciondenuncianteid"));
                    obj.setOcupaciondenuncianteid(resultSet.getInt("ocupaciondenuncianteid"));
                    obj.setEstadocivildenuncianteid(resultSet.getInt("estadocivildenuncianteid"));
                    obj.setInstitucionimplicadaid(resultSet.getInt("institucionimplicadaid"));
                    obj.setNacionalidaddenuncianteid(resultSet.getInt("nacionalidaddenuncianteid"));
                    lista.add(obj);
                    obj = null;
                }
            }
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            this._status = false;
        } catch (SQLException e) {
            e.printStackTrace();
            this._status = false;
        }
        return lista;
    }


    public void guardarPredenuncia(){
        String comando = "";
            comando = String.format("INSERT INTO predenuncia(\n" +
                            "\t id,tipodenuncia,generodenunciante,asunto,generodenunciado, unidaddireccionafecta, niveleducaciondenuncianteid, ocupaciondenuncianteid, estadocivildenuncianteid, institucionimplicadaid, nacionalidaddenuncianteid)\n" +
                            "\tVALUES (%d,'%s','%s', '%s', '%s', '%s', %d, %d, %d, %d, %d);",
                    this.getIdpredenuncia(),this.getTipodenuncia(),this.getGenerodenunciante(),this.getDescripcioninvestigacion(),this.getGenerodenunciado(),this.getUnidaddireccionafectada(),this.getNiveleducaciondenunciateid(),this.getOcupaciondenuncianteid(),this.getEstadocivildenuncianteid(), this.getInstitucionimplicadaid(), this.getNacionalidaddenuncianteid());

        //Establecemos la conexión
        try {
            Conexion c = new Conexion();
            Connection conn= c.getConn();

            //Creamos el query
            Statement st = conn.createStatement();
            int resultSet = st.executeUpdate(comando);
            Log.e("Pred result:",""+resultSet);

            conn.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            this._status = false;
        } catch (SQLException e) {
            e.printStackTrace();
            this._status = false;
        }

    }

    public void eliminarPredenuncia(){
        String comando = String.format("DELETE FROM cpccs.predenuncia WHERE id = %d;",this.getIdpredenuncia());

        //Establecemos la conexión
        try {
            Conexion c = new Conexion();
            Connection conn= c.getConn();

            //Creamos el query
            Statement st = conn.createStatement();
            int resultSet = st.executeUpdate(comando);
            Log.e("DELETE Pred result:",""+resultSet);

            conn.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            this._status = false;
        } catch (SQLException e) {
            e.printStackTrace();
            this._status = false;
        }
    }

    public int getIdpredenuncia() {
        return idpredenuncia;
    }

    public void setIdpredenuncia(int idpredenuncia) {
        this.idpredenuncia = idpredenuncia;
    }

    public String getTipodenuncia() {
        return tipodenuncia;
    }

    public void setTipodenuncia(String tipodenuncia) {
        this.tipodenuncia = tipodenuncia;
    }

    public String getGenerodenunciante() {
        return generodenunciante;
    }

    public void setGenerodenunciante(String generodenunciante) {
        this.generodenunciante = generodenunciante;
    }

    public String getDescripcioninvestigacion() {
        return descripcioninvestigacion;
    }

    public void setDescripcioninvestigacion(String descripcioninvestigacion) {
        this.descripcioninvestigacion = descripcioninvestigacion;
    }

    public int getNiveleducaciondenunciateid() {
        return niveleducaciondenunciateid;
    }

    public void setNiveleducaciondenunciateid(int niveleducaciondenunciateid) {
        this.niveleducaciondenunciateid = niveleducaciondenunciateid;
    }

    public int getOcupaciondenuncianteid() {
        return ocupaciondenuncianteid;
    }

    public void setOcupaciondenuncianteid(int ocupaciondenuncianteid) {
        this.ocupaciondenuncianteid = ocupaciondenuncianteid;
    }

    public int getNacionalidaddenuncianteid() {
        return nacionalidaddenuncianteid;
    }

    public void setNacionalidaddenuncianteid(int nacionalidaddenuncianteid) {
        this.nacionalidaddenuncianteid = nacionalidaddenuncianteid;
    }

    public int getEstadocivildenuncianteid() {
        return estadocivildenuncianteid;
    }

    public void setEstadocivildenuncianteid(int estadocivildenuncianteid) {
        this.estadocivildenuncianteid = estadocivildenuncianteid;
    }

    public int getInstitucionimplicadaid() {
        return institucionimplicadaid;
    }

    public void setInstitucionimplicadaid(int institucionimplicadaid) {
        this.institucionimplicadaid = institucionimplicadaid;
    }

    public String getGenerodenunciado() {
        return generodenunciado;
    }

    public void setGenerodenunciado(String generodenunciado) {
        this.generodenunciado = generodenunciado;
    }

    public String getFuncionariopublico() {
        return funcionariopublico;
    }

    public void setFuncionariopublico(String funcionariopublico) {
        this.funcionariopublico = funcionariopublico;
    }

    public String getUnidaddireccionafectada() {
        return unidaddireccionafectada;
    }

    public void setUnidaddireccionafectada(String unidaddireccionafectada) {
        this.unidaddireccionafectada = unidaddireccionafectada;
    }
}
