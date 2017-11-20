package espol.example.personal.comunitarias.Youtube;

/**
 * Created by Palacios on 06/01/2017.
 */

public final class VideoEntry {
    public  String text;
    public  String videoId;
    public  String fecha ;

    public VideoEntry(String text, String videoId) {
        this.text = text;
        this.videoId = videoId;
    }
    public VideoEntry(String text, String videoId, String fecha) {
        this.text = text;
        this.videoId = videoId;
        this.fecha = fecha;
    }


    public String getText() {
        return text;
    }

    public String getVideoId() {
        return videoId;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}