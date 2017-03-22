package com.example.personal.comunitarias.Noticias;



public class Noticia {
    private String titulo;
    private String url;
    private String fecha , des, s_img;



    public Noticia(String titulo, String url, String fecha, String descripcion, String s_img) {
        this.titulo = titulo;
        this.url = url;
        this.fecha = fecha;
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
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
