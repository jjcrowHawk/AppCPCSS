package com.example.personal.comunitarias.Noticias;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Palacios on 22/02/2017.
 */
public class PrimaryReader extends AsyncTask<String, Void, Void> {
    private String url;
    private Context context;
    private List<Noticia> noticias;
    private boolean stop;


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
                noticias.clear();
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
                    //Noticia noticia = new Noticia(tit, link, dia + "/" + mes, content, imgurl);
                    //noticias.add(noticia);

                    SQLiteOpenHelper DBHelper ;
                    String tabla ;
                    SQLiteDatabase bd;
                    if(tipo.equalsIgnoreCase("boletines")){
                        DBHelper = new BoletinesDataBase(context);
                        bd = DBHelper.getWritableDatabase();
                        tabla = "boletin";
                    }
                    else{
                        DBHelper = new NoticiasDataBase(context);
                        bd = DBHelper.getWritableDatabase();
                        tabla = "noticia";
                    }
                    Cursor fila = bd.rawQuery("select titulo,contenidoprevio from "+ tabla +" where titulo='" + tit + "'", null);


                    if (!fila.moveToFirst()) {
                        ContentValues registro = new ContentValues();
                        registro.put("titulo", tit);
                        registro.put("link", link);
                        registro.put("contenidoprevio", content);
                        registro.put("dia", dia);
                        registro.put("mes", mes);
                        registro.put("urlimagen", imgurl);


                        bd.insert(tabla, null, registro);
                    }
                    //Para debug
                    //Cursor fila1 = bd.rawQuery("select contenidoprevio from noticia", null);
                    //Log.e("FILA1", "" + fila1.getCount());

                    bd.close();
                }
            }

            //Se lee de la base de datos
            SQLiteOpenHelper DBHelper ;
            String tabla ;
            SQLiteDatabase bd;
            if(tipo.equalsIgnoreCase("boletines")){
                DBHelper = new BoletinesDataBase(context);
                bd = DBHelper.getWritableDatabase();
                tabla = "boletin";
            }
            else{
                DBHelper = new NoticiasDataBase(context);
                bd = DBHelper.getWritableDatabase();
                tabla = "noticia";
            }
            Cursor fila_db = bd.rawQuery("select * from "+tabla, null);


            if (fila_db.moveToFirst()) {

                while (fila_db.isAfterLast() == false) {
                    String n_titulo = fila_db.getString(fila_db.getColumnIndex("titulo"));
                    String n_link = fila_db.getString(fila_db.getColumnIndex("link"));
                    String n_dia = fila_db.getString(fila_db.getColumnIndex("dia"));
                    String n_mes = fila_db.getString(fila_db.getColumnIndex("mes"));
                    String n_contenido = fila_db.getString(fila_db.getColumnIndex("contenidoprevio"));
                    String n_urlimg = fila_db.getString(fila_db.getColumnIndex("urlimagen"));
                    Noticia noticia = new Noticia(n_titulo, n_link, n_dia+"/"+n_mes, n_contenido, n_urlimg);
                    noticias.add(noticia);
                    fila_db.moveToNext();
                }
            }
            ////Log.e("FILA_DB", ""+fila_db.getCount());
            //bd.close();

        } catch (IOException e) {

            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }



    public void setNoticias(LinkedList<Noticia> noticias) {
        this.noticias = noticias;
    }

    @Override
    protected void onPostExecute(Void result) {

        if(stop) {
            if(!noticias.isEmpty()){
                Intent i=new Intent(context,NoticiasActivity.class);
                (context).startActivity(i);
                ((Intro_noticias)context).finish();
            }
            else{
                Intent i=new Intent(context,NoticiasActivity.class);
                (context).startActivity(i);
                ((Intro_noticias)context).finish();
                //smejsa esin noticias
            }

        }
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