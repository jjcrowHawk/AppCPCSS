package com.example.personal.comunitarias.WebService;

import com.example.personal.comunitarias.Constantes;
import com.example.personal.comunitarias.WebServiceAsynchronic;

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

    public String makeGetPetition(){
        response = HttpRequest.get(this.url).basic(Constantes.WS_AUTH_USER,Constantes.WS_AUTH_PASSWORD).body();
        return this.response;
    }

    public String makePostPetition(){
        response= HttpRequest.post(this.url).basic(Constantes.WS_AUTH_USER,Constantes.WS_AUTH_PASSWORD).form(this.datos).body();
        return this.response;
    }
}
