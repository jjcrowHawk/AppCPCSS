/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.personal.comunitarias.BaseDeDatos.predenuncia;

import com.example.personal.comunitarias.DatabaseRemote._Default;

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
