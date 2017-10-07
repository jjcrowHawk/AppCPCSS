package com.example.personal.comunitarias;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;

import com.example.personal.comunitarias.DatabaseHelper.DatabaseHelper;

public class Intro extends AppCompatActivity {

    private int tiempo = 10;
    int pStatus = 0;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);



       Thread thread = new Thread() {
            public void run() {
                while (pStatus < 200) {
                    pStatus += 1;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                        }
                    });
                    try {
                        sleep(tiempo);
                    } catch (Exception e) {

                    }
                }

                //Inicializando la base
                //DatabaseHelper DBHelper = new DatabaseHelper(getApplicationContext());
                //DBHelper.inicializar();

                //Log.d("SIZE estado civil: ", ""+DBHelper.getAllEstadocivil().size());
                //Log.d("SIZE nivel: ", ""+DBHelper.getAllNiveleducacion().size());
                //Log.d("SIZE ocupa: ", ""+DBHelper.getAllOcupacionNombres().size());
                //Log.d("SIZE ciudades: ", ""+DBHelper.getAllCiudades().size());
                //Log.d("SIZE inst: ", ""+DBHelper.getAllInstitucionNombres().size());
                Intent i=new Intent(getBaseContext(),MenuSecundario.class);
                startActivity(i);
                //Inicializar actividad Menu

                finish();




            }
        };
        thread.start();



    }

}