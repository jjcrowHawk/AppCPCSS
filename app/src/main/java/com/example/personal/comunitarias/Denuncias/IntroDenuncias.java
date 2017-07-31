package com.example.personal.comunitarias.Denuncias;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

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

    private ProgressDialog mProgressDialog;

    //Listas
    ArrayList<String> lista_estadocivil, lista_niveledu, lista_nacionalidad,
            lista_ocup, lista_prov, lista_ciudad, lista_inst, lista_etnia, lista_pais;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        new Progress_cargando().execute();

    }

    public class Progress_cargando extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            lista_estadocivil       = new Estadocivil().getListaEstadoCivilNombres();
            lista_niveledu          = new Niveleducacion().getListaNivelEducacionNombres();
            lista_nacionalidad      = new Nacionalidad().getListaNacionalidadNombres();
            lista_ocup              = new Ocupacion().getListaOcupacionNombres();
            lista_prov              = new Provincia().getListaNombreProvincia();
            lista_inst              = new Institucion().getListaInstitucionNombres();
            lista_etnia             = Etnia.getListaNombresEtnia();
            lista_pais              = Pais.getListaNombresPais();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

            TabsDenuncia t = new TabsDenuncia();
            t.setLista_estadocivil(lista_estadocivil);
            t.setLista_niveledu(lista_niveledu);
            t.setLista_nacionalidad(lista_nacionalidad);
            t.setLista_ocup(lista_ocup);
            t.setLista_prov(lista_prov);
            t.setLista_inst(lista_inst);
            t.setLista_pais(lista_pais);
            t.setLista_etnia(lista_etnia);
            Intent i=new Intent(getBaseContext(), t.getClass());
            startActivity(i);
        }
    }

}
