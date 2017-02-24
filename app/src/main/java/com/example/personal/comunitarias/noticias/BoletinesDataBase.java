package com.example.personal.comunitarias.noticias;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class BoletinesDataBase extends SQLiteOpenHelper {

    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "cpccs2";

    // Table Names
    private static final String TABLE_BOLETIN = "boletin";

    // Common column names
    private static final String KEY_ID = "id";


    private static final String KEY_BOLETIN_TITULO = "titulo";
    private static final String KEY_BOLETIN_URLIMAGEN = "urlimagen";
    private static final String KEY_BOLETIN_CONTENIDOPREVIO = "contenidoprevio";
    private static final String KEY_BOLETIN_LINK = "link";
    private static final String KEY_BOLETIN_MES = "mes";
    private static final String KEY_BOLETIN_DIA = "dia";



    // Table Create Statements

    // boletin table create statement
    private static final String CREATE_TABLE_BOLETIN = "CREATE TABLE "
            + TABLE_BOLETIN + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_BOLETIN_TITULO
            + " TEXT," + KEY_BOLETIN_URLIMAGEN
            + " TEXT," + KEY_BOLETIN_CONTENIDOPREVIO
            + " TEXT," + KEY_BOLETIN_LINK
            + " TEXT," + KEY_BOLETIN_MES
            + " TEXT," + KEY_BOLETIN_DIA
            + " TEXT" + ")";




    public BoletinesDataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating required tables
        db.execSQL(CREATE_TABLE_BOLETIN);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
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
}