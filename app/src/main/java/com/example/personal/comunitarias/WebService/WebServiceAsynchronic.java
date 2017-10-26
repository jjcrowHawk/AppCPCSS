package com.example.personal.comunitarias.WebService;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.personal.comunitarias.Constantes;

import java.util.Map;

/**
 * Created by pc on 15/7/2017.
 */

public class WebServiceAsynchronic extends AsyncTask<String,Long,String> {


    private Map<String, String> datos;
    //Web service URL
    private String url= "";

    //Actividad para mostrar el cuadro de progreso
    private Context actividad;

    //Resultado
    private String xml=null;

    //Clase a la cual se le retorna los datos dle ws
    private AsynchronousTask callback=null;
    private String mode;

    public AsynchronousTask getCallback() {
        return callback;
    }
    public void setCallback(AsynchronousTask callback) {
        this.callback = callback;
    }

    ProgressDialog progDailog;

    /**
     * Crea una estancia de la clase webService para hacer consultas a ws
     * @param urlWebService Url del servicio web
     * @param data Datos a enviar del servicios web
     * @param activity Actividad de donde se llama el servicio web, para mostrar el cuadro de "Cargando"
     * @param callback CLase a la que se le retornara los datos del servicio web
     */
    public WebServiceAsynchronic(String urlWebService, Map<String, String> data, Context activity, AsynchronousTask callback, String mode) {
        this.url=urlWebService;
        this.datos =data;
        this.actividad=activity;
        this.callback=callback;
        this.mode=mode;
    }

    public WebServiceAsynchronic(String urlWebService,Context activity, AsynchronousTask callback, String mode){
        this.url=urlWebService;
        this.callback=callback;
        this.actividad=activity;
        this.mode=mode;
    }

    public WebServiceAsynchronic() {
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if(actividad!=null) {
            progDailog = new ProgressDialog(actividad);
            progDailog.setMessage("Loading...");
            progDailog.setIndeterminate(false);
            progDailog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progDailog.setCancelable(false);
            progDailog.show();
        }
    }
    @Override
    protected String doInBackground(String... params) {
        try {
            if(this.mode.equals("GET")) {
                String r = HttpRequest.get(this.url).basic(Constantes.WS_AUTH_USER,Constantes.WS_AUTH_PASSWORD).body();
                return r;
            }
            else if(this.mode.equals("POST")){
                String r= HttpRequest.post(this.url).basic(Constantes.WS_AUTH_USER,Constantes.WS_AUTH_PASSWORD).form(this.datos).body();
                return r;
            }
            else{
                System.out.println("NO MODE SET!!!!!!!!!!!!!!");
            }
            return null;

        } catch (HttpRequest.HttpRequestException exception) {
            Log.e("doInBackground", exception.getMessage());

            return "Error HttpRequestException";
        } catch (Exception e) {
            Log.e("doInBackground", e.getMessage());
            return "Error Exception";
        }
    }
    @Override
    protected void onPostExecute(String response) {
        super.onPostExecute(response);
        this.xml=response;
        if(actividad!=null)
            progDailog.dismiss();
        //Retorno los datos
        callback.processFinish(this.xml);



    }
    public Map<String, String> getDatos() {
        return datos;
    }

    public void setDatos(Map<String, String> datos) {
        this.datos = datos;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Context getActividad() {
        return actividad;
    }

    public void setActividad(Context actividad) {
        this.actividad = actividad;
    }

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    public ProgressDialog getProgDailog() {
        return progDailog;
    }

    public void setProgDailog(ProgressDialog progDailog) {
        this.progDailog = progDailog;
    }

}
