package com.example.personal.comunitarias;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
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
import android.widget.Button;

import com.example.personal.comunitarias.Denuncias.Tabs;
import com.example.personal.comunitarias.Mision.mision;
import com.example.personal.comunitarias.Mision.vision;
import com.example.personal.comunitarias.noticias.Noticias;
import com.example.personal.comunitarias.oficinas.IntroOficinas;
import com.example.personal.comunitarias.preguntas.PreguntasFrecuentes;
import com.example.personal.comunitarias.tv.IntroTv;
import com.example.personal.comunitarias.tweets.IntroTweets;
import com.example.personal.comunitarias.videos.IntroVideos;

public class Menu extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Button Denuncias,Pedidos,Noticiase,Preguntas;

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


        Denuncias = (Button)findViewById(R.id.idTarea);
        Pedidos = (Button)findViewById(R.id.idExamen);
        Noticiase = (Button)findViewById(R.id.idProyecto);
        Preguntas = (Button)findViewById(R.id.idResult);

        Denuncias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getBaseContext(), Tabs.class);
                startActivity(i);

            }
        });


        Pedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(getApplicationContext(), "En construcción" , Toast.LENGTH_SHORT).show();
                //Intent i=new Intent(getBaseContext(), Tabs.class);
               // startActivity(i);
            }
        });

        Noticiase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getBaseContext(), Noticias.class);
                startActivity(i);
                //finish();
            }
        });

        Preguntas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getBaseContext(), PreguntasFrecuentes.class);
                startActivity(i);


            }
        });
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
            startActivity (new Intent(getApplicationContext(), mision.class));

        } else if (id == R.id.nav_vision) {
            startActivity (new Intent(getApplicationContext(), vision.class));

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
        }
        else if (id == R.id.nav_tv) {
            if(!isOnlineNet())
                Snackbar.make((ViewGroup) ((ViewGroup) this
                        .findViewById(android.R.id.content)).getChildAt(0), "Necesita conexión a Internet", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            else
                startActivity (new Intent(getApplicationContext(), IntroTv.class));
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

            if (mWifi.isConnected()) {
                tipoConexion1 = true;
            }
            if (mMobile.isConnected()) {
                tipoConexion2 = true;
            }

            if (tipoConexion1 == true || tipoConexion2 == true) {
               /* Estas conectado a internet usando wifi o redes moviles, puedes enviar tus datos */
                return true;
            }
        }

        return false;
    }
}
