package com.example.personal.comunitarias;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.personal.comunitarias.Facebook.IntroFacebook;
import com.example.personal.comunitarias.Twitter.IntroTweets;
import com.example.personal.comunitarias.Youtube.IntroVideos;

public class MenuPrincipal extends AppCompatActivity implements  SeccionFragment.OnFragmentInteractionListener{

    ImageButton participacionButton,controlButton,transparenciaButton,luchaButton,noticiasButton,contactoButton,facebookButton,twitterButton,youtubeButton;
    FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_principal);

        participacionButton = (ImageButton) findViewById(R.id.participacionButton);
        controlButton = (ImageButton) findViewById(R.id.controlSocialButton);
        transparenciaButton = (ImageButton) findViewById(R.id.transparenciaButton);
        luchaButton = (ImageButton) findViewById(R.id.denunciasButton);
        noticiasButton = (ImageButton) findViewById(R.id.noticiaButton);
        contactoButton = (ImageButton) findViewById(R.id.contactoButton);
        facebookButton = (ImageButton) findViewById(R.id.facebookButton);
        twitterButton = (ImageButton) findViewById(R.id.twitterButton);
        youtubeButton = (ImageButton) findViewById(R.id.youtubeButton);
        setearAcciones();
        transaction = getSupportFragmentManager().beginTransaction();
    }

    public void setearAcciones(){

        participacionButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                cambiarImagen("PC");
                /*LayoutInflater inflater =(LayoutInflater) MenuSecundario.this.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View v = inflater.inflate(R.layout.activity_participacion_ciudadana2, null);
                Intent i = new Intent();
                i.setClass(v.getContext(), ParticipacionCiudadanaActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                container.addView(v);*/

            }
        });

        controlButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                cambiarImagen("CS");
            }
        });

        transparenciaButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                cambiarImagen("TP");
            }
        });

        luchaButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                cambiarImagen("LC");

            }
        });

        noticiasButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                cambiarImagen("");
            }
        });

        contactoButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                cambiarImagen("");
            }
        });

        facebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cambiarImagen("");
                Intent i=new Intent(getBaseContext(), IntroFacebook.class);
                startActivity(i);
            }
        });

        twitterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cambiarImagen("");
                Intent i=new Intent(getBaseContext(), IntroTweets.class);
                startActivity(i);
            }
        });

        youtubeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cambiarImagen("");
                Intent i=new Intent(getBaseContext(), IntroVideos.class);
                startActivity(i);
            }
        });
    }

    public void cambiarImagen(String boton){
        switch(boton){
            case "PC":
                participacionButton.setImageResource(R.drawable.img_asset_50hdpi);
                controlButton.setImageResource(R.drawable.img_asset_52hdpi);
                transparenciaButton.setImageResource(R.drawable.img_asset_53hdpi);
                luchaButton.setImageResource(R.drawable.img_asset_54hdpi);
                break;
            case "CS":
                participacionButton.setImageResource(R.drawable.img_asset_51hdpi);
                controlButton.setImageResource(R.drawable.img_asset_34hdpi);
                transparenciaButton.setImageResource(R.drawable.img_asset_53hdpi);
                luchaButton.setImageResource(R.drawable.img_asset_54hdpi);
                break;
            case "TP":
                participacionButton.setImageResource(R.drawable.img_asset_51hdpi);
                controlButton.setImageResource(R.drawable.img_asset_52hdpi);
                transparenciaButton.setImageResource(R.drawable.img_asset_35hdpi);
                luchaButton.setImageResource(R.drawable.img_asset_54hdpi);
                break;
            case "LC":
                participacionButton.setImageResource(R.drawable.img_asset_51hdpi);
                controlButton.setImageResource(R.drawable.img_asset_52hdpi);
                transparenciaButton.setImageResource(R.drawable.img_asset_53hdpi);
                luchaButton.setImageResource(R.drawable.img_asset_36hdpi);
                break;
            default:
                participacionButton.setImageResource(R.drawable.img_asset_51hdpi);
                controlButton.setImageResource(R.drawable.img_asset_52hdpi);
                transparenciaButton.setImageResource(R.drawable.img_asset_53hdpi);
                luchaButton.setImageResource(R.drawable.img_asset_54hdpi);
                break;
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        //
    }
}
