package com.example.personal.comunitarias.Denuncias;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.example.personal.comunitarias.BaseDeDatos.ciudad.Ciudad;
import com.example.personal.comunitarias.BaseDeDatos.estadocivil.Estadocivil;
import com.example.personal.comunitarias.BaseDeDatos.etnia.Etnia;
import com.example.personal.comunitarias.BaseDeDatos.institucion.Institucion;
import com.example.personal.comunitarias.BaseDeDatos.nacionalidad.Nacionalidad;
import com.example.personal.comunitarias.BaseDeDatos.niveleducacion.Niveleducacion;
import com.example.personal.comunitarias.BaseDeDatos.ocupacion.Ocupacion;
import com.example.personal.comunitarias.BaseDeDatos.pais.Pais;
import com.example.personal.comunitarias.BaseDeDatos.provincia.Provincia;
import com.example.personal.comunitarias.R;

import java.util.ArrayList;

/**
 * Created by Sianna-chan on 20/05/2017.
 */

public class IntroDenuncias extends AppCompatActivity {

    private int tiempo = 10;
    int pStatus = 0;
    private Handler handler = new Handler();
    TabsDenuncia t;
    private ProgressDialog mProgressDialog;

    //Listas
    ArrayList<String> lista_estadocivil, lista_niveledu, lista_nacionalidad,
            lista_ocup, lista_prov, lista_ciudades_provincias, lista_inst, lista_etnia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        new Progress_cargando().execute();

        t = new TabsDenuncia();
        t.setLista_estadocivil(new ArrayList<String>());
        t.setLista_niveledu(new ArrayList<String>());
        t.setLista_nacionalidad(new ArrayList<String>());
        t.setLista_ocup(new ArrayList<String>());
        t.setLista_prov(new ArrayList<String>());
        t.setLista_inst(new ArrayList<String>());
        t.setLista_etnia(new ArrayList<String>());
        t.setLista_ciudades_provincias(new ArrayList<String>());
    }

    public class Progress_cargando extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            lista_estadocivil       = new Estadocivil().getListaEstadoCivilNombres();
            t.setLista_estadocivil(lista_estadocivil);
            lista_niveledu          = new Niveleducacion().getListaNivelEducacionNombres();
            t.setLista_niveledu(lista_niveledu);
            lista_etnia             = new Etnia().getListaNombresEtnia();
            t.setLista_etnia(lista_etnia);
            lista_prov              = new Provincia().getListaNombreProvincia();
            t.setLista_prov(lista_prov);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            /*Peticionario.adapter4.notifyDataSetChanged();
            Peticionario.adapter5.notifyDataSetChanged();
            Peticionario.adapterEtnia.notifyDataSetChanged();*/
            new Progress_ciudades_provincias().execute();
            Intent i=new Intent(getBaseContext(), t.getClass());
            startActivity(i);
        }
    }


    public class Progress_ciudades_provincias extends AsyncTask<Void, Void, Void>{
        @Override
        protected Void doInBackground(Void... voids) {
            lista_ciudades_provincias=new ArrayList<String>();
            for (String prov : lista_prov) {
                ArrayList<String> lista_ciudades = new Ciudad().getListaNombresCiudad_prov(new Provincia().getID_WS(prov));
                for (String ciudad : lista_ciudades) {
                    String ciudad_provincia = ciudad + ", " + prov;
                    lista_ciudades_provincias.add(ciudad_provincia);
                }
            }
            t.setLista_ciudades_provincias(lista_ciudades_provincias);
            return null;
        }

        @Override
        protected void onPostExecute(Void result){
            Peticionario.adapterautocomplate.notifyDataSetChanged();
        }
    }
}
