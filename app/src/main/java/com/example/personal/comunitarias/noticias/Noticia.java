package com.example.personal.comunitarias.Noticias;



public class Noticia {
    private String titulo;
    private String url;
    private String fecha , descripcion, urlImg;



    public Noticia(String titulo, String url, String fecha, String descripcion, String urlImg) {
        this.titulo = titulo;
        this.url = url;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.urlImg = urlImg;
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
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }
}
