package com.example.personal.comunitarias;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.personal.comunitarias.Denuncias.IntroDenuncias;
import com.example.personal.comunitarias.Facebook.IntroFacebook;
import com.example.personal.comunitarias.Pedidos.IntroPedidos;
import com.example.personal.comunitarias.Twitter.IntroTweets;
import com.example.personal.comunitarias.Youtube.IntroVideos;

public class AntiCorrupcionActivity extends AppCompatActivity{
    Button Denuncias,Pedidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anticorrupcion);

        Denuncias = (Button)findViewById(R.id.idDenuncias);
        Pedidos = (Button)findViewById(R.id.idPedidos);

        Denuncias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getBaseContext(), IntroDenuncias.class);
                startActivity(i);
            }
        });
        Pedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getBaseContext(), IntroPedidos.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed(){
        Intent i= new Intent(this.getBaseContext(),MenuPrincipal.class);
        startActivity(i);
    }
}

