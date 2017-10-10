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

    @Override
    public void onFragmentInteraction(Uri uri) {
        //
    }
}
