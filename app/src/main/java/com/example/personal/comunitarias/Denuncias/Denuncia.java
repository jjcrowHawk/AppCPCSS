package com.example.personal.comunitarias.Denuncias;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.personal.comunitarias.R;

/**
 * Created by Janina Costa on 28/1/2017.
 */

public class Denuncia extends AppCompatActivity {
    Spinner comparecer, hechos;
    ArrayAdapter<CharSequence> adapter, adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_denuncia);

        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Spinner comparecer con CPCCS
        comparecer = (Spinner) findViewById(R.id.spinner_comparecer);
        adapter = ArrayAdapter.createFromResource(this,
                R.array.si_no, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        comparecer.setAdapter(adapter);

        //Spinner hechos son investigados
        hechos = (Spinner) findViewById(R.id.spinner_hechos);
        adapter2 = ArrayAdapter.createFromResource(this,
                R.array.si_no, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hechos.setAdapter(adapter2);

    }

    //Acciones del boton regresar

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }



}
