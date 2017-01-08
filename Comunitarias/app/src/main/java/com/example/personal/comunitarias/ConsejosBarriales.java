    package com.example.personal.comunitarias;

    import android.os.Bundle;
    import android.support.v7.app.AppCompatActivity;
    import android.view.View;
    import android.view.WindowManager;
    import android.widget.AdapterView;
    import android.widget.GridView;

    import gridviews.CustomGridViewActivity;

    public class ConsejosBarriales extends AppCompatActivity {

        GridView androidGridView;

        String[] gridViewString = {
                "Crear Evento", "Agendar Evento",

        } ;
        int[] gridViewImageId = {
                R.drawable.denuncia_icon, R.drawable.pedidos_icon,

        };

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_consejos_barriales);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            CustomGridViewActivity adapterViewAndroid = new CustomGridViewActivity(ConsejosBarriales.this, gridViewString, gridViewImageId);
            androidGridView=(GridView)findViewById(R.id.grid_view_image_text_consejos);
            androidGridView.setAdapter(adapterViewAndroid);

            listenersBotones();
        }

        public void listenersBotones(){
            androidGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                    if(gridViewString[+i].equals("Crear Evento")){
                        //abrir la nueva actividad
                    }
                    if(gridViewString[+i].equals("Agendar Evento")){
                        
                    }
                }
            });

        }



    }

