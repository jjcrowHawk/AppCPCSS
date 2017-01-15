package noticias;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

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

        WebView myWebView = new WebView(Noticias.this);
        myWebView.loadUrl(noticia.getUrl());
        myWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        new AlertDialog.Builder(Noticias.this).setView(myWebView)
                .setTitle(noticia.getTitulo())
                .setPositiveButton("Volver", new DialogInterface.OnClickListener() {
                    @TargetApi(11)
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).show();
    }

    public void itemListeners(){
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                noticiaWebView(adapter.noticias.get(i));
                Toast.makeText(getApplicationContext(), "presiono " + i, Toast.LENGTH_SHORT).show();
            }
        });
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "presiono LARGO " + i, Toast.LENGTH_SHORT).show();
                return false;
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
                        //adapter.noticias.add(new Noticia(tit, link, dia + "/" + mes, content, imgurl));

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
                            adapter.noticias.add(new Noticia(n_titulo, n_link, n_dia+"/"+n_mes, n_contenido, n_urlimg));
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

        try {
            Process p = Runtime.getRuntime().exec("ping -c 1 www.google.es");

            int val = p.waitFor();
            boolean reachable = (val == 0);
            return reachable;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }


}