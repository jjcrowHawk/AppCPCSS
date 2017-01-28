package com.example.personal.comunitarias.Denuncias;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.personal.comunitarias.R;

/**
 * Created by Janina Costa on 28/1/2017.
 */

public class Peticionario extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner identidad, tipoIdentificacion, genero, estado_civil, nivelEducacion, nacionalidad, residencia, provincia, ciudad;
    ArrayAdapter<CharSequence> adapter, adapter2, adapter3, adapter4, adapter5, adapter6, adapter7,adapter8, adapter9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datos_peticionario);



        //Spinner identidad reservada
        identidad = (Spinner) findViewById(R.id.spinner);
        adapter = ArrayAdapter.createFromResource(this,
                R.array.si_no, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        identidad.setAdapter(adapter);

        //Spinner Tipo de identificacion
        tipoIdentificacion = (Spinner) findViewById(R.id.spinner2);
        adapter2 = ArrayAdapter.createFromResource(this,
                R.array.tipo_identificacion, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipoIdentificacion.setAdapter(adapter2);

        //Spinner género
        genero = (Spinner) findViewById(R.id.spinner3);
        adapter3 = ArrayAdapter.createFromResource(this,
                R.array.genero, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genero.setAdapter(adapter3);

        //Spinner estado_civil
        estado_civil = (Spinner) findViewById(R.id.spinner4);
        adapter4 = ArrayAdapter.createFromResource(this,
                R.array.estado_civil, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        estado_civil.setAdapter(adapter4);

        //Spinner Nivel educacion
        nivelEducacion = (Spinner) findViewById(R.id.n_educacion);
        adapter5 = ArrayAdapter.createFromResource(this,
                R.array.nivel__educacion, android.R.layout.simple_spinner_item);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nivelEducacion.setAdapter(adapter5);

        //Spinner Nivel Nacionalidad
        nacionalidad = (Spinner) findViewById(R.id.spinner6);
        adapter6 = ArrayAdapter.createFromResource(this,
                R.array.nacionalidad, android.R.layout.simple_spinner_item);
        adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nacionalidad.setAdapter(adapter6);

        //Spinner Residencia
        residencia = (Spinner) findViewById(R.id.spinner7);
        adapter7 = ArrayAdapter.createFromResource(this,
                R.array.si_no, android.R.layout.simple_spinner_item);
        adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        residencia.setAdapter(adapter7);

        //Spinner Provincia
        provincia = (Spinner) findViewById(R.id.spinner8);

        //Spinner Ciudad
        ciudad = (Spinner) findViewById(R.id.spinner9);

        loadSpinnerProvincias();

    }

    private void loadSpinnerProvincias() {
        // Create an ArrayAdapter using the string array and a default spinner
        // layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.provincias, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        this.provincia.setAdapter(adapter);

        // This activity implements the AdapterView.OnItemSelectedListener
        this.provincia.setOnItemSelectedListener(this);
        this.ciudad.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        switch (parent.getId()) {
            case R.id.spinner8:

                // Retrieves an array
                TypedArray arrayLocalidades = getResources().obtainTypedArray(
                        R.array.array_provincia_a_localidades);

                CharSequence[] localidades = arrayLocalidades.getTextArray(pos);
                arrayLocalidades.recycle();

                // Create an ArrayAdapter using the string array and a default
                // spinner layout
                ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(
                        this, android.R.layout.simple_spinner_item,
                        android.R.id.text1, localidades);

                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                // Apply the adapter to the spinner
                this.ciudad.setAdapter(adapter);

                break;

            case R.id.spinner9:

                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    //Acciones del boton regresar


}
