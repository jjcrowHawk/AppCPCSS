package com.example.personal.comunitarias;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

public class Intro extends AppCompatActivity {

    private int tiempo = 30;
    int pStatus = 0;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Thread thread = new Thread() {
            public void run() {
                while (pStatus < 100) {
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
                Intent i=new Intent(getBaseContext(),Menu.class);
                startActivity(i);
                finish();

            }
        };
        thread.start();



    }

}