package com.example.personal.comunitarias;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Janina Costa on 15/1/2017.
 */

public class PreguntasFrecuentes extends AppCompatActivity {

    LinearLayout tutorial;
    ImageButton close;

    String androidListViewStrings[] = {
            "¿Cómo puede ver una noticia?",
            "¿Cómo puedo hacer una denuncia?",
            "¿Cómo puedo hacer un pedido?"};

    Integer image_id[] = {R.drawable.d_flecha, R.drawable.d_flecha, R.drawable.d_flecha};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preguntas_frecuentes_lista);

        listAdapter androidListAdapter = new listAdapter(this, image_id, androidListViewStrings);
        ListView androidListView = (ListView) findViewById(R.id.custom_listview_example);
        androidListView.setAdapter(androidListAdapter);



        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //
        androidListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //¿Cómo ver una noticia?
                if(position == 0){
                    //Toast.makeText(getApplicationContext(), "clik "+ position , Toast.LENGTH_SHORT).show();
                    setContentView(R.layout.tuto_pregunta1);
                    //toolbar para el nuevo layout
                    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
                    setSupportActionBar(toolbar);
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                    close = (ImageButton) findViewById(R.id.close1);
                    //close
                    close.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //Toast.makeText(PreguntasFrecuentes.this, "click", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(PreguntasFrecuentes.this, PreguntasFrecuentes.class);
                            startActivity(i);
                        }
                    });
                }
                //¿?
                if(position == 1){
                    //Toast.makeText(getApplicationContext(), "clik "+ position , Toast.LENGTH_SHORT).show();
                    setContentView(R.layout.tuto_pregunta2);
                    //toolbar para el nuevo layout
                    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
                    setSupportActionBar(toolbar);
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                    close = (ImageButton) findViewById(R.id.close2);

                    close.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //Toast.makeText(PreguntasFrecuentes.this, "click", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(PreguntasFrecuentes.this, PreguntasFrecuentes.class);
                            startActivity(i);
                        }
                    });
                }

                //twitters

                if(position == 2){
                    //Toast.makeText(getApplicationContext(), "clik "+ position , Toast.LENGTH_SHORT).show();
                    setContentView(R.layout.tuto_pregunta3);
                    //toolbar para el nuevo layout
                    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
                    setSupportActionBar(toolbar);
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                    close = (ImageButton) findViewById(R.id.close3);
                    close.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //Toast.makeText(PreguntasFrecuentes.this, "click", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(PreguntasFrecuentes.this, PreguntasFrecuentes.class);
                            startActivity(i);
                        }
                    });
                }
            }
        });



    }

    public class listAdapter extends ArrayAdapter {
        String[] androidListViewStrings;
        Integer[] imagesId;
        Context context;

        public listAdapter(Activity context, Integer[] imagesId, String[] textListView) {
            super(context, R.layout.preguntas_frecuentes_item, textListView);
            this.androidListViewStrings = textListView;
            this.imagesId = imagesId;
            this.context = context;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater layoutInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View viewRow = layoutInflater.inflate(R.layout.preguntas_frecuentes_item, null,true);

            TextView mtextView = (TextView) viewRow.findViewById(R.id.text_view);
            ImageView mimageView = (ImageView) viewRow.findViewById(R.id.image_view);
            mtextView.setText(androidListViewStrings[i]);
            mimageView.setImageResource(imagesId[i]);


            //setContentView(R.layout.preguntas_frecuentes_lista);
            return viewRow;
        }

    }
    //Acciones del boton regresar

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();

        return super.onOptionsItemSelected(item);
    }

}
