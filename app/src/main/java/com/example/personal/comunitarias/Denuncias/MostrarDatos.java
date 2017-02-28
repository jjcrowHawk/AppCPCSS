package com.example.personal.comunitarias.Denuncias;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

import com.example.personal.comunitarias.BaseDeDatos.ciudad.Ciudad;
import com.example.personal.comunitarias.BaseDeDatos.institucion.Institucion;
import com.example.personal.comunitarias.BaseDeDatos.predenuncia.Predenuncia;
import com.example.personal.comunitarias.BaseDeDatos.provincia.Provincia;
import com.example.personal.comunitarias.BaseDeDatos.reclamo.Reclamo;
import com.example.personal.comunitarias.R;

/**
 * Created by PC-JANINA on 26/2/2017.
 */
public class MostrarDatos extends Fragment implements AdapterView.OnItemSelectedListener {


    /******/
    private ViewPager viewPager;

    private View view;
    private TabsDenuncia tabs;
    Button guardar;
    EditText m_txtNombrePet, m_txtApellidoPet, txtIdent, txtCorreo, txtNombreDenunciado, txtApellidoDenunciado, txtDenuncia;

    public  MostrarDatos(){

    }

    public MostrarDatos(ViewPager viewPager) {
        this.viewPager = viewPager;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.denuncia_tab4_mostrar_dat,container,false);
        inicializar();
        deshabilarCampos();
        view.findViewById(R.id.anterior).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                viewPager.setCurrentItem(2);
            }
        });


        return  view;
    }

   public void inicializar() {
       m_txtNombrePet= (EditText)view.findViewById(R.id.m_txt_Nombres);
       m_txtApellidoPet= (EditText)view.findViewById(R.id.m_txt_Apellidos);
       txtIdent  = (EditText)view.findViewById(R.id.txt_tipoIdentificacion);
       txtCorreo = (EditText)view.findViewById(R.id.txt_correo);
       txtNombreDenunciado = (EditText)view.findViewById(R.id.txt_nombreDenunciado);
       txtApellidoDenunciado = (EditText)view.findViewById(R.id.txt_ApellidosDenunciado);
       //txtDenuncia = (EditText)view.findViewById(R.id.m_txt_descripcion);

       guardar = (Button) view.findViewById(R.id.enviar);


    }

    public void deshabilarCampos(){
        m_txtNombrePet.setEnabled(false);
        m_txtApellidoPet.setEnabled(false);
        txtIdent.setEnabled(false);
        txtCorreo.setEnabled(false);
        txtNombreDenunciado.setEnabled(false);
        txtApellidoDenunciado.setEnabled(false);
        //txtDenuncia.setEnabled(false);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
