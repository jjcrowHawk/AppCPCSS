package com.example.personal.comunitarias.Mision;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.example.personal.comunitarias.R;
import com.squareup.picasso.Picasso;

/**
 * Created by kleberstevendiazcoello on 23/1/17.
 */

public class Mision extends AppCompatActivity {
    ImageView i_mision;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mision);
        i_mision = (ImageView) findViewById(R.id.mision_img);
        Picasso.with(getApplicationContext()).load("http://www.cpccs.gob.ec/wp-content/uploads/2015/11/MISIO%CC%81N.jpg").into(i_mision);

        //Descripción de Misión en WebView para el texto justificado
        WebView webView = (WebView) findViewById(R.id.webView_mision);
        webView.loadUrl("file:///android_asset/mision.html");

    }
}
