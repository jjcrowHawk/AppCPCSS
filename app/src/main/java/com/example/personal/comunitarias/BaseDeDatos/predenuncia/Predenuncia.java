/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.personal.comunitarias.BaseDeDatos.predenuncia;

import com.example.personal.comunitarias.DatabaseRemote.*;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Kattya Desiderio
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

    //Obtener la lista de todas las per-denuncias
    public ArrayList<Predenuncia> getListaPredenuncia(){
        DB db = new DB();
        ArrayList<Predenuncia> lista = new ArrayList<>();
        try {
            ResultSet resultSet = db.select("SELECT * FROM public.usuario");
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
        }catch (Exception ex){
            this._mensagem = ex.getMessage();
            this._status = false;
        }
        return lista;
    }


    public void guardarPredenuncia(){
        String comando = "";
            comando = String.format("INSERT INTO cpccs.predenuncia(\n" +
                            "\tdescripcioninvestigacion,generodenunciado, funcionariopublico, generodenunciante,  niveleducaciondenuncianteid, ocupaciondenuncianteid, estadocivildenuncianteid, institucionimplicadaid, nacionalidaddenuncianteid)\n" +
                            "\tVALUES ('%s', '%s', '%s', '%s', '%d', '%d', '%d', '%d', '%d');",
                    this.getDescripcioninvestigacion(),this.generodenunciado, this.getFuncionariopublico(),this.getNiveleducaciondenunciateid(),this.getOcupaciondenuncianteid(),this.getEstadocivildenuncianteid(), this.getInstitucionimplicadaid(), this.getNacionalidaddenuncianteid());
        DB db = new DB();
        db.execute(comando);
        this._mensagem = db.get_mensagem();
        this._status = db.is_status();
    }

    public void eliminarPredenuncia(){
        String comando = String.format("DELETE FROM cpccs.predenuncia WHERE id = %d;",this.getIdpredenuncia());
        DB db = new DB();
        db.execute(comando);
        this._mensagem = db.get_mensagem();
        this._status = db.is_status();
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


}
