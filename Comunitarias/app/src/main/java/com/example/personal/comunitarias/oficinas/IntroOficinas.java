package com.example.personal.comunitarias.oficinas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.personal.comunitarias.R;
import com.example.personal.comunitarias.oficinas.OficinasActivity;
import com.example.personal.comunitarias.tv.CpccsTv;

public class IntroOficinas extends AppCompatActivity {

    private int tiempo = 30;
    int pStatus = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_oficinas);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Thread thread = new Thread() {
            public void run() {
                while (pStatus < 100) {
                    pStatus += 1;
                    try {
                        sleep(tiempo);
                    } catch (Exception e) {

                    }
                }
                Intent i=new Intent(getBaseContext(), OficinasActivity.class);
                startActivity(i);
                finish();

            }
        };
        thread.start();
    }
}
