package com.example.personal.comunitarias;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import noticias.Noticias;

public class Menu extends AppCompatActivity  {
    Button Denuncias,Pedidos,Noticiase,Preguntas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity);
        Denuncias = (Button)findViewById(R.id.idTarea);
        Pedidos = (Button)findViewById(R.id.idExamen);
        Noticiase = (Button)findViewById(R.id.idProyecto);
        Preguntas = (Button)findViewById(R.id.idResult);

        Denuncias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        Pedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        Noticiase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getBaseContext(), Noticias.class);
                startActivity(i);
                finish();
            }
        });

        Preguntas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getBaseContext(),IntroConsejo.class);
                startActivity(i);
                finish();

            }
        });

    }

}
