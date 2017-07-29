package com.example.personal.comunitarias;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.personal.comunitarias.BaseDeDatos.estadocivil.Estadocivil;
import com.example.personal.comunitarias.Contactenos.Contacto;
import com.example.personal.comunitarias.DatabaseHelper.DatabaseHelper;
import com.example.personal.comunitarias.Denuncias.IntroDenuncias;
import com.example.personal.comunitarias.Denuncias.Peticionario;
import com.example.personal.comunitarias.Denuncias.TabsDenuncia;
import com.example.personal.comunitarias.Facebook.IntroFacebook;
import com.example.personal.comunitarias.Mision.Mision;
import com.example.personal.comunitarias.Mision.Vision;
import com.example.personal.comunitarias.Pedidos.IntroPedidos;
import com.example.personal.comunitarias.Pedidos.TabsPedido;
import com.example.personal.comunitarias.Noticias.Intro_noticias;
import com.example.personal.comunitarias.Oficinas.IntroOficinas;
import com.example.personal.comunitarias.preguntas.PreguntasFrecuentes;
import com.example.personal.comunitarias.CpccsTV.IntroTv;
import com.example.personal.comunitarias.Twitter.IntroTweets;
import com.example.personal.comunitarias.Youtube.IntroVideos;

import java.util.ArrayList;

public class Menu extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Button Noticias,RedesSociales,ParticipacionCiudadana,ControlSocial,AntiCorrupcion,DenunciasPedidos;
    DatabaseHelper db;

    private ProgressDialog mProgressDialog;
    static ArrayList<String> lst_estadocivil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        /*Cargando la base de datos*/
        SQLiteOpenHelper DBHelper = new DatabaseHelper(getApplicationContext());
        SQLiteDatabase bd = DBHelper.getWritableDatabase();
        Cursor fila = bd.rawQuery("select * from estadocivil", null);
        ///////////////

        Noticias = (Button)findViewById(R.id.idNoticias);
        RedesSociales = (Button)findViewById(R.id.idRedesSociales);
        ParticipacionCiudadana=(Button)findViewById(R.id.idPP);
        ControlSocial=(Button)findViewById(R.id.idControlSocial);
        AntiCorrupcion=(Button)findViewById(R.id.idAntiCorrupcion);
        DenunciasPedidos = (Button)findViewById(R.id.idDenunciaPedidos);


        DenunciasPedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getBaseContext(), IntroDenuncias.class);
                startActivity(i);
            }
        });
        RedesSociales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getBaseContext(), RedesSocialesActivity.class);
                startActivity(i);
            }
        });
        ParticipacionCiudadana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getBaseContext(), ParticipacionCiudadanaActivity.class);
                startActivity(i);
            }
        });
/*
        Pedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getBaseContext(), IntroPedidos.class);
                startActivity(i);

            }
        });
*/
        Noticias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SQLiteOpenHelper DBHelper = new DatabaseHelper(Menu.this);
                SQLiteDatabase  bd = DBHelper.getWritableDatabase();
                Cursor fila_db1 = bd.rawQuery("select * from boletin", null);
                Cursor fila_db2 = bd.rawQuery("select * from noticia", null);
                if(!isOnlineNet() && (fila_db1.getCount()== 0 || fila_db2.getCount() == 0)){
                    Toast.makeText(Menu.this,"No hay informacion para presentar",Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent i=new Intent(getBaseContext(), Intro_noticias.class);
                    startActivity(i);
                }

            }
        });
/*
        Preguntas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getBaseContext(), PreguntasFrecuentes.class);
                startActivity(i);
            }
        });
        */
    }

    public class Progress_guardando extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(Menu.this.getBaseContext());
            mProgressDialog.setMessage("Cargando datos...");
            mProgressDialog.setIndeterminate(false);
            //mProgressDialog.show();
        }


        @Override
        protected Void doInBackground(Void... params) {
            lst_estadocivil= new Estadocivil().getListaEstadoCivilNombres();
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            //mProgressDialog.show();
        }

        @Override
        protected void onPostExecute(Void result) {
            //mProgressDialog.dismiss();
            //mProgressDialog.cancel();
            TabsDenuncia t = new TabsDenuncia();
            t.setLista_estadocivil(lst_estadocivil);
            Intent i=new Intent(getBaseContext(), t.getClass());
            startActivity(i);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_mision) {
            // Handle the camera action
            startActivity (new Intent(getApplicationContext(), Mision.class));

        } else if (id == R.id.nav_vision) {
            startActivity (new Intent(getApplicationContext(), Vision.class));

        } else if (id == R.id.nav_oficinas) {

            if(!isOnlineNet())
                Snackbar.make((ViewGroup) ((ViewGroup) this
                        .findViewById(android.R.id.content)).getChildAt(0), "Necesita conexión a Internet", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            else
                startActivity (new Intent(getApplicationContext(), IntroOficinas.class));

        } else if (id == R.id.nav_videos) {
            if(!isOnlineNet()){
                Snackbar.make((ViewGroup) ((ViewGroup) this
                        .findViewById(android.R.id.content)).getChildAt(0), "Necesita conexión a Internet", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
            else
                startActivity (new Intent(getApplicationContext(), IntroVideos.class));


        } else if (id == R.id.nav_tweets) {
            if(!isOnlineNet())
                Snackbar.make((ViewGroup) ((ViewGroup) this
                        .findViewById(android.R.id.content)).getChildAt(0), "Necesita conexión a Internet", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            else
                startActivity (new Intent(getApplicationContext(), IntroTweets.class));
        } else if (id == R.id.nav_facebook) {
            if(!isOnlineNet())
                Snackbar.make((ViewGroup) ((ViewGroup) this
                        .findViewById(android.R.id.content)).getChildAt(0), "Necesita conexión a Internet", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            else
                startActivity (new Intent(getApplicationContext(), IntroFacebook.class));
        }
        else if (id == R.id.nav_tv) {
            if(!isOnlineNet())
                Snackbar.make((ViewGroup) ((ViewGroup) this
                        .findViewById(android.R.id.content)).getChildAt(0), "Necesita conexión a Internet", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            else
                startActivity (new Intent(getApplicationContext(), IntroTv.class));
        } else if (id == R.id.contactenos) {
                startActivity (new Intent(getApplicationContext(), Contacto.class));
        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public Boolean isOnlineNet() {

        ConnectivityManager cm;
        NetworkInfo ni;
        cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        ni = cm.getActiveNetworkInfo();
        boolean tipoConexion1 = false;
        boolean tipoConexion2 = false;

        if (ni != null) {
            ConnectivityManager connManager1 = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mWifi = connManager1.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

            ConnectivityManager connManager2 = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mMobile = connManager2.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if (mWifi !=null) {
                if (mWifi.isConnected()) {
                    tipoConexion1 = true;
                }
            }
            if (mMobile !=null) {
                if (mMobile.isConnected()) {
                    tipoConexion2 = true;
                }
            }

            if (tipoConexion1 == true || tipoConexion2 == true) {
               /* Estas conectado a internet usando wifi o redes moviles, puedes enviar tus datos */
                return true;
            }
        }

        return false;
    }


}


