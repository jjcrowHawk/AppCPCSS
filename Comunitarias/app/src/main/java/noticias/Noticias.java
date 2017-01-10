package noticias;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

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
                    adapter.noticias.add(new Noticia(tit,link,dia+"/"+mes,content,imgurl));


                }
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_noticias, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}