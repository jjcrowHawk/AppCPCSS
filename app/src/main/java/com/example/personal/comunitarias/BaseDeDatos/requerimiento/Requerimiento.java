package com.example.personal.comunitarias.BaseDeDatos.requerimiento;

import com.example.personal.comunitarias.BaseDeDatos.provincia.Provincia;
import com.example.personal.comunitarias.Constantes;
import com.example.personal.comunitarias.WebService.HttpRequest;
import com.example.personal.comunitarias.WebService.WebServiceResolver;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by pc on 24/10/2017.
 */

public class Requerimiento {

    int tipoDenuncia, idDenuncia;
    boolean identidadReservada;
    String nombresDenunciante,edadDenunciante,correoDenunciante,telefonoDenunciante,celularDenunciante,direccionDenunciante,institucionDenunciante,cargoDenunciante,tipoIdentificacion,identificacionID;
    String pais,descripcionInvestigación;
    int provinciaDenunciante, ciudadDenunciante, generoDenunciante, estadoDenunciante,etniaDenunciante,educacionDenunciante;
    String nombresDenunciado, institucionDenunciado, cargoDenunciado, parroquiaDenunciado;
    int generoDenunciado,provinciaDenunciado,ciudadDenunciado;
    File evidencia;
    boolean status;

    public Requerimiento(){

    }

    public void guardarRequerimientoWS(){
        Map<String,String> datos=new HashMap<String,String>();
        try {
            datos.put("tipodenuncia", ""+this.tipoDenuncia);
            datos.put("identidad_reservada", ""+this.identidadReservada);
            datos.put("nombres_apellidos_denunciante", ""+this.nombresDenunciante);
            datos.put("edad_denunciante", this.edadDenunciante);
            datos.put("correo_denunciante", this.correoDenunciante);
            datos.put("telefono_denunciante", this.telefonoDenunciante);
            datos.put("celular_denunciante", this.celularDenunciante);
            datos.put("direccion_denunciante", "" + this.direccionDenunciante);
            datos.put("provincia_denunciante", "" + this.provinciaDenunciante);
            datos.put("ciudad_denunciante", "" + this.ciudadDenunciante);
            datos.put("genero_denunciante", "" + this.generoDenunciante);
            datos.put("estado_civil_denunciante",""+this.estadoDenunciante);
            datos.put("etnia_denunciante",""+this.etniaDenunciante);
            datos.put("niveleducaciondenunciante",""+this.educacionDenunciante);
            datos.put("institucion_denunciante",this.institucionDenunciante);
            datos.put("cargo_denunciante",this.cargoDenunciante);
            datos.put("tipo_identificacion",this.tipoIdentificacion);
            datos.put("identificacion_id",this.identificacionID);
            datos.put("pais",this.pais);
            datos.put("descripcion",this.descripcionInvestigación);
            datos.put("nombres_apellidos_denunciado",this.nombresDenunciado);
            datos.put("genero_denunciado",""+this.generoDenunciado);
            datos.put("institucion_denunciado",this.institucionDenunciado);
            datos.put("cargo_denunciado",this.cargoDenunciado);
            datos.put("provincia_denunciado",""+this.provinciaDenunciado);
            datos.put("ciudad_denunciado",""+this.ciudadDenunciado);
            datos.put("parroquia_denunciado",this.parroquiaDenunciado);

            WebServiceResolver ws = new WebServiceResolver(Constantes.WS_REQUERIMIENTOS, datos);
            System.out.println("RESPONSE BEFORE PETITION: "+ws.getResponse());
            System.out.println("RESPONSE OF PETITION: "+ws.makePostPetition());
            String idPred=""+getCantidadDenuncias();
            if(this.evidencia!=null) {
                System.out.println("POSTING FILE.........");
                String nombreArchivo=evidencia.toString().substring(evidencia.toString().lastIndexOf("/")+1);
                HttpRequest request=HttpRequest.post(Constantes.WS_EVIDENCIAS).basic(Constantes.WS_AUTH_USER,Constantes.WS_AUTH_PASSWORD)
                        .part("denuncia", idPred)
                        .part("archivo",nombreArchivo ,this.evidencia);
                JSONObject jsonArchivo= new JSONObject(request.body());
                System.out.println(jsonArchivo.toString());
            }
            this.status=true;
        }
        catch(MalformedURLException e){
            e.printStackTrace();
            this.status=false;
        }
        catch(JSONException e){
            e.printStackTrace();
            this.status=false;
        }
        catch(Exception e){
            e.printStackTrace();
            this.status=false;
        }
    }

