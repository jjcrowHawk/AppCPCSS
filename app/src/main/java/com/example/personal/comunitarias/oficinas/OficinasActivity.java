package com.example.personal.comunitarias.oficinas;

import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.personal.comunitarias.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class OficinasActivity extends AppCompatActivity
        implements OnMapReadyCallback, GoogleMap.OnCameraIdleListener,
        NavigationView.OnNavigationItemSelectedListener{

    private GoogleMap mMap;
    private OficinasReader officereader;
    public static Oficina select;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oficinas);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        officereader = new OficinasReader(this);
        officereader.execute();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);






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
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        Oficina oficina = officereader.getProvincias().get(item.getTitle());
        anadirMarcador(oficina);
        //Toast.makeText(getApplicationContext(), oficina.getProvincia(), Toast.LENGTH_SHORT).show();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setMinZoomPreference(7.1f);
        LatLngBounds bounds = new LatLngBounds(new LatLng(-4.708246, -92.737665), new LatLng(1.251923, -75.456171));
        mMap.setLatLngBoundsForCameraTarget(bounds);
        LatLng ecuador = new LatLng(-0.577191, -78.362055);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ecuador, 7.2f));
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {

            @Override
            public boolean onMarkerClick(Marker arg0) {
                select = officereader.getProvincias().get(arg0.getTitle());

                Intent i=new Intent(getBaseContext(), OfficeDialog.class);
                startActivity(i);
                return true;
            }

        });


    }

    public void onClick(View view) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.openDrawer(GravityCompat.START);
    }


    public void anadirMarcador(Oficina oficina) {
        mMap.clear();
        mMap.addMarker(new MarkerOptions()
                .position(oficina.getCoordenada())
                .title(oficina.getProvincia())
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_logo))

        );

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(oficina.getCoordenada(), 15f));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onCameraIdle() {

        float maxZoom = 17.0f;
        float minZoom = 7.2f;

        if (mMap.getCameraPosition().zoom > maxZoom) {
            mMap.animateCamera(CameraUpdateFactory.zoomTo(maxZoom));
        } else if (mMap.getCameraPosition().zoom < minZoom) {
            mMap.animateCamera(CameraUpdateFactory.zoomTo(minZoom));
        }

    }





}
