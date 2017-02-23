/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.personal.comunitarias.BaseDeDatos.reclamo;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.personal.comunitarias.DatabaseRemote.DB;
import com.example.personal.comunitarias.DatabaseRemote._Default;

/**
 *
 * @author Kattya Desiderio
 */
public class Reclamo extends _Default {
    int idreclamo; //pk
    String nombresapellidosdenunciante;
    String tipoidentificacion;
    String numidenti;
    String direccion;
    String email;
    String nombresapellidosdenunciado;
    String telefono;
    String cargo;
    String comparecer; //char(5)
    String documentores; //char(5)
    String identidadreservada;
    String resideextrangero;
    int ciudaddeldenuncianteid; //fk
    int ciudaddeldenunciadoid; //fk
    int institucionimplicadaid; //fk
    int provinciadenuncianteid; //fk
    int provinciadenunciadoid; //fk

    public Reclamo() {
        super();
        this.idreclamo = -1;
        this.nombresapellidosdenunciado = "";
        this.tipoidentificacion = "";
        this.numidenti = "";
        this.direccion = "";
        this.email = "";
        this.nombresapellidosdenunciado = "";
        this.telefono = "";
        this.cargo = "";
        this.comparecer = "";
        this.documentores = "";
        this.identidadreservada = "";
        this.resideextrangero = "";
        this.ciudaddeldenunciadoid = -1; //fk
        this.ciudaddeldenunciadoid = -1; //fk
        this.institucionimplicadaid = -1; //fk
        this.provinciadenuncianteid = -1; //fk
        this.provinciadenunciadoid = -1; //fk
    }

    public int getIdreclamo() {
        return idreclamo;
    }

    public void setIdreclamo(int idreclamo) {
        this.idreclamo = idreclamo;
    }

    public Reclamo(String nombresapellidosdenunciante, String tipoidentificacion, String numidenti, String direccion, String email, String nombresapellidosdenunciado, String telefono, String cargo, String comparecer, String documentores, String identidadreservada, String resideextrangero) {
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

    public String getComparecer() {
        return comparecer;
    }

    public void setComparecer(String comparecer) {
        this.comparecer = comparecer;
    }

    public String getDocumentores() {
        return documentores;
    }

    public void setDocumentores(String documentores) {
        this.documentores = documentores;
    }

    public String getIdentidadreservada() {
        return identidadreservada;
    }

    public void setIdentidadreservada(String identidadreservada) {
        this.identidadreservada = identidadreservada;
    }

    public String getResideextrangero() {
        return resideextrangero;
    }

    public void setResideextrangero(String resideextrangero) {
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

    public void Guardar_Reclamo() {
        Log.d("myTag", "Entre al query");
        String comando = "";
        //if (this.getIdreclamo() == -1) {
        comando = String.format(" INSERT INTO cpccs.reclamo (nombresapellidosdenunciante, tipoidentificacion, numidenti ,direccion,email,nombresapellidosdenunciado, telefono, cargo,comparecer,documentores,identidadreservada,resideextrangero,ciudaddeldenuncianteid,ciudaddeldenunciadoid,insttitucionimplicadaid,provinciadenuncianteid,provinciadenunciadoid)VALUES('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s',%d,%d,%d,%d,%d);",
                "KLEBER DIAZ", "0125455", "12345", "samanes", "kleber", "Domenica Vera", "1245", "12345", "1", "1", "1", "1", 1, 1, 1, 1, 1);
        //}


        // comando = String.format(" INSERT INTO cpccs.reclamo (nombresapellidosdenunciante, tipoidentificacion, numidenti ,direccion,email,nombresapellidosdenunciado, telefono, cargo,comparecer,documentores,identidadreservada,resideextrangero,ciudaddeldenuncianteid,ciudaddeldenunciadoid,insttitucionimplicadaid,provinciadenuncianteid,provinciadenunciadoid)Values('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s',%d,%d,%d,%d,%d);",
        //      this.getNombresapellidosdenunciante(),"0125455","12345",this.getDireccion(),this.getEmail(),getNombresapellidosdenunciado(),this.getTelefono(),this.getCargo(),this.getComparecer(),this.getDocumentores(),this.getIdentidadreservada(),this.getResideextrangero(),this.getCiudaddeldenuncianteid(),this.getCiudaddeldenunciadoid(),this.getInstitucionimplicadaid(),this.getProvinciadenuncianteid(),this.getCiudaddeldenunciadoid());
        DB db = new DB();
        db.execute(comando);
        this._mensagem = db.get_mensagem();
        this._status = db.is_status();
        //Log.d("myTag", _mensagem);
        //Log.d("myTag", _status + "Mi status");


    }


}