    public int getCantidadDenuncias(){
        int contador=0;
        try {
            WebServiceResolver ws = new WebServiceResolver(Constantes.WS_REQUERIMIENTOS,null);
            String result=ws.makeGetPetition();
            JSONObject jsonC=new JSONObject(result);
            int registros=Integer.parseInt(jsonC.getString("count"));
            int paginas=registros/10;
            paginas = registros%10>0?paginas+1:paginas;
            for(int i=0;i<paginas;i++) {
                WebServiceResolver wsr = new WebServiceResolver(Constantes.WS_REQUERIMIENTOS + "?offset=" + i * 10, null);
                String p = wsr.makeGetPetition();
                JSONObject json = new JSONObject(p);
                JSONArray datos = json.getJSONArray("results");
                for (int j = 0; j < datos.length(); j++) {
                    contador++;
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return contador;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getTipoDenuncia() {
        return tipoDenuncia;
    }

    public void setTipoDenuncia(int tipoDenuncia) {
        this.tipoDenuncia = tipoDenuncia;
    }

    public boolean isIdentidadReservada() {
        return identidadReservada;
    }

    public void setIdentidadReservada(boolean identidadReservada) {
        this.identidadReservada = identidadReservada;
    }

    public String getNombresDenunciante() {
        return nombresDenunciante;
    }

    public void setNombresDenunciante(String nombresDenunciante) {
        this.nombresDenunciante = nombresDenunciante;
    }

    public String getEdadDenunciante() {
        return edadDenunciante;
    }

    public void setEdadDenunciante(String edadDenunciante) {
        this.edadDenunciante = edadDenunciante;
    }

    public String getCorreoDenunciante() {
        return correoDenunciante;
    }

    public void setCorreoDenunciante(String correoDenunciante) {
        this.correoDenunciante = correoDenunciante;
    }

    public String getTelefonoDenunciante() {
        return telefonoDenunciante;
    }

    public void setTelefonoDenunciante(String telefonoDenunciante) {
        this.telefonoDenunciante = telefonoDenunciante;
    }

    public String getCelularDenunciante() {
        return celularDenunciante;
    }

    public void setCelularDenunciante(String celularDenunciante) {
        this.celularDenunciante = celularDenunciante;
    }

    public String getDireccionDenunciante() {
        return direccionDenunciante;
    }

    public void setDireccionDenunciante(String direccionDenunciante) {
        this.direccionDenunciante = direccionDenunciante;
    }

    public String getInstitucionDenunciante() {
        return institucionDenunciante;
    }

    public void setInstitucionDenunciante(String institucionDenunciante) {
        this.institucionDenunciante = institucionDenunciante;
    }

    public String getCargoDenunciante() {
        return cargoDenunciante;
    }

    public void setCargoDenunciante(String cargoDenunciante) {
        this.cargoDenunciante = cargoDenunciante;
    }

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getIdentificacionID() {
        return identificacionID;
    }

    public void setIdentificacionID(String identificacionID) {
        this.identificacionID = identificacionID;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getDescripcionInvestigación() {
        return descripcionInvestigación;
    }

    public void setDescripcionInvestigación(String descripcionInvestigación) {
        this.descripcionInvestigación = descripcionInvestigación;
    }

    public int getProvinciaDenunciante() {
        return provinciaDenunciante;
    }

    public void setProvinciaDenunciante(int provinciaDenunciante) {
        this.provinciaDenunciante = provinciaDenunciante;
    }

    public int getCiudadDenunciante() {
        return ciudadDenunciante;
    }

    public void setCiudadDenunciante(int ciudadDenunciante) {
        this.ciudadDenunciante = ciudadDenunciante;
    }

    public int getGeneroDenunciante() {
        return generoDenunciante;
    }

    public void setGeneroDenunciante(int generoDenunciante) {
        this.generoDenunciante = generoDenunciante;
    }

    public int getEstadoDenunciante() {
        return estadoDenunciante;
    }

    public void setEstadoDenunciante(int estadoDenunciante) {
        this.estadoDenunciante = estadoDenunciante;
    }

    public int getEtniaDenunciante() {
        return etniaDenunciante;
    }

    public void setEtniaDenunciante(int etniaDenunciante) {
        this.etniaDenunciante = etniaDenunciante;
    }

    public int getEducacionDenunciante() {
        return educacionDenunciante;
    }

    public void setEducacionDenunciante(int educacionDenunciante) {
        this.educacionDenunciante = educacionDenunciante;
    }

    public int getGeneroDenunciado() {
        return generoDenunciado;
    }

    public void setGeneroDenunciado(int generoDenunciado) {
        this.generoDenunciado = generoDenunciado;
    }

    public String getInstitucionDenunciado() {
        return institucionDenunciado;
    }

    public void setInstitucionDenunciado(String institucionDenunciado) {
        this.institucionDenunciado = institucionDenunciado;
    }

    public String getCargoDenunciado() {
        return cargoDenunciado;
    }

    public void setCargoDenunciado(String cargoDenunciado) {
        this.cargoDenunciado = cargoDenunciado;
    }

    public String getParroquiaDenunciado() {
        return parroquiaDenunciado;
    }

    public void setParroquiaDenunciado(String parroquiaDenunciado) {
        this.parroquiaDenunciado = parroquiaDenunciado;
    }

    public int getProvinciaDenunciado() {
        return provinciaDenunciado;
    }

    public void setProvinciaDenunciado(int provinciaDenunciado) {
        this.provinciaDenunciado = provinciaDenunciado;
    }

    public int getCiudadDenunciado() {
        return ciudadDenunciado;
    }

    public void setCiudadDenunciado(int ciudadDenunciado) {
        this.ciudadDenunciado = ciudadDenunciado;
    }

    public File getEvidencia() {
        return evidencia;
    }

    public void setEvidencia(File evidencia) {
        this.evidencia = evidencia;
    }

    public String getNombresDenunciado() {
        return nombresDenunciado;
    }

    public void setNombresDenunciado(String nombresDenunciado) {
        this.nombresDenunciado = nombresDenunciado;
    }
}
