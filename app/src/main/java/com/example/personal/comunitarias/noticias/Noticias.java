package com.example.personal.comunitarias.noticias;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.personal.comunitarias.DatabaseHelper.DatabaseHelper;
import com.example.personal.comunitarias.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;


public class Noticias extends AppCompatActivity {

    private ListView listview;
    private ListViewAdapter adapter;
    private Button load;
    private int page;
    private ProgressDialog mProgressDialog;
    private  int count= 0;
    private ProgressDialog pd;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticias);
        adapter = new ListViewAdapter(Noticias.this);
        page =1 ;

        new JsoupListView().execute();
        count++;

        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);





    }

    public void noticiaWebView(Noticia noticia) {

        pd = new ProgressDialog(this);
        pd.setMessage("Cargando...");
        WebView myWebView = new WebView(Noticias.this);

        myWebView.setWebViewClient(new Noticias.MyWebViewClient());
        myWebView.loadUrl(noticia.getUrl());


        new AlertDialog.Builder(Noticias.this).setView(myWebView)
                .setTitle(noticia.getTitulo())
                .setPositiveButton("Volver", new DialogInterface.OnClickListener() {
                    @TargetApi(11)
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).show();
        pd.show();
    }

    public void itemListeners(){
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                noticiaWebView(adapter.noticias.get(i));
            }
        });

        load = (Button) findViewById(R.id.load);
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page++;

                new JsoupListView().execute();

            }
        });



    }









    private class JsoupListView extends AsyncTask<Void, Void, Void> {

        //String url = "http://www.cpccs.gob.ec/es/category/com.example.personal.comunitarias.noticias/page/"+page+"/";
        String url = "http://www.cpccs.gob.ec/es/category/noticias/page/"+page+"/";
        int positionview;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            positionview = adapter.noticias.size()-1;
            mProgressDialog = new ProgressDialog(Noticias.this);
            mProgressDialog.setTitle("Noticias");
            mProgressDialog.setMessage("Cargando...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {


            try {
                if (isOnlineNet()) {
                    //adapter.noticias.clear();
                    Document doc = Jsoup.connect(url).get();
                    for (Element article : doc.select("article")) {
                        Elements wrap = article.select("div[class=blog-wrap]");
                        Elements date = wrap.select("div[class=post-date]");
                        String dia = date.select("span[class=day]").text();
                        String mes = date.select("span[class=month]").text();

                        Elements image = wrap.select("div[class=entry blog-media]");
                        Elements img = image.select("a").select("img[src]");
                        String imgurl = img.attr("src");
                        Elements contenido = wrap.select("div[class=post-content]");
                        String content = contenido.select("p").text();
                        Elements titulo = wrap.select("a");
                        String link = titulo.attr("href");
                        String tit = titulo.get(1).text();
                        //adapter.com.example.personal.comunitarias.noticias.add(new Noticia(tit, link, dia + "/" + mes, content, imgurl));

                        DatabaseHelper DBHelper = new DatabaseHelper(Noticias.this);
                        SQLiteDatabase bd = DBHelper.getWritableDatabase();

                        //Se verifica si ya existen, para ya no añadirlos a la  base
                        Cursor fila = bd.rawQuery("select titulo,contenidoprevio from noticia where titulo='" + tit + "'", null);

                        if (!fila.moveToFirst()) {
                            ContentValues registro = new ContentValues();
                            registro.put("titulo", tit);
                            registro.put("link", link);
                            registro.put("contenidoprevio", content);
                            registro.put("dia", dia);
                            registro.put("mes", mes);
                            registro.put("urlimagen", imgurl);

                            bd.insert("noticia", null, registro);
                        }
                        //Para debug
                        //Cursor fila1 = bd.rawQuery("select contenidoprevio from noticia", null);
                        //Log.e("FILA1", "" + fila1.getCount());

                        bd.close();
                    }
                }
                //Limpiar recycler
                adapter.noticias.clear();
                //Se lee de la base de datos
                    DatabaseHelper DBHelper= new DatabaseHelper(Noticias.this);
                    SQLiteDatabase bd = DBHelper.getWritableDatabase();

                    Cursor fila_db = bd.rawQuery("select * from noticia", null);

                    if (fila_db.moveToFirst()) {

                        while (fila_db.isAfterLast() == false) {
                            String n_titulo = fila_db.getString(fila_db.getColumnIndex("titulo"));
                            String n_link = fila_db.getString(fila_db.getColumnIndex("link"));
                            String n_dia = fila_db.getString(fila_db.getColumnIndex("dia"));
                            String n_mes = fila_db.getString(fila_db.getColumnIndex("mes"));
                            String n_contenido = fila_db.getString(fila_db.getColumnIndex("contenidoprevio"));
                            String n_urlimg = fila_db.getString(fila_db.getColumnIndex("urlimagen"));
                            adapter.noticias.add(new Noticia(n_titulo, n_link, Integer.valueOf(n_dia),Integer.valueOf(n_mes), n_contenido, n_urlimg));
                            fila_db.moveToNext();
                        }
                    }
                    //Log.e("FILA_DB", ""+fila_db.getCount());
                    bd.close();

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            mProgressDialog.dismiss();
            mProgressDialog.cancel();
            listview = (ListView) findViewById(R.id.listView1);
            listview.setAdapter(adapter);
            if(count>=1)
                listview.setSelection(positionview);
            itemListeners();

        }
    }

    //Acciones del boton regresar

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    //Saber si hay conexion
    public Boolean isOnlineNet() {

        ConnectivityManager cm;
        NetworkInfo ni;
        cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        ni = cm.getActiveNetworkInfo();
        boolean tipoConexion1 = false;
        boolean tipoConexion2 = false;

        if (ni != null) {
            ConnectivityManager connManager1 = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mWifi = connManager1.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

            ConnectivityManager connManager2 = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mMobile = connManager2.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if (mWifi.isConnected()) {
                tipoConexion1 = true;
            }
            if (mMobile.isConnected()) {
                tipoConexion2 = true;
            }

            if (tipoConexion1 == true || tipoConexion2 == true) {
               /* Estas conectado a internet usando wifi o redes moviles, puedes enviar tus datos */
                return true;
            }
        }

        return false;
    }





    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            if (!pd.isShowing()) {
                pd.show();
            }

            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            if (pd.isShowing()) {
                pd.cancel();

            }

        }
    }



}