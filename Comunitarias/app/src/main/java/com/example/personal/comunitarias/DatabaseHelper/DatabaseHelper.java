package com.example.personal.comunitarias.DatabaseHelper;

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
    private static final String DATABASE_NAME = "cpcss";

    // Table Names
    private static final String TABLE_NOTICIA = "noticia";
    private static final String TABLE_REGION = "region";
    private static final String TABLE_PROVINCIA = "provincia";
    private static final String TABLE_CIUDAD = "ciudad";
    private static final String TABLE_RECLAMO = "reclamo";
    private static final String TABLE_SECTOR = "region";
    private static final String TABLE_INSTITUCION = "institucion";
    private static final String TABLE_PREDENUNCIA = "predenuncia";
    private static final String TABLE_OCUPACION = "ocupacion";
    private static final String TABLE_ESTADOCIVIL = "estadocivil";
    private static final String TABLE_NACIONALIDAD = "nacionalidad";
    private static final String TABLE_NIVELEDUCACION = "niveleducacion";

    // Common column names
    private static final String KEY_ID = "id";

    // NOTICIA Table - column names
    private static final String KEY_NOTICIA_TITULO = "titulo";
    private static final String KEY_NOTICIA_URLIMAGEN = "urlimagen";
    private static final String KEY_NOTICIA_CONTENIDOPREVIO = "contenidoprevio";
    private static final String KEY_NOTICIA_LINK = "link";
    private static final String KEY_NOTICIA_MES = "mes";
    private static final String KEY_NOTICIA_DIA = "dia";

    // REGION Table - column names
    private static final String KEY_REGION_NOMBRE = "nombre";
    private static final String KEY_REGION_DESCRIPCION = "descripcion";

    // PROVINCIA Table - column names
    private static final String KEY_PROVINCIA_NOMBRE = "nombre";
    private static final String KEY_REGION_ID = "region_id";


    // Table Create Statements
    // Noticia table create statement
    private static final String CREATE_TABLE_NOTICIA = "CREATE TABLE "
            + TABLE_NOTICIA + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NOTICIA_TITULO
            + " TEXT," + KEY_NOTICIA_URLIMAGEN
            + " TEXT," + KEY_NOTICIA_CONTENIDOPREVIO
            + " TEXT," + KEY_NOTICIA_LINK
            + " TEXT," + KEY_NOTICIA_MES
            + " TEXT," + KEY_NOTICIA_DIA
            + " TEXT" + ")";

    // Region table create statement
    private static final String CREATE_TABLE_REGION = "CREATE TABLE "
            + TABLE_REGION + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_REGION_NOMBRE
            + " TEXT" + ")";

    // Region table create statement
    private static final String CREATE_TABLE_PROVINCIA = "CREATE TABLE "
            + TABLE_PROVINCIA + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_PROVINCIA_NOMBRE
            + " TEXT," + KEY_REGION_DESCRIPCION
            + " TEXT," + KEY_REGION_ID + " INTEGER" + ")";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating required tables
        db.execSQL(CREATE_TABLE_NOTICIA);
        db.execSQL(CREATE_TABLE_REGION);
        db.execSQL(CREATE_TABLE_PROVINCIA);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTICIA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REGION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROVINCIA);

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