package com.example.personal.comunitarias.oficinas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.WindowManager;

import com.example.personal.comunitarias.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.TreeMap;

public class OficinasActivity extends AppCompatActivity implements OnMapReadyCallback,GoogleMap.OnCameraIdleListener {

    private GoogleMap mMap;
    private OficinasReader officereader;
    public static Oficina select;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_oficinas);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        officereader = new OficinasReader(this);
        officereader.execute();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setMinZoomPreference(7.1f);
        LatLngBounds bounds = new LatLngBounds(new LatLng(-4.708246, -92.737665),new LatLng(1.251923, -75.456171));
        mMap.setLatLngBoundsForCameraTarget(bounds);
        LatLng ecuador = new LatLng(-0.577191, -78.362055);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ecuador, 7.2f));
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener()
        {

            @Override
            public boolean onMarkerClick(Marker arg0) {
                select = officereader.getProvincias().get(arg0.getTitle());
                Intent i=new Intent(getBaseContext(), OfficeDialog.class);
                startActivity(i);
                return true;
            }

        });


    }




    public void anadirMarcador(TreeMap<String , Oficina> oficinas){

        for(Oficina oficina : oficinas.values()){
            mMap.addMarker(new MarkerOptions()
                    .position(oficina.getCoordenada())
                    .title(oficina.getProvincia())



            );
        }




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
