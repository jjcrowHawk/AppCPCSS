package com.example.personal.comunitarias.Noticias;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class NoticiasDataBase extends SQLiteOpenHelper {

    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "cpccs_noticias";

    // Table Names
    private static final String TABLE_NOTICIA = "noticia";

    // Common column names
    private static final String KEY_ID = "id";

    // NOTICIA Table - column names
    private static final String KEY_NOTICIA_TITULO = "titulo";
    private static final String KEY_NOTICIA_URLIMAGEN = "urlimagen";
    private static final String KEY_NOTICIA_CONTENIDOPREVIO = "contenidoprevio";
    private static final String KEY_NOTICIA_LINK = "link";
    private static final String KEY_NOTICIA_MES = "mes";
    private static final String KEY_NOTICIA_DIA = "dia";


    // Table Create Statements
    // Noticia table create statement
    private static final String CREATE_TABLE_NOTICIA = "CREATE TABLE "
            + TABLE_NOTICIA + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NOTICIA_TITULO
            + " TEXT," + KEY_NOTICIA_URLIMAGEN
            + " TEXT," + KEY_NOTICIA_CONTENIDOPREVIO
            + " TEXT," + KEY_NOTICIA_LINK
            + " TEXT," + KEY_NOTICIA_MES
            + " TEXT," + KEY_NOTICIA_DIA
            + " TEXT" + ")";




    public NoticiasDataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating required tables
        db.execSQL(CREATE_TABLE_NOTICIA);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTICIA);

        // create new tables
        onCreate(db);
    }

    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }
}