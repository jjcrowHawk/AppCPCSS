package com.example.personal.comunitarias.Mision;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.widget.ImageView;

import com.example.personal.comunitarias.R;
import com.squareup.picasso.Picasso;

/**
 * Created by kleberstevendiazcoello on 23/1/17.
 */

public class Vision extends AppCompatActivity {
    ImageView i_vision;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vision);
        i_vision = (ImageView) findViewById(R.id.vision_img);
        Picasso.with(getApplicationContext()).load("http://www.cpccs.gob.ec/wp-content/uploads/2015/11/VISIO%CC%81N.jpg").into(i_vision);

        //Descripción de Visión en WebView para el texto justificado
        WebView webView = (WebView) findViewById(R.id.webView_vision);
        webView.loadUrl("file:///android_asset/vision.html");

    }

}
