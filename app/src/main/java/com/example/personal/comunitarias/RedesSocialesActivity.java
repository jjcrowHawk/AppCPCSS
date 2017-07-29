package com.example.personal.comunitarias;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.personal.comunitarias.Contactenos.Contacto;
import com.example.personal.comunitarias.CpccsTV.IntroTv;
import com.example.personal.comunitarias.Facebook.IntroFacebook;
import com.example.personal.comunitarias.Mision.Mision;
import com.example.personal.comunitarias.Mision.Vision;
import com.example.personal.comunitarias.Oficinas.IntroOficinas;
import com.example.personal.comunitarias.Twitter.IntroTweets;
import com.example.personal.comunitarias.Youtube.IntroVideos;

public class RedesSocialesActivity extends AppCompatActivity{
    Button Facebook,Twitter,Youtube;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redes_sociales);

        Facebook = (Button)findViewById(R.id.idFacebook);
        Twitter = (Button)findViewById(R.id.idTwitter);
        Youtube = (Button)findViewById(R.id.idYoutube);

        Facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getBaseContext(), IntroFacebook.class);
                startActivity(i);
            }
        });
        Twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getBaseContext(), IntroTweets.class);
                startActivity(i);
            }
        });
        Youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getBaseContext(), IntroVideos.class);
                startActivity(i);
            }
        });
    }

}
