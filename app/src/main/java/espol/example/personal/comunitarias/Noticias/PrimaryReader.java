package espol.example.personal.comunitarias.Noticias;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

import espol.example.personal.comunitarias.DatabaseHelper.DatabaseHelper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 *  ESTA CLASE SIRVE PARA LA EXTRACCION INICIAL DE LAS NOTICIAS Y BOLETINES
 */

public class PrimaryReader extends AsyncTask<String, Void, Void> {
    private String url;
    private Context context;
    private List<Noticia> noticias;
    private boolean stop;
    private ProgressDialog progDailog;

    public PrimaryReader(Context context,List<Noticia> noticias, boolean stop ) {
        this.noticias  = noticias;
        this.context = context;
        this.stop = stop;
        this.url = "http://www.cpccs.gob.ec/es/category/";
    }

    @Override
    protected Void doInBackground(String... parametros) {
        String numeroPagina = parametros[1];
        String tipo = parametros[0];
        url = url+tipo+"/page/"+numeroPagina+"/";
        try {
            if(isOnlineNet()) {
                Document doc = Jsoup.connect(url).get();

                //extraemos las noticias del documento obtenido de la url y las almacenamos
                List<Noticia> noticias_extraidas = extraerNoticias(doc);

                //guardamos las noticias extraidas en la base local ( solo llena la base)
                for(Noticia noticia : noticias_extraidas){
                    guardarNoticiaBase(noticia , tipo);
                }
            }

            //Se lee de la base de datos
            leerNoticiaBase(tipo);


        } catch (IOException e) {

            e.printStackTrace();
        }

        return null;
    }


    // recibe un  objeto Document que contiene la estructura html de la pagina y obtiene la inforamcion necesaria para formar una noticia
    public List<Noticia> extraerNoticias(Document doc){
        List<Noticia> noticias_extraidas = new LinkedList<>();
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
            System.out.println(content);
            Elements titulo = wrap.select("a");
            String link = titulo.attr("href");
            String tit = titulo.get(1).text();
            noticias_extraidas.add(new Noticia(tit, link, dia + "/" + mes, content, imgurl));
        }
        return noticias_extraidas;
    }


    // guarda las noticias extraidas en la base de datos local
    public void guardarNoticiaBase(Noticia noticia, String tipo){
        SQLiteOpenHelper DBHelper = new DatabaseHelper(context);
        String tabla ;
        SQLiteDatabase  bd = DBHelper.getWritableDatabase();
        if(tipo.equalsIgnoreCase("boletines")){
            tabla = "boletin";
        }
        else{
            tabla = "noticia";
        }
        Cursor fila = bd.rawQuery("select titulo,contenidoprevio from "+ tabla +" where titulo='" + noticia.getTitulo() + "'", null);


        if (!fila.moveToFirst()) {
            ContentValues registro = new ContentValues();
            registro.put("titulo", noticia.getTitulo());
            registro.put("link", noticia.getUrl());
            registro.put("contenidoprevio", noticia.getDescripcion());
            registro.put("dia", noticia.getFecha().split("/")[0]);
            registro.put("mes", noticia.getFecha().split("/")[1]);
            registro.put("urlimagen", noticia.getUrlImg());


            bd.insert(tabla, null, registro);
        }

        bd.close();
    }


    //lee las noticias guardadas de la base local
    public void leerNoticiaBase(String tipo){
        SQLiteOpenHelper DBHelper = new DatabaseHelper(context);
        String tabla ;
        SQLiteDatabase  bd = DBHelper.getWritableDatabase();
        if(tipo.equalsIgnoreCase("boletines")){
            tabla = "boletin";
        }
        else{
            tabla = "noticia";
        }
        Cursor fila_db = bd.rawQuery("select * from "+tabla, null);

        noticias.clear();
        if (fila_db.moveToFirst()) {

            while (fila_db.isAfterLast() == false) {
                String n_titulo = fila_db.getString(fila_db.getColumnIndex("titulo"));
                String n_link = fila_db.getString(fila_db.getColumnIndex("link"));
                String n_dia = fila_db.getString(fila_db.getColumnIndex("dia"));
                String n_mes = fila_db.getString(fila_db.getColumnIndex("mes"));
                String n_contenido = fila_db.getString(fila_db.getColumnIndex("contenidoprevio"));
                String n_urlimg = fila_db.getString(fila_db.getColumnIndex("urlimagen"));
                Noticia noticia = new Noticia(n_titulo, n_link, n_dia+"/"+n_mes, n_contenido, n_urlimg);
                if(!noticias.contains(noticia))
                    noticias.add(noticia);
                fila_db.moveToNext();
            }
        }
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progDailog = new ProgressDialog(context);
        progDailog.setMessage("Loading...");
        progDailog.setIndeterminate(false);
        progDailog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progDailog.setCancelable(false);
        progDailog.show();
    }



    public void setNoticias(LinkedList<Noticia> noticias) {
        this.noticias = noticias;
    }

    @Override
    protected void onPostExecute(Void result) {
            progDailog.dismiss();
        NoticiasFragment.adapter.notifyDataSetChanged();
    }

    public Boolean isOnlineNet() {

        ConnectivityManager cm;
        NetworkInfo ni;
        cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        ni = cm.getActiveNetworkInfo();
        boolean tipoConexion1 = false;
        boolean tipoConexion2 = false;

        if (ni != null) {
            ConnectivityManager connManager1 = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mWifi = connManager1.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

            ConnectivityManager connManager2 = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
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



}