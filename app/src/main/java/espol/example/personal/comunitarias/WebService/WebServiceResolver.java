package espol.example.personal.comunitarias.WebService;

import espol.example.personal.comunitarias.Constantes;

import java.net.MalformedURLException;
import java.util.Map;

/**
 * Created by pc on 23/7/2017.
 */

public class WebServiceResolver {
    private Map<String, String> datos;
    //Web service URL
    private String url= "";
    private String response="";

    public WebServiceResolver(String url, Map<String,String> datos){
        this.url=url;
        this.datos=datos;
    }

    public String makeGetPetition() throws MalformedURLException,HttpRequest.HttpRequestException {
        response = HttpRequest.get(this.url).basic(Constantes.WS_AUTH_USER,Constantes.WS_AUTH_PASSWORD).body();
        return this.response;
    }

    public String makePostPetition() throws MalformedURLException,HttpRequest.HttpRequestException {
        response= HttpRequest.post(this.url).basic(Constantes.WS_AUTH_USER,Constantes.WS_AUTH_PASSWORD).form(this.datos).body();
        return this.response;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
