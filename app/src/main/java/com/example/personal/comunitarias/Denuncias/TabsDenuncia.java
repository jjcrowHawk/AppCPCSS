package com.example.personal.comunitarias.Denuncias;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.example.personal.comunitarias.BaseDeDatos.estadocivil.Estadocivil;
import com.example.personal.comunitarias.R;

import java.util.ArrayList;

public class TabsDenuncia extends AppCompatActivity {

    TabLayout tabLayout;
    CustomViewPager viewPager;
    LinearLayout tabStrip;
    CoordinatorLayout coordinatorLayout;

    static ArrayList<String> lista_estadocivil, lista_niveledu, lista_nacionalidad,
            lista_ocup, lista_prov, lista_ciudades_provincias, lista_inst, lista_etnia, lista_pais;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabs_denuncia);

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout1);

        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        viewPager = (CustomViewPager) findViewById(R.id.viewPager);
        viewPager.setPagingEnabled(false);
        viewPager.setAdapter(new CustomAdapter(getSupportFragmentManager(), getApplicationContext()));

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        tabStrip = ((LinearLayout) tabLayout.getChildAt(0));
        BloquearTabs();



        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
        });

    }
    public void BloquearTabs(){
        tabStrip.getChildAt(0).setClickable(false);
        tabStrip.getChildAt(1).setClickable(false);
        tabStrip.getChildAt(2).setClickable(false);
        tabStrip.getChildAt(3).setClickable(false);
    }


    private class CustomAdapter extends FragmentPagerAdapter {

        private String fragments[] = {"Peticionario", "Denuncia", "Denunciado", "Mostrar Datos"};

        public CustomAdapter(FragmentManager supportFragmentManager, Context applicationContext) {
            super(supportFragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    Peticionario p= new Peticionario(viewPager);
                    p.setLista_estadocivil(lista_estadocivil);
                    p.setLista_niveledu(lista_niveledu);
                    p.setLista_nacionalidad(lista_nacionalidad);
                    p.setLista_ocup(lista_ocup);
                    p.setLista_prov(lista_prov);
                    p.setLista_etnia(lista_etnia);
                    p.setLista_ciudades_provincias(lista_ciudades_provincias);
                    return p;
                case 1:
                    Denuncia d = new Denuncia(viewPager);
                    d.setLista_inst(lista_inst);
                    return d;
                case 2:
                    Denunciado de = new Denunciado(viewPager);
                    de.setLista_inst(lista_inst);
                    de.setLista_prov(lista_prov);
                    de.setLista_ciudades_provincias(lista_ciudades_provincias);
                    de.setLista_ocup(lista_ocup);
                    return de;
                case 3:
                    return new MostrarDatos(viewPager);

                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return fragments.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragments[position];
        }
    }


    //Acciones del boton regresar

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        new AlertDialog.Builder(this)
                .setTitle("¡Aviso!")
                .setMessage("Si sale del formulario se perderán todos los datos ingresados. ¿Desea salir?")
                .setPositiveButton("SI", null)
                .setPositiveButton("Si", new AlertDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setNegativeButton("NO", null)
                .show();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        //replaces the default 'Back' button action
        if(keyCode==KeyEvent.KEYCODE_BACK)
        {
            new AlertDialog.Builder(this)
                    .setTitle("¡Aviso!")
                    .setMessage("Si sale del formulario se perderán todos los datos ingresados. ¿Desea salir?")
                    .setPositiveButton("SI", null)
                    .setPositiveButton("Si", new AlertDialog.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    })
                    .setNegativeButton("NO", null)
                    .show();

        }
        return true;
    }

    //
    public static void setLista_estadocivil(ArrayList<String> lista_estadocivil) {
        TabsDenuncia.lista_estadocivil = lista_estadocivil;
    }

    public static void setLista_niveledu(ArrayList<String> lista_niveledu) {
        TabsDenuncia.lista_niveledu = lista_niveledu;
    }

    public static void setLista_nacionalidad(ArrayList<String> lista_nacionalidad) {
        TabsDenuncia.lista_nacionalidad = lista_nacionalidad;
    }

    public static void setLista_ocup(ArrayList<String> lista_ocup) {
        TabsDenuncia.lista_ocup = lista_ocup;
    }

    public static void setLista_prov(ArrayList<String> lista_prov) {
        TabsDenuncia.lista_prov = lista_prov;
    }

    public static void setLista_ciudades_provincias(ArrayList<String> lista_ciudad) {
        TabsDenuncia.lista_ciudades_provincias = lista_ciudad;
    }

    public static void setLista_inst(ArrayList<String> lista_inst) {
        TabsDenuncia.lista_inst = lista_inst;
    }

    public static ArrayList<String> getLista_pais() {
        return lista_pais;
    }

    public static void setLista_pais(ArrayList<String> lista_pais) {
        TabsDenuncia.lista_pais = lista_pais;
    }

    public static ArrayList<String> getLista_etnia() {
        return lista_etnia;
    }

    public static void setLista_etnia(ArrayList<String> lista_etnia) {
        TabsDenuncia.lista_etnia = lista_etnia;
    }
}
