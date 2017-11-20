package com.example.personal.comunitarias.Pedidos;

import android.content.Context;
import android.content.DialogInterface;
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

import com.example.personal.comunitarias.Denuncias.Denuncia;
import com.example.personal.comunitarias.Denuncias.Denunciado;
import com.example.personal.comunitarias.Denuncias.MostrarDatos;
import com.example.personal.comunitarias.Denuncias.Peticionario;
import com.example.personal.comunitarias.R;

import java.util.ArrayList;

/**
 * Created by PC-JANINA on 26/2/2017.
 */

public class TabsPedido extends AppCompatActivity {

    TabLayout tabLayout;
    CustomViewPager viewPager;
    LinearLayout tabStrip;
    CoordinatorLayout coordinatorLayout ;

    static ArrayList<String> lista_estadocivil, lista_niveledu,lista_prov,lista_etnia,lista_ciudades_provincias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabs_pedido);

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);

        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

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

        private String fragments[] = {"Peticionario", "Pedido", "Datos Entidad", "Mostrar Datos"};

        public CustomAdapter(FragmentManager supportFragmentManager, Context applicationContext) {
            super(supportFragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    Peticionario_PE p= new Peticionario_PE();
                    p.asignarPager(viewPager);
                    p.setLista_estadocivil(lista_estadocivil);
                    p.setLista_niveledu(lista_niveledu);
                    p.setLista_prov(lista_prov);
                    p.setLista_etnia(lista_etnia);
                    p.setLista_ciudades_provincias(lista_ciudades_provincias);
                    return p;
                case 1:
                    Pedido solucion =new Pedido();
                    solucion.asignarPager(viewPager);
                    return solucion;
                case 2:
                    DatosEntidad de = new DatosEntidad();
                    de.asignarPager(viewPager);
                    de.setLista_prov(lista_prov);
                    de.setLista_ciudades_provincias(lista_ciudades_provincias);
                    return de;
                case 3:
                    MostrarDatosPedido abcd = new MostrarDatosPedido();
                    abcd.asignarPager(viewPager);
                    return abcd;

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

    public static void setLista_estadocivil(ArrayList<String> lista_estadocivil) {
        TabsPedido.lista_estadocivil = lista_estadocivil;
    }

    public static void setLista_niveledu(ArrayList<String> lista_niveledu) {
        TabsPedido.lista_niveledu = lista_niveledu;
    }

    public static void setLista_prov(ArrayList<String> lista_prov) {
        TabsPedido.lista_prov = lista_prov;
    }

    public static ArrayList<String> getLista_etnia() {
        return lista_etnia;
    }

    public static void setLista_etnia(ArrayList<String> lista_etnia) {
        TabsPedido.lista_etnia = lista_etnia;
    }

    public static ArrayList<String> getLista_ciudades_provincias() {
        return lista_ciudades_provincias;
    }

    public static void setLista_ciudades_provincias(ArrayList<String> lista_ciudades_provincias) {
        TabsPedido.lista_ciudades_provincias = lista_ciudades_provincias;
    }
}
