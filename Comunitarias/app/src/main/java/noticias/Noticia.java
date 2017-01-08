package noticias;



public class Noticia {
    private String titulo;
    private String url;
    private String fecha;

    public Noticia(String titulo, String url, String fecha) {
        this.titulo = titulo;
        this.url = url;
        this.fecha = fecha;
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
}
