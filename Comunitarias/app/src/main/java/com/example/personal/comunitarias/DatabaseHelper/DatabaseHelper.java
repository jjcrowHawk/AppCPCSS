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
    private static final String TABLE_SECTOR = "sector";
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
    private static final String KEY_REGION_ID = "regionid";

    // CIUDAD Table - column names
    private static final String KEY_CIUDAD_NOMBRE = "nombre";
    private static final String KEY_PROVINCIA_ID = "provinciaid";

    // RECLAMO Table - column names
    private static final String KEY_RECLAMO_NOMBRESAPELLIDOSDENUNCIANTE = "nombresapellidosdenunciante";
    private static final String KEY_RECLAMO_TIPOIDENTIFICACION = "tipoidentificacion";
    private static final String KEY_RECLAMO_NUMIDENTI = "nummidenti";
    private static final String KEY_RECLAMO_DIRECCION = "direccion";
    private static final String KEY_RECLAMO_EMAIL = "email";
    private static final String KEY_RECLAMO_NOMBRESAPELLIDOSDENUNCIADO = "nombresapellidosdenunciado";
    private static final String KEY_RECLAMO_TELEFONO = "telefono";
    private static final String KEY_RECLAMO_CARGO = "cargo";
    private static final String KEY_RECLAMO_COMPARECER = "comparecer";
    private static final String KEY_RECLAMO_DOCUMENTORES = "documentores";
    private static final String KEY_RECLAMO_IDENTIDADRESERVADA = "identidadreservada";
    private static final String KEY_RECLAMO_RESIDEEXTRANJERO = "resideextranjero";
    private static final String KEY_CIUDADDENUNCIANTE_ID = "ciudaddenuncianteid";
    private static final String KEY_CIUDADDENUNCIADO_ID = "ciudaddenunciadoid";
    private static final String KEY_INSTITUCIONIMPLICADA_ID = "institucionimplicadaid";
    private static final String KEY_PROVINCIADENUNCIANTE_ID = "provinciadenuncianteid";
    private static final String KEY_PROVINCIADENUNCIADO_ID = "provinciadenunciadoid";

    // SECTOR Table - column names
    private static final String KEY_SECTOR_NOMBRE = "nombre";
    private static final String KEY_SECTOR_DESCRIPCION = "descripcion";
    private static final String KEY_SECTOR_CONTROL = "control";
    private static final String KEY_SECTOR_MENSAJE = "mensaje";

    // INSTITUCION Table - column names
    private static final String KEY_INSTITUCION_NOMBRE = "nombre";
    private static final String KEY_INSTITUCION_DESCRIPCION = "descripcion";
    private static final String KEY_INSTITUCION_URL = "url";
    private static final String KEY_INSTITUCION_EMAIL = "email";
    private static final String KEY_INSTITUCION_COMPETENCIA = "competencia";
    private static final String KEY_INSTITUCION_REPRESENTANTE = "representante";
    private static final String KEY_INSTITUCION_PUBLICA = "publica";
    private static final String KEY_SECTOR_ID = "sectorid";

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

    // Provincia table create statement
    private static final String CREATE_TABLE_PROVINCIA = "CREATE TABLE "
            + TABLE_PROVINCIA + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_PROVINCIA_NOMBRE
            + " TEXT," + KEY_REGION_DESCRIPCION
            + " TEXT," + KEY_REGION_ID + " INTEGER" + ")";

    // Ciudad table create statement
    private static final String CREATE_TABLE_CIUDAD = "CREATE TABLE "
            + TABLE_CIUDAD + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_CIUDAD_NOMBRE
            + " TEXT," +  KEY_PROVINCIA_ID + " INTEGER" + ")";

    // Reclamo table create statement
    private static final String CREATE_TABLE_RECLAMO = "CREATE TABLE "
            + TABLE_RECLAMO + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_RECLAMO_NOMBRESAPELLIDOSDENUNCIANTE
            + " TEXT," + KEY_RECLAMO_TIPOIDENTIFICACION
            + " TEXT," + KEY_RECLAMO_NUMIDENTI
            + " TEXT," + KEY_RECLAMO_DIRECCION
            + " TEXT," + KEY_RECLAMO_EMAIL
            + " TEXT," + KEY_RECLAMO_NOMBRESAPELLIDOSDENUNCIADO
            + " TEXT," + KEY_RECLAMO_TELEFONO
            + " TEXT," + KEY_RECLAMO_CARGO
            + " TEXT," + KEY_RECLAMO_COMPARECER
            + " TEXT," + KEY_RECLAMO_DOCUMENTORES
            + " TEXT," + KEY_RECLAMO_IDENTIDADRESERVADA
            + " TEXT," + KEY_RECLAMO_RESIDEEXTRANJERO
            + " TEXT," + KEY_CIUDADDENUNCIANTE_ID
            + " INTEGER," + KEY_CIUDADDENUNCIADO_ID
            + " INTEGER," + KEY_INSTITUCIONIMPLICADA_ID
            + " INTEGER," + KEY_PROVINCIADENUNCIANTE_ID
            + " INTEGER," + KEY_PROVINCIADENUNCIADO_ID
            + " INTEGER," + KEY_PROVINCIA_ID + " INTEGER" + ")";

    // Sector table create statement
    private static final String CREATE_TABLE_SECTOR = "CREATE TABLE "
            + TABLE_SECTOR + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_SECTOR_NOMBRE
            + " TEXT," + KEY_SECTOR_DESCRIPCION
            + " TEXT," + KEY_SECTOR_MENSAJE
            + " TEXT," + KEY_SECTOR_CONTROL
            + " TEXT" + ")";

    // Institucion table create statement
    private static final String CREATE_TABLE_INSTITUCION= "CREATE TABLE "
            + TABLE_INSTITUCION + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_INSTITUCION_NOMBRE
            + " TEXT," + KEY_INSTITUCION_DESCRIPCION
            + " TEXT," + KEY_INSTITUCION_URL
            + " TEXT," + KEY_INSTITUCION_EMAIL
            + " TEXT," + KEY_INSTITUCION_COMPETENCIA
            + " TEXT," + KEY_INSTITUCION_REPRESENTANTE
            + " TEXT," + KEY_INSTITUCION_PUBLICA
            + " TEXT," + KEY_SECTOR_ID+ " INTEGER" + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating required tables
        db.execSQL(CREATE_TABLE_NOTICIA);
        db.execSQL(CREATE_TABLE_REGION);
        db.execSQL(CREATE_TABLE_PROVINCIA);
        db.execSQL(CREATE_TABLE_CIUDAD);
        db.execSQL(CREATE_TABLE_RECLAMO);
        db.execSQL(CREATE_TABLE_SECTOR);
        db.execSQL(CREATE_TABLE_INSTITUCION);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTICIA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REGION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROVINCIA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CIUDAD);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECLAMO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SECTOR);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INSTITUCION);

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