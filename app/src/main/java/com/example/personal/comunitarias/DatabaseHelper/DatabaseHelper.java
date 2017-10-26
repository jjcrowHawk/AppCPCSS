package com.example.personal.comunitarias.DatabaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Kattya Desiderio on 15/01/2017.
 */


public class DatabaseHelper extends SQLiteOpenHelper {

    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "cpccs";

    // Table Names
    private static final String TABLE_NOTICIA = "noticia";
    private static final String TABLE_BOLETIN = "boletin";

    // Common column names
    private static final String KEY_ID = "id";

    // NOTICIA Table - column names
    private static final String KEY_NOTICIA_TITULO = "titulo";
    private static final String KEY_NOTICIA_URLIMAGEN = "urlimagen";
    private static final String KEY_NOTICIA_CONTENIDOPREVIO = "contenidoprevio";
    private static final String KEY_NOTICIA_LINK = "link";
    private static final String KEY_NOTICIA_MES = "mes";
    private static final String KEY_NOTICIA_DIA = "dia";

    // BOLETIN Table - column names
    private static final String KEY_BOLETIN_TITULO = "titulo";
    private static final String KEY_BOLETIN_URLIMAGEN = "urlimagen";
    private static final String KEY_BOLETIN_CONTENIDOPREVIO = "contenidoprevio";
    private static final String KEY_BOLETIN_LINK = "link";
    private static final String KEY_BOLETIN_MES = "mes";
    private static final String KEY_BOLETIN_DIA = "dia";

    //------------- Table Create Statements --------------------------------------------//
    // Noticia table create statement
    private static final String CREATE_TABLE_NOTICIA = "CREATE TABLE "
            + TABLE_NOTICIA + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NOTICIA_TITULO
            + " TEXT," + KEY_NOTICIA_URLIMAGEN
            + " TEXT," + KEY_NOTICIA_CONTENIDOPREVIO
            + " TEXT," + KEY_NOTICIA_LINK
            + " TEXT," + KEY_NOTICIA_MES
            + " TEXT," + KEY_NOTICIA_DIA
            + " TEXT" + ")";

    // Boletin table create statement
    private static final String CREATE_TABLE_BOLETIN = "CREATE TABLE "
            + TABLE_BOLETIN + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_BOLETIN_TITULO
            + " TEXT," + KEY_BOLETIN_URLIMAGEN
            + " TEXT," + KEY_BOLETIN_CONTENIDOPREVIO
            + " TEXT," + KEY_BOLETIN_LINK
            + " TEXT," + KEY_BOLETIN_MES
            + " TEXT," + KEY_BOLETIN_DIA
            + " TEXT" + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    //--------------------------- FIN CREATE ----------------------------------------------------//
    @Override
    public void onCreate(SQLiteDatabase db) {
        // creating required tables
        db.execSQL(CREATE_TABLE_NOTICIA);
        db.execSQL(CREATE_TABLE_BOLETIN);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTICIA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOLETIN);

        // create new tables
        onCreate(db);
    }

    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

    //----------------------------------Inicilizar base con datos de la base remota--------------------------------------//


    //-----------------------------------------CRUDS functions---------------------------------------//


    //---------------------SELECT-------------------------------------------------

   // public Noticia getNoticia(long noticia_id) {
   //     SQLiteDatabase db = this.getReadableDatabase();
//
   //     String selectQuery = "SELECT  * FROM " + TABLE_CIUDAD + " WHERE "
   //             + KEY_ID + " = " + noticia_id;
//
   //     Log.e(LOG, selectQuery);
//
   //     Cursor c = db.rawQuery(selectQuery, null);
//
   //     if (c != null)
   //         c.moveToFirst();
//
   //     Noticia n = new Noticia();
   //     n.setTitulo(c.getString(c.getColumnIndex(KEY_NOTICIA_TITULO)));
   //     n.setDescripcion(c.getString(c.getColumnIndex(KEY_NOTICIA_CONTENIDOPREVIO)));
   //     n.setUrl(c.getString(c.getColumnIndex(KEY_NOTICIA_LINK)));
   //     n.setUrlImg(c.getString(c.getColumnIndex(KEY_NOTICIA_URLIMAGEN)));
   //     n.setDia(c.getInt(c.getColumnIndex(KEY_NOTICIA_DIA)));
   //     n.setMes(c.getInt(c.getColumnIndex(KEY_NOTICIA_MES)));
//
   //     return n;
   // }

    //---------------------- SELECT ALL ------------------------


   // //SELECT ALL NOTICIA
   // public List<Noticia> getAllNoticias() {
   //     List<Noticia> tags = new ArrayList<Noticia>();
   //     String selectQuery = "SELECT  * FROM " + TABLE_NOTICIA;
//
   //     Log.e(LOG, selectQuery);
//
   //     SQLiteDatabase db = this.getReadableDatabase();
   //     Cursor c = db.rawQuery(selectQuery, null);
//
   //     // looping through all rows and adding to list
   //     if (c.moveToFirst()) {
   //         do {
   //             Noticia n = new Noticia();
   //             n.setTitulo(c.getString(c.getColumnIndex(KEY_NOTICIA_TITULO)));
   //             n.setDescripcion(c.getString(c.getColumnIndex(KEY_NOTICIA_CONTENIDOPREVIO)));
   //             n.setUrl(c.getString(c.getColumnIndex(KEY_NOTICIA_LINK)));
   //             n.setUrlImg(c.getString(c.getColumnIndex(KEY_NOTICIA_URLIMAGEN)));
   //             n.setDia(c.getInt(c.getColumnIndex(KEY_NOTICIA_DIA)));
   //             n.setMes(c.getInt(c.getColumnIndex(KEY_NOTICIA_MES)));
//
   //             // adding to tags list
   //             tags.add(n);
   //         } while (c.moveToNext());
   //     }
   //     return tags;
   // }





    //------------------------------------ UPDATE -------------------------------------------//

    public void updateNoticia_Titulo(int id,String titulo){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NOTICIA_TITULO, titulo);
        db.update(TABLE_NOTICIA, values, KEY_ID+"='"+id+"'", null);
    }


    //------------------------------ DELETE --------------------------------------

    public boolean deleteNoticia(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NOTICIA, KEY_ID + "='" + id+"'", null) > 0;
    }


}