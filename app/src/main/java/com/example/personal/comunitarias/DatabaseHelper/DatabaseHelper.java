package com.example.personal.comunitarias.DatabaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.personal.comunitarias.BaseDeDatos.ciudad.Ciudad;
import com.example.personal.comunitarias.BaseDeDatos.provincia.Provincia;
import com.example.personal.comunitarias.BaseDeDatos.region.Region;

import java.util.ArrayList;
import java.util.List;

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
    private static final String KEY_RECLAMOINSTITUCIONIMPLICADA_ID = "institucionimplicadaid";
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

    // PREDENUNCIA Table - column names
    private static final String KEY_PREDENUNCIA_TIPODENUNCIA = "tipodenuncia";
    private static final String KEY_PREDENUNCIA_GENERODENUNCIANTE = "generodenunciante";
    private static final String KEY_PREDENUNCIA_GENERODENUNCIADO = "generodenunciado";
    private static final String KEY_PREDENUNCIA_DESCRIPCIONINVESTIGACION = "descripcioninvestigacion";
    private static final String KEY_PREDENUNCIA_FUNCIONARIOPUBLICO = "funcionariopublico";
    private static final String KEY_NIVELEDUCACIONDENUNCIANTE_ID = "niveleducaciondenuncianteid";
    private static final String KEY_OCUPACIONNDENUNCIANTE_ID = "ocupaciondenuncianteid";
    private static final String KEY_NACIONALIDADDENUNCIANTE_ID = "nacionalidaddenuncianteid";
    private static final String KEY_ESTADOCIVILNDENUNCIANTE_ID = "estadocivildenuncianteid";
    private static final String KEY_PREDENUNCIAINSTITUCIONIMPLICADA_ID = "institucionimplicadaid";

    // OCUPACION Table - column names
    private static final String KEY_OCUPACION_NOMBRE = "nombre";
    private static final String KEY_OCUPACION_DESCRIPCION = "descripcion";

    // ESTADOCIVIL Table - column names
    private static final String KEY_ESTADOCIVIL_NOMBRE = "nombre";

    // NACIONALIDAD Table - column names
    private static final String KEY_NACIONALIDAD_NOMBRE = "nombre";

    // NIVELEDUCACION Table - column names
    private static final String KEY_NIVELEDUCACION_NOMBRE = "nombre";
    private static final String KEY_NIVELEDUCACION_DESCRIPCION = "descripcion";

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

    // Region table create statement
//    private static final String CREATE_TABLE_REGION = "CREATE TABLE "
//            + TABLE_REGION + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_REGION_NOMBRE
//            + " TEXT," + KEY_REGION_DESCRIPCION
//            + " TEXT" + ")";

    private static final String CREATE_TABLE_REGION = "CREATE TABLE "
            + TABLE_REGION + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_REGION_NOMBRE
            + " TEXT," + KEY_REGION_DESCRIPCION + " TEXT" + ")";

    // Provincia table create statement
    private static final String CREATE_TABLE_PROVINCIA = "CREATE TABLE "
            + TABLE_PROVINCIA + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_PROVINCIA_NOMBRE
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
            + " INTEGER," + KEY_RECLAMOINSTITUCIONIMPLICADA_ID
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

    // Pre-denuncia table create statement
    private static final String CREATE_TABLE_PREDENUNCIA= "CREATE TABLE "
            + TABLE_PREDENUNCIA + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_PREDENUNCIA_TIPODENUNCIA
            + " TEXT," + KEY_PREDENUNCIA_GENERODENUNCIANTE
            + " TEXT," + KEY_PREDENUNCIA_GENERODENUNCIADO
            + " TEXT," + KEY_PREDENUNCIA_DESCRIPCIONINVESTIGACION
            + " TEXT," + KEY_PREDENUNCIA_FUNCIONARIOPUBLICO
            + " TEXT," + KEY_NIVELEDUCACIONDENUNCIANTE_ID
            + " INTEGER," + KEY_OCUPACIONNDENUNCIANTE_ID
            + " INTEGER," + KEY_NACIONALIDADDENUNCIANTE_ID
            + " INTEGER," + KEY_ESTADOCIVILNDENUNCIANTE_ID
            + " INTEGER," +  KEY_PREDENUNCIAINSTITUCIONIMPLICADA_ID + " INTEGER" + ")";

    // Ocupacion table create statement
    private static final String CREATE_TABLE_OCUPACION = "CREATE TABLE "
            + TABLE_OCUPACION + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_OCUPACION_NOMBRE
            + " TEXT," + KEY_OCUPACION_DESCRIPCION
            + " TEXT" + ")";

    // NivelEducacion table create statement
    private static final String CREATE_TABLE_NIVELEDUCACION = "CREATE TABLE "
            + TABLE_NIVELEDUCACION + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NIVELEDUCACION_NOMBRE
            + " TEXT," + KEY_NIVELEDUCACION_DESCRIPCION
            + " TEXT" + ")";

    // Nacionalidad table create statement
    private static final String CREATE_TABLE_NACIONALIDAD = "CREATE TABLE "
            + TABLE_NACIONALIDAD + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NACIONALIDAD_NOMBRE
            + " TEXT" +  ")";

    // EstadoCivil table create statement
    private static final String CREATE_TABLE_ESTADOCIVIL = "CREATE TABLE "
            + TABLE_ESTADOCIVIL + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_ESTADOCIVIL_NOMBRE
            + " TEXT" +  ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }
    //--------------------------- FIN CREATE ----------------------------------------------------//
    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.print(CREATE_TABLE_REGION);
        // creating required tables
        db.execSQL(CREATE_TABLE_NOTICIA);
        db.execSQL(CREATE_TABLE_REGION);
        db.execSQL(CREATE_TABLE_PROVINCIA);
        db.execSQL(CREATE_TABLE_CIUDAD);
        db.execSQL(CREATE_TABLE_RECLAMO);
        db.execSQL(CREATE_TABLE_SECTOR);
        db.execSQL(CREATE_TABLE_INSTITUCION);
        db.execSQL(CREATE_TABLE_PREDENUNCIA);
        db.execSQL(CREATE_TABLE_OCUPACION);
        db.execSQL(CREATE_TABLE_NIVELEDUCACION);
        db.execSQL(CREATE_TABLE_NACIONALIDAD);
        db.execSQL(CREATE_TABLE_ESTADOCIVIL);

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
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PREDENUNCIA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_OCUPACION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NIVELEDUCACION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NACIONALIDAD);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ESTADOCIVIL);

        // create new tables
        onCreate(db);
    }

    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

    //-----------------------------------------CRUDS functions---------------------------------------//

    public long createRegion(Region region) {
        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(KEY_REGION_NOMBRE, region.getNombre());
        values.put(KEY_REGION_DESCRIPCION, region.getDescripcion());

        // insert row
        long region_id = db.insert(TABLE_REGION, null, values);

        return region_id;
    }

//    public long createProvincia(Provincia provincia, long[] regiones_ids) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(KEY_PROVINCIA_NOMBRE, provincia.getNombre());
//
//        // insert row
//        long provincia_id = db.insert(TABLE_REGION, null, values);
//
//        for (long region_id : regiones_ids) {
//            //createRegionProvincia(region_id, provincia_id);
//        }
//
//        return provincia_id;
//    }

    public long createProvinciaRegion(Provincia provincia, long region_id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_REGION_ID, region_id);
        values.put(KEY_PROVINCIA_NOMBRE, provincia.getNombre());

        long id = db.insert(TABLE_PROVINCIA, null, values);

        return id;
    }

    public long createCiudad(Ciudad ciudad, long provincia_id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PROVINCIA_ID, provincia_id);
        values.put(KEY_CIUDAD_NOMBRE, ciudad.getNombre());

        long id = db.insert(TABLE_CIUDAD, null, values);

        return id;
    }

    //---------------------SELECT-------------------------------------------------
    public Region getRegion(long region_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_REGION + " WHERE "
                + KEY_ID + " = " + region_id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Region r = new Region();
        r.setIdregion((c.getInt(c.getColumnIndex(KEY_ID))));
        r.setNombre((c.getString(c.getColumnIndex(KEY_REGION_NOMBRE))));
        r.setDescripcion(c.getString(c.getColumnIndex(KEY_REGION_DESCRIPCION)));

        return r;
    }

    public Provincia getProvincia(long provincia_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_PROVINCIA + " WHERE "
                + KEY_ID + " = " + provincia_id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Provincia p = new Provincia();
        p.setIdprovincia((c.getInt(c.getColumnIndex(KEY_ID))));
        p.setNombre((c.getString(c.getColumnIndex(KEY_PROVINCIA_NOMBRE))));
        p.setRegionid((c.getInt(c.getColumnIndex(KEY_REGION_ID))));

        return p;
    }

    public Ciudad getCiudad(long ciudad_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_CIUDAD + " WHERE "
                + KEY_ID + " = " + ciudad_id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Ciudad ci = new Ciudad();
        ci.setIdciudad(c.getInt(c.getColumnIndex(KEY_ID)));
        ci.setNombre(c.getString(c.getColumnIndex(KEY_CIUDAD_NOMBRE)));
        ci.setProvinciaid(c.getInt(c.getColumnIndex(KEY_PROVINCIA_ID)));

        return ci;
    }

    //---------------------- SELECT ALL ------------------------

    //SELECT ALL REGIONS
    public List<Region> getAllRegion() {
        List<Region> todos = new ArrayList<Region>();
        String selectQuery = "SELECT  * FROM " + TABLE_REGION;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Region r = new Region();
                r.setIdregion(c.getInt((c.getColumnIndex(KEY_ID))));
                r.setNombre((c.getString(c.getColumnIndex(KEY_REGION_NOMBRE))));
                r.setDescripcion(c.getString(c.getColumnIndex(KEY_REGION_DESCRIPCION)));

                // adding to todo list
                todos.add(r);
            } while (c.moveToNext());
        }

        return todos;
    }
    //SELECT ALL PROVINICIA
    public List<Provincia> getAllProvincias() {
        List<Provincia> tags = new ArrayList<Provincia>();
        String selectQuery = "SELECT  * FROM " + TABLE_PROVINCIA;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Provincia p = new Provincia();
                p.setIdprovincia(c.getInt((c.getColumnIndex(KEY_ID))));
                p.setNombre(c.getString(c.getColumnIndex(KEY_PROVINCIA_NOMBRE)));

                // adding to tags list
                tags.add(p);
            } while (c.moveToNext());
        }
        return tags;
    }

    //SELECT ALL CIUDAD
    public List<Ciudad> getAllCiudades() {
        List<Ciudad> tags = new ArrayList<Ciudad>();
        String selectQuery = "SELECT  * FROM " + TABLE_CIUDAD;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Ciudad ci = new Ciudad();
                ci.setIdciudad(c.getInt((c.getColumnIndex(KEY_ID))));
                ci.setNombre(c.getString(c.getColumnIndex(KEY_CIUDAD_NOMBRE)));
                ci.setProvinciaid(c.getInt(c.getColumnIndex(KEY_PROVINCIA_ID)));

                // adding to tags list
                tags.add(ci);
            } while (c.moveToNext());
        }
        return tags;
    }

    //------------------------------------ UPDATE -------------------------------------------//
    public void updateProvincia_Nombre(int id,String nombre){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_PROVINCIA_NOMBRE, nombre);
        db.update(TABLE_PROVINCIA, values, KEY_ID+"'"+id+"'", null);
    }

    public void updateRegion_Nombre(int id,String nombre){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_REGION_NOMBRE, nombre);
        db.update(TABLE_REGION, values, KEY_ID+"'"+id+"'", null);
    }

    public void updateCiudad_Nombre(int id,String nombre){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_CIUDAD_NOMBRE, nombre);
        db.update(TABLE_CIUDAD, values, KEY_ID+"='"+id+"'", null);
    }

    //------------------------------ DELETE --------------------------------------

    public boolean deleteProvincia(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_PROVINCIA, KEY_ID + "='" + id+"'", null) > 0;
    }

    public boolean deleteRegion(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_REGION, KEY_ID + "='" + id+"'", null) > 0;
    }

    public boolean deleteCiudad(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_CIUDAD, KEY_ID + "='" + id+"'", null) > 0;
    }

}