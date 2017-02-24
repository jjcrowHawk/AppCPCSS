package com.example.personal.comunitarias;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
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
import android.widget.Toast;

import com.example.personal.comunitarias.DatabaseHelper.DatabaseHelper;
import com.example.personal.comunitarias.Denuncias.TabsDenuncia;
import com.example.personal.comunitarias.Mision.Mision;
import com.example.personal.comunitarias.Mision.Vision;
import com.example.personal.comunitarias.noticias.BoletinesDataBase;
import com.example.personal.comunitarias.noticias.Intro_noticias;
import com.example.personal.comunitarias.noticias.NoticiasDataBase;
import com.example.personal.comunitarias.oficinas.IntroOficinas;
import com.example.personal.comunitarias.preguntas.PreguntasFrecuentes;
import com.example.personal.comunitarias.tv.IntroTv;
import com.example.personal.comunitarias.tweets.IntroTweets;
import com.example.personal.comunitarias.videos.IntroVideos;

public class Menu extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Button Denuncias,Pedidos,Noticiase,Preguntas;
    DatabaseHelper db;

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
                Intent i=new Intent(getBaseContext(), TabsDenuncia.class);
                startActivity(i);

            }
        });


        Pedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(getApplicationContext(), "En construcción" , Toast.LENGTH_SHORT).show();
                //Intent i=new Intent(getBaseContext(), TabsDenuncia.class);
               // startActivity(i);
            }
        });

        Noticiase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteOpenHelper DBHelperBoletines =new BoletinesDataBase(Menu.this) ;
                SQLiteDatabase bd1 = DBHelperBoletines.getWritableDatabase();
                SQLiteOpenHelper DBHelperNoticias =new NoticiasDataBase(Menu.this) ;
                SQLiteDatabase bd2 = DBHelperNoticias.getWritableDatabase();
                Cursor fila_db1 = bd1.rawQuery("select * from boletin", null);
                Cursor fila_db2 = bd2.rawQuery("select * from noticia", null);
                if(!isOnlineNet() && (fila_db1.getCount()== 0 || fila_db2.getCount() == 0)){
                    Toast.makeText(Menu.this,"No hay informacion para presentar",Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent i=new Intent(getBaseContext(), Intro_noticias.class);
                    startActivity(i);
                    //finish();
                }

            }
        });

        Preguntas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getBaseContext(), PreguntasFrecuentes.class);
                startActivity(i);


            }
        });

        /*
        //BASE
        db = new DatabaseHelper(getApplicationContext());
        //Creando Regiones
        Region r1 = new Region("Costa","Region Costa");
        Region r2 = new Region("Sierra","Region Sierra");
        //Insertando en base
        long region1_id = db.createRegion(r1);
        long region2_id = db.createRegion(r2);

        Log.d("Tag Count", "Tag Count: " + db.getAllRegion().size());

        //Creando Provincias
        Provincia p2 = new Provincia("Pichincha");
        Provincia p1 = new Provincia("Guayas");

        db.createProvinciaRegion(p1, region1_id);
        db.createProvinciaRegion(p2, region2_id);


        Log.d("Get Tags", "Getting All Tags");

        List<Region> allRegion = db.getAllRegion();
        for (Region r : allRegion) {
            Log.d("Region Name", r.getNombre());
        }

        List<Provincia> allProvincia = db.getAllProvincias();
        for (Provincia p : allProvincia) {
            Log.d("Provincia Name", p.getNombre());
        }

        db.closeDB();*/
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
