package com.example.personal.comunitarias;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.personal.comunitarias.Facebook.FacebookActivity;
import com.example.personal.comunitarias.Facebook.IntroFacebook;
import com.example.personal.comunitarias.Twitter.IntroTweets;
import com.example.personal.comunitarias.Youtube.IntroVideos;

public class MenuSecundario extends AppCompatActivity {

    ImageButton participacionButton,controlButton,transparenciaButton,luchaButton,noticiasButton,contactoButton,facebookButton,twitterButton,youtubeButton;
    TextView tituloTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_secundario);

        participacionButton = (ImageButton) findViewById(R.id.participacionButton);
        controlButton = (ImageButton) findViewById(R.id.controlSocialButton);
        transparenciaButton = (ImageButton) findViewById(R.id.transparenciaButton);
        luchaButton = (ImageButton) findViewById(R.id.denunciasButton);
        noticiasButton = (ImageButton) findViewById(R.id.noticiaButton);
        contactoButton = (ImageButton) findViewById(R.id.contactoButton);
        facebookButton = (ImageButton) findViewById(R.id.facebookButton);
        twitterButton = (ImageButton) findViewById(R.id.twitterButton);
        youtubeButton = (ImageButton) findViewById(R.id.youtubeButton);
        tituloTextView = (TextView) findViewById(R.id.tituloTextView);

        setearAcciones();
    }

    public void setearAcciones(){

        participacionButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                tituloTextView.setText("Participación Ciudadana");
                cambiarImagen("PC");
            }
        });

        controlButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                tituloTextView.setText("Control Social");
                cambiarImagen("CS");
            }
        });

        transparenciaButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                tituloTextView.setText("Transparencia");
                cambiarImagen("TP");
            }
        });

        luchaButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                tituloTextView.setText("Lucha contra la Corrupción");
                cambiarImagen("LC");
            }
        });

        noticiasButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                tituloTextView.setText("Noticias");
                cambiarImagen("");
            }
        });

        contactoButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                tituloTextView.setText("Contacto");
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
            case "LC":
                participacionButton.setImageResource(R.drawable.img_asset_51hdpi);
                controlButton.setImageResource(R.drawable.img_asset_52hdpi);
                transparenciaButton.setImageResource(R.drawable.img_asset_53hdpi);
                luchaButton.setImageResource(R.drawable.img_asset_36hdpi);
            default:
                participacionButton.setImageResource(R.drawable.img_asset_51hdpi);
                controlButton.setImageResource(R.drawable.img_asset_52hdpi);
                transparenciaButton.setImageResource(R.drawable.img_asset_53hdpi);
                luchaButton.setImageResource(R.drawable.img_asset_54hdpi);
        }
    }
}
