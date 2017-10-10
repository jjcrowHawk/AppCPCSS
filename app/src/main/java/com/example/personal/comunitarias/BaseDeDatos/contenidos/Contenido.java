package com.example.personal.comunitarias.BaseDeDatos.contenidos;

/**
 * Created by pc on 8/10/2017.
 */

public class Contenido {
    private String titulo,descripcion,urlVideo;

    public Contenido(String titulo, String descripcion, String urlVideo) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.urlVideo = urlVideo;
    }

    public Contenido(){

    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrlVideo() {
        return urlVideo;
    }

    public void setUrlVideo(String urlVideo) {
        this.urlVideo = urlVideo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contenido contenido = (Contenido) o;

        if (titulo != null ? !titulo.equals(contenido.titulo) : contenido.titulo != null)
            return false;
        if (descripcion != null ? !descripcion.equals(contenido.descripcion) : contenido.descripcion != null)
            return false;
        return urlVideo != null ? urlVideo.equals(contenido.urlVideo) : contenido.urlVideo == null;

    }
}
