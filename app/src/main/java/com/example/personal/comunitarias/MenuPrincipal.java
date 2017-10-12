package com.example.personal.comunitarias;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.support.design.widget.FloatingActionButton;

import com.example.personal.comunitarias.Contactenos.Contacto;
import com.example.personal.comunitarias.CpccsTV.IntroTv;
import com.example.personal.comunitarias.Facebook.IntroFacebook;
import com.example.personal.comunitarias.Mision.Mision;
import com.example.personal.comunitarias.Mision.Vision;
import com.example.personal.comunitarias.Oficinas.IntroOficinas;
import com.example.personal.comunitarias.Twitter.IntroTweets;
import com.example.personal.comunitarias.Youtube.IntroVideos;

public class MenuPrincipal extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener{

    ImageButton participacionButton,controlButton,transparenciaButton,luchaButton,noticiasButton,contactoButton,facebookButton,twitterButton,youtubeButton;
    FragmentTransaction transaction;
    FloatingActionButton fab;
    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        participacionButton = (ImageButton) findViewById(R.id.participacionButton);
        controlButton = (ImageButton) findViewById(R.id.controlSocialButton);
        transparenciaButton = (ImageButton) findViewById(R.id.transparenciaButton);
        luchaButton = (ImageButton) findViewById(R.id.denunciasButton);
        noticiasButton = (ImageButton) findViewById(R.id.noticiaButton);
        contactoButton = (ImageButton) findViewById(R.id.contactoButton);
        facebookButton = (ImageButton) findViewById(R.id.facebookButton);
        twitterButton = (ImageButton) findViewById(R.id.twitterButton);
        youtubeButton = (ImageButton) findViewById(R.id.youtubeButton);
        fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        setearAcciones();
        transaction = getSupportFragmentManager().beginTransaction();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



    }

    public void setearAcciones(){

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(GravityCompat.START);
            }
        });

        participacionButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent i=new Intent(getBaseContext(), MenuSecundario.class);
                i.putExtra("WINDOW","PC");
                startActivity(i);

            }
        });

        controlButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent i=new Intent(getBaseContext(), MenuSecundario.class);
                i.putExtra("WINDOW","CS");
                startActivity(i);
            }
        });

        transparenciaButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getBaseContext(), MenuSecundario.class);
                i.putExtra("WINDOW","TP");
                startActivity(i);
            }
        });

        luchaButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getBaseContext(), AntiCorrupcionActivity.class);
                startActivity(i);

            }
        });

        noticiasButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getBaseContext(), MenuSecundario.class);
                i.putExtra("WINDOW","NT");
                startActivity(i);
            }
        });

        contactoButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getBaseContext(), MenuSecundario.class);
                i.putExtra("WINDOW","CON");
                startActivity(i);
            }
        });

        facebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(getBaseContext(), IntroFacebook.class);
                startActivity(i);
            }
        });

        twitterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(getBaseContext(), IntroTweets.class);
                startActivity(i);
            }
        });

        youtubeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(getBaseContext(), IntroVideos.class);
                startActivity(i);
            }
        });
    }

    public void onBackPressed() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

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

        }
        else if (id == R.id.nav_tv) {
            if(!isOnlineNet())
                Snackbar.make((ViewGroup) ((ViewGroup) this
                        .findViewById(android.R.id.content)).getChildAt(0), "Necesita conexión a Internet", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            else
                startActivity (new Intent(getApplicationContext(), IntroTv.class));
        }



        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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
