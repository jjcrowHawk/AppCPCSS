/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.personal.comunitarias.BaseDeDatos.predenuncia;

/**
 *
 * @author Kattya Desiderio
 */
public class Predenuncia {
    char tipodenuncia;
    char generodenunciante;
    String descripcioninvestigacion;
    int niveleducaciondenunciateid ; //fk
    int ocupaciondenuncianteid; //fk
    int nacionalidaddenuncianteid; //fk
    int estadocivildenuncianteid; //fk
    int institucionimplicadaid; //fk
    char generodenunciado;
    String funcionariopublico; //char(5)

    public char getTipodenuncia() {
        return tipodenuncia;
    }

    public void setTipodenuncia(char tipodenuncia) {
        this.tipodenuncia = tipodenuncia;
    }

    public char getGenerodenunciante() {
        return generodenunciante;
    }

    public void setGenerodenunciante(char generodenunciante) {
        this.generodenunciante = generodenunciante;
    }

    public String getDescripcioninvestigacion() {
        return descripcioninvestigacion;
    }

    public void setDescripcioninvestigacion(String descripcioninvestigacion) {
        this.descripcioninvestigacion = descripcioninvestigacion;
    }

    public char getGenerodenunciado() {
        return generodenunciado;
    }

    public void setGenerodenunciado(char generodenunciado) {
        this.generodenunciado = generodenunciado;
    }
    
    
}
