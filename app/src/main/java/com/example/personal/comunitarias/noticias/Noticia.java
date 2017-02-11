package com.example.personal.comunitarias.noticias;



public class Noticia {
    private String titulo;
    private String url;
    private String des, s_img;
    private int dia,mes;


    public Noticia() {
    }

    public Noticia(String titulo, String url, int dia, int mes, String descripcion, String s_img) {
        this.titulo = titulo;
        this.url = url;
        this.dia=dia;
        this.mes=mes;
        this.des = descripcion;
        this.s_img = s_img;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public String getDescripcion() {
        return des;
    }

    public void setDescripcion(String descripcion) {
        this.des = descripcion;
    }

    public String getS_img() {
        return s_img;
    }

    public void setS_img(String s_img) {
        this.s_img = s_img;
    }
}
