package com.example.personal.comunitarias.Mision;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.ImageView;

import com.example.personal.comunitarias.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Sianna-chan on 21/02/2017.
 */

public class Vision extends AppCompatActivity {
    ImageView i_vision;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vision);

        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Setear imagen Visión
        i_vision = (ImageView) findViewById(R.id.vision_img);
        Picasso.with(getApplicationContext()).load(R.drawable.img_asset_30hdpi).into(i_vision);

        //Descripción de Visión en WebView para el texto justificado
        WebView webView = (WebView) findViewById(R.id.webView_vision);
        webView.loadUrl("file:///android_asset/vision.html");

    }

    //Acciones del boton regresar

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}
