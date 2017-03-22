package com.example.personal.comunitarias.DatabaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.personal.comunitarias.BaseDeDatos.ciudad.Ciudad;
import com.example.personal.comunitarias.BaseDeDatos.estadocivil.Estadocivil;
import com.example.personal.comunitarias.BaseDeDatos.institucion.Institucion;
import com.example.personal.comunitarias.BaseDeDatos.nacionalidad.Nacionalidad;
import com.example.personal.comunitarias.BaseDeDatos.niveleducacion.Niveleducacion;
import com.example.personal.comunitarias.BaseDeDatos.ocupacion.Ocupacion;
import com.example.personal.comunitarias.BaseDeDatos.provincia.Provincia;
import com.example.personal.comunitarias.BaseDeDatos.reclamo.Reclamo;
import com.example.personal.comunitarias.BaseDeDatos.region.Region;
import com.example.personal.comunitarias.BaseDeDatos.sector.Sector;
import com.example.personal.comunitarias.noticias.Noticia;

import java.sql.SQLException;
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

    //----------------------------------Inicilizar base con datos de la base remota--------------------------------------//
    public void inicializar()  {
        inicializar_estadocivil();
        inicializar_niveleducacion();
        inicializar_nacionalidad();
        inicializar_ocupacion();
        inicializar_provincia();
        inicializar_ciudad();
        inicializar_institucion();
    }

    public void inicializar_estadocivil() {
        ArrayList<Estadocivil> lista = new Estadocivil().getListaEstadoCivil();
        for (Estadocivil e: lista){
            createEstadoCivil(e);
        }
    }

    public void inicializar_niveleducacion()  {
        ArrayList<Niveleducacion> lista = new Niveleducacion().getListaNivelEducacion();
        for (Niveleducacion n: lista){
            createNivelEducacion(n);
        }
    }

    public void inicializar_nacionalidad() {
        ArrayList<Nacionalidad> lista = new Nacionalidad().getListaNacionalidad();
        for (Nacionalidad n: lista){
            createNacionalidad(n);
        }
    }

    public void inicializar_provincia() {
        ArrayList<Provincia> lista = new Provincia().getListaProvincia();
        for (Provincia p: lista){
            createProvincia(p);
        }
    }

    public void inicializar_ciudad(){
        ArrayList<Ciudad> lista = new Ciudad().getListaCiudad();
        for (Ciudad p: lista){
            createCiudad(p);
        }
    }

    public void inicializar_institucion(){
        ArrayList<Institucion> lista = new Institucion().getListaInstitucion();
        for (Institucion i: lista){
            createInstitucion(i);
        }
    }

    public void inicializar_ocupacion(){
        ArrayList<Ocupacion> lista = new Ocupacion().getListaOcupacion();
        Log.d("SIZE ocupacion: ", ""+lista.size());
        for (Ocupacion i: lista){
            createOcupacion(i);
        }
    }


    //-----------------------------------------CRUDS functions---------------------------------------//

    //Region
    public long createRegion(Region region) {
        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(KEY_REGION_NOMBRE, region.getNombre());
        values.put(KEY_REGION_DESCRIPCION, region.getDescripcion());

        // insert row
        long region_id = db.insert(TABLE_REGION, null, values);

        return region_id;
    }


    //ProvinciaRegion
    public long createProvinciaRegion(Provincia provincia, long region_id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_REGION_ID, region_id);
        values.put(KEY_PROVINCIA_NOMBRE, provincia.getNombre());

        long id = db.insert(TABLE_PROVINCIA, null, values);

        return id;
    }

    //Provincia
    public long createProvincia(Provincia provincia) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        long id=-1;

        if (getProvincia(provincia.getIdprovincia()).getNombre().equals("")) {
            values.put(KEY_ID, provincia.getIdprovincia());
            values.put(KEY_PROVINCIA_NOMBRE, provincia.getNombre());
            values.put(KEY_REGION_ID, 0);

            id = db.insert(TABLE_PROVINCIA, null, values);
        }


        return id;
    }

    //Ciudad
    public long createCiudad_prov(Ciudad ciudad, long provincia_id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PROVINCIA_ID, provincia_id);
        values.put(KEY_CIUDAD_NOMBRE, ciudad.getNombre());

        long id = db.insert(TABLE_CIUDAD, null, values);

        return id;
    }

    public long createCiudad(Ciudad ciudad) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        long id=-1;

        if (getCiudad(ciudad.getIdciudad()).getNombre().equals("")) {
            values.put(KEY_ID, ciudad.getIdciudad());
            values.put(KEY_PROVINCIA_ID, ciudad.getProvinciaid());
            values.put(KEY_CIUDAD_NOMBRE, ciudad.getNombre());

            id = db.insert(TABLE_CIUDAD, null, values);
        }



        return id;
    }

    //Noticia
    public long createNoticia(Noticia noticia, int dia, int mes) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NOTICIA_TITULO, noticia.getTitulo());
        values.put(KEY_NOTICIA_CONTENIDOPREVIO, noticia.getDescripcion());
        values.put(KEY_NOTICIA_DIA, dia);
        values.put(KEY_NOTICIA_MES, mes);
        values.put(KEY_NOTICIA_LINK, noticia.getUrl());
        values.put(KEY_NOTICIA_URLIMAGEN, noticia.getS_img());

        long id = db.insert(TABLE_NOTICIA, null, values);

        return id;
    }

    //Sector
    public long createSector(Sector sector) {
        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(KEY_SECTOR_NOMBRE, sector.getNombre());
        values.put(KEY_SECTOR_DESCRIPCION, sector.getDescripcion());
        values.put(KEY_SECTOR_CONTROL, sector.getControl());
        values.put(KEY_SECTOR_MENSAJE, sector.getMensaje());

        // insert row
        long sector_id = db.insert(TABLE_SECTOR, null, values);

        return sector_id;
    }

    //Institucion
    public long createInstitucion(Institucion institucion, long sector_id) {
        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(KEY_INSTITUCION_NOMBRE, institucion.getNombre());
        values.put(KEY_INSTITUCION_DESCRIPCION, institucion.getDescripcion());
        values.put(KEY_INSTITUCION_URL, institucion.getUrl());
        values.put(KEY_INSTITUCION_EMAIL, institucion.getEmail());
        values.put(KEY_INSTITUCION_COMPETENCIA, institucion.getCompetencia());
        values.put(KEY_INSTITUCION_REPRESENTANTE, institucion.getRepresentante());
        values.put(KEY_INSTITUCION_PUBLICA, institucion.getPublica());
        values.put(KEY_SECTOR_ID, sector_id);

        // insert row
        long institucion_id = db.insert(TABLE_INSTITUCION, null, values);

        return institucion_id;
    }

    public long createInstitucion(Institucion institucion) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        long id=-1;

        if (getInstitucion(institucion.getIdinstitucion()).getNombre().equals("")) {
            values.put(KEY_ID, institucion.getIdinstitucion());
            values.put(KEY_INSTITUCION_NOMBRE, institucion.getNombre());

            // insert row
            id = db.insert(TABLE_INSTITUCION, null, values);
        }



        return id;
    }

    //Ocupacion
    public long createOcupacion(Ocupacion ocupacion) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        long id=-1;

        if (getOcupacion(ocupacion.getIdocupacion()).getNombre().equals("")) {
            values.put(KEY_ID, ocupacion.getIdocupacion());
            values.put(KEY_OCUPACION_NOMBRE, ocupacion.getNombre());
            values.put(KEY_OCUPACION_DESCRIPCION, ocupacion.getDescripcion());

            // insert row
            id = db.insert(TABLE_OCUPACION, null, values);
        }

        return id;
    }

    //EstadoCivil
    public long createEstadoCivil(Estadocivil estadocivil) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        long estadocivil_id=-1;

        if (getEstadocivil(estadocivil.getIdestadocivil()).getNombre().equals("")){

            values.put(KEY_ESTADOCIVIL_NOMBRE, estadocivil.getNombre());
            values.put(KEY_ID, estadocivil.getIdestadocivil());
            // insert row
            estadocivil_id = db.insert(TABLE_ESTADOCIVIL, null, values);
        }

        return estadocivil_id;
    }

    //NivelEducacion
    public long createNivelEducacion(Niveleducacion niveleducacion) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        long niveleducacion_id=-1;

        if (getNiveleducacion(niveleducacion.getIdniveleducacion()).getNombre().equals("")) {
            values.put(KEY_ID, niveleducacion.getIdniveleducacion());
            values.put(KEY_NIVELEDUCACION_NOMBRE, niveleducacion.getNombre());
            values.put(KEY_NIVELEDUCACION_DESCRIPCION, niveleducacion.getDescripcion());

            // insert row
            niveleducacion_id = db.insert(TABLE_NIVELEDUCACION, null, values);
        }



        return niveleducacion_id;
    }

    //Nacinalidad
    public long createNacionalidad(Nacionalidad nacionalidad) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        long id=-1;

        if (getNacionalidad(nacionalidad.getIdnacionalidad()).getNombre().equals("")) {
            values.put(KEY_ID, nacionalidad.getIdnacionalidad());
            values.put(KEY_NACIONALIDAD_NOMBRE, nacionalidad.getNombre());

            // insert row
            id = db.insert(TABLE_NACIONALIDAD, null, values);
        }

        return id;
    }

    //Reclamo
    public long createReclamo(Reclamo reclamo,long ciudaddeldenunciante_id,
                              long ciudaddeldenunciado_id, long institucionimplicada_id,
                              long provinciadenunciante_id, long provinciadenunciado_id) {

        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(KEY_RECLAMO_NOMBRESAPELLIDOSDENUNCIANTE, reclamo.getNombresapellidosdenunciante());
        values.put(KEY_RECLAMO_TIPOIDENTIFICACION,reclamo.getTipoidentificacion());
        values.put(KEY_RECLAMO_NUMIDENTI,reclamo.getNumidenti());
        values.put(KEY_RECLAMO_DIRECCION,reclamo.getDireccion());
        values.put(KEY_RECLAMO_EMAIL,reclamo.getEmail());
        values.put(KEY_RECLAMO_NOMBRESAPELLIDOSDENUNCIADO,reclamo.getNombresapellidosdenunciado());
        values.put(KEY_RECLAMO_TELEFONO,reclamo.getTelefono());
        values.put(KEY_RECLAMO_CARGO,reclamo.getCargo());
        values.put(KEY_RECLAMO_COMPARECER,reclamo.getComparecer());
        values.put(KEY_RECLAMO_DOCUMENTORES,reclamo.getDocumentores());
        values.put(KEY_RECLAMO_IDENTIDADRESERVADA,reclamo.getIdentidadreservada());
        values.put(KEY_RECLAMO_RESIDEEXTRANJERO,reclamo.getResideextrangero());
        values.put(KEY_RECLAMO_NUMIDENTI,reclamo.getNumidenti());
        values.put(KEY_CIUDADDENUNCIANTE_ID, ciudaddeldenunciante_id);
        values.put(KEY_CIUDADDENUNCIADO_ID,ciudaddeldenunciado_id);
        values.put(KEY_RECLAMOINSTITUCIONIMPLICADA_ID,institucionimplicada_id);
        values.put(KEY_PROVINCIADENUNCIANTE_ID,provinciadenunciante_id);
        values.put(KEY_PROVINCIADENUNCIADO_ID,provinciadenunciado_id);

        // insert row
        long reclamo_id = db.insert(TABLE_RECLAMO, null, values);

        return reclamo_id;
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

    //GET PROVINCIA

    public Provincia getProvincia(long provincia_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_PROVINCIA + " WHERE "
                + KEY_ID + " ='" + provincia_id+"'";

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);
        Provincia p = new Provincia();
        if (c.moveToFirst()) {
            p.setIdprovincia((c.getInt(c.getColumnIndex(KEY_ID))));
            p.setNombre((c.getString(c.getColumnIndex(KEY_PROVINCIA_NOMBRE))));
            p.setRegionid((c.getInt(c.getColumnIndex(KEY_REGION_ID))));
        }
        return p;
    }

    public int getProvincia(String nombre) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_PROVINCIA + " WHERE "
                + KEY_PROVINCIA_NOMBRE + " ='" + nombre+"'";

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);
        Provincia p = new Provincia();
        if (c.moveToFirst()) {
            p.setIdprovincia((c.getInt(c.getColumnIndex(KEY_ID))));
            p.setNombre((c.getString(c.getColumnIndex(KEY_PROVINCIA_NOMBRE))));
            p.setRegionid((c.getInt(c.getColumnIndex(KEY_REGION_ID))));
        }
        return p.getIdprovincia();
    }
    //

    //GET CIUDAD
    public Ciudad getCiudad(long ciudad_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_CIUDAD + " WHERE "
                + KEY_ID + " ='" + ciudad_id+"'";

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);
        Ciudad ci = new Ciudad();

        if (c.moveToFirst()) {
            ci.setIdciudad(c.getInt(c.getColumnIndex(KEY_ID)));
            ci.setNombre(c.getString(c.getColumnIndex(KEY_CIUDAD_NOMBRE)));
            ci.setProvinciaid(c.getInt(c.getColumnIndex(KEY_PROVINCIA_ID)));
        }

        return ci;
    }

    public int getCiudad_id(String nombre) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_CIUDAD + " WHERE "
                + KEY_CIUDAD_NOMBRE + " ='" + nombre+"'";

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);
        Ciudad ci = new Ciudad();

        if (c.moveToFirst()) {
            ci.setIdciudad(c.getInt(c.getColumnIndex(KEY_ID)));
            ci.setNombre(c.getString(c.getColumnIndex(KEY_CIUDAD_NOMBRE)));
            ci.setProvinciaid(c.getInt(c.getColumnIndex(KEY_PROVINCIA_ID)));
        }

        return ci.getIdciudad();
    }

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
   //     n.setS_img(c.getString(c.getColumnIndex(KEY_NOTICIA_URLIMAGEN)));
   //     n.setDia(c.getInt(c.getColumnIndex(KEY_NOTICIA_DIA)));
   //     n.setMes(c.getInt(c.getColumnIndex(KEY_NOTICIA_MES)));
//
   //     return n;
   // }

    //GET NACIONALIDAD
    public Nacionalidad getNacionalidad(long nacionalidad_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_NACIONALIDAD + " WHERE "
                + KEY_ID + " ='" + nacionalidad_id+"'";

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);
        Nacionalidad n = new Nacionalidad();

        if (c.moveToFirst()) {
            n.setIdnacionalidad(c.getInt(c.getColumnIndex(KEY_ID)));
            n.setNombre(c.getString(c.getColumnIndex(KEY_NACIONALIDAD_NOMBRE)));
        }

        return n;
    }

    public int getNacionalidad_id(String nombre) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_NACIONALIDAD + " WHERE "
                + KEY_NACIONALIDAD_NOMBRE+ " ='" + nombre+"'";

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);
        Nacionalidad n = new Nacionalidad();

        if (c.moveToFirst()) {
            n.setIdnacionalidad(c.getInt(c.getColumnIndex(KEY_ID)));
            n.setNombre(c.getString(c.getColumnIndex(KEY_NACIONALIDAD_NOMBRE)));
        }

        return n.getIdnacionalidad();
    }
    //

    //GET SECTOR
    public Sector getSector(long sector_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_SECTOR+ " WHERE "
                + KEY_ID + " = " + sector_id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Sector s = new Sector();
        s.setIdsector(c.getInt(c.getColumnIndex(KEY_ID)));
        s.setNombre(c.getString(c.getColumnIndex(KEY_SECTOR_NOMBRE)));
        s.setDescripcion(c.getString(c.getColumnIndex(KEY_SECTOR_DESCRIPCION)));
        s.setControl(c.getString(c.getColumnIndex(KEY_SECTOR_CONTROL)));
        s.setMensaje(c.getString(c.getColumnIndex(KEY_SECTOR_MENSAJE)));

        return s;
    }

    //Ocupacion
    public Ocupacion getOcupacion(long ocupacion_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_OCUPACION+ " WHERE "
                + KEY_ID + " = " + ocupacion_id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);
        Ocupacion o = new Ocupacion();

        if (c.moveToFirst()) {
            o.setIdocupacion(c.getInt(c.getColumnIndex(KEY_ID)));
            o.setNombre(c.getString(c.getColumnIndex(KEY_OCUPACION_NOMBRE)));
            o.setDescripcion(c.getString(c.getColumnIndex(KEY_OCUPACION_DESCRIPCION)));
        }


        return o;
    }

    public int getOcupacion_id(String nombre) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_OCUPACION + " WHERE "
                + KEY_OCUPACION_NOMBRE+ " ='" + nombre+"'";

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);
        Ocupacion n = new Ocupacion();

        if (c.moveToFirst()) {
            n.setIdocupacion(c.getInt(c.getColumnIndex(KEY_ID)));
            n.setNombre(c.getString(c.getColumnIndex(KEY_NACIONALIDAD_NOMBRE)));
        }

        return n.getIdocupacion();
    }



    //GET INSTITUCION
    public Institucion getInstitucion(long institucion_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_INSTITUCION+ " WHERE "
                + KEY_ID + " ='" + institucion_id+"'";

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);
        Institucion i = new Institucion();

        if (c.moveToFirst()) {
            i.setIdinstitucion((c.getInt(c.getColumnIndex(KEY_ID))));
            i.setNombre(c.getString(c.getColumnIndex(KEY_INSTITUCION_NOMBRE)));
            /*i.setDescripcion(c.getString(c.getColumnIndex(KEY_INSTITUCION_DESCRIPCION)));
            i.setUrl(c.getString(c.getColumnIndex(KEY_INSTITUCION_URL)));
            i.setEmail(c.getString(c.getColumnIndex(KEY_INSTITUCION_EMAIL)));
            i.setCompetencia(c.getString(c.getColumnIndex(KEY_INSTITUCION_COMPETENCIA)));
            i.setRepresentante(c.getString(c.getColumnIndex(KEY_INSTITUCION_REPRESENTANTE)));
            i.setPublica(c.getString(c.getColumnIndex(KEY_INSTITUCION_PUBLICA)));
            i.setSectorid(c.getInt(c.getColumnIndex(KEY_SECTOR_ID)));*/
        }
        return i;
    }

    public int getInstitucion_id(String nombre) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_INSTITUCION+ " WHERE "
                + KEY_INSTITUCION_NOMBRE + " ='" + nombre+"'";

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);
        Institucion i = new Institucion();

        if (c.moveToFirst()) {
            i.setIdinstitucion((c.getInt(c.getColumnIndex(KEY_ID))));
            i.setNombre(c.getString(c.getColumnIndex(KEY_INSTITUCION_NOMBRE)));
            /*i.setDescripcion(c.getString(c.getColumnIndex(KEY_INSTITUCION_DESCRIPCION)));
            i.setUrl(c.getString(c.getColumnIndex(KEY_INSTITUCION_URL)));
            i.setEmail(c.getString(c.getColumnIndex(KEY_INSTITUCION_EMAIL)));
            i.setCompetencia(c.getString(c.getColumnIndex(KEY_INSTITUCION_COMPETENCIA)));
            i.setRepresentante(c.getString(c.getColumnIndex(KEY_INSTITUCION_REPRESENTANTE)));
            i.setPublica(c.getString(c.getColumnIndex(KEY_INSTITUCION_PUBLICA)));
            i.setSectorid(c.getInt(c.getColumnIndex(KEY_SECTOR_ID)));*/
        }
        return i.getIdinstitucion();
    }

    //

    //GET ESTADO CIVIL
    public Estadocivil getEstadocivil(long estadocivil_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_ESTADOCIVIL+ " WHERE "
                + KEY_ID + " = '" + estadocivil_id+"'";

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        Estadocivil e = new Estadocivil();

        if (c.moveToFirst()) {
            e.setIdestadocivil(c.getInt(c.getColumnIndex(KEY_ID)));
            e.setNombre(c.getString(c.getColumnIndex(KEY_ESTADOCIVIL_NOMBRE)));
        }

        return e;
    }

    public int getEstadocivil_id(String nombre) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_ESTADOCIVIL+ " WHERE "
                + KEY_ESTADOCIVIL_NOMBRE + " = '" + nombre+"'";

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        Estadocivil e = new Estadocivil();

        if (c.moveToFirst()) {
            e.setIdestadocivil(c.getInt(c.getColumnIndex(KEY_ID)));
            e.setNombre(c.getString(c.getColumnIndex(KEY_ESTADOCIVIL_NOMBRE)));
        }

        return e.getIdestadocivil();
    }
    //

    //GET NIVEL EDUCACION

    public Niveleducacion getNiveleducacion(long niveleducacion_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_NIVELEDUCACION+ " WHERE "
                + KEY_ID + " = '" + niveleducacion_id+"'";

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        Niveleducacion ne = new Niveleducacion();

        if (c.moveToFirst()){
            ne.setIdniveleducacion(c.getInt(c.getColumnIndex(KEY_ID)));
            ne.setNombre(c.getString(c.getColumnIndex(KEY_NIVELEDUCACION_NOMBRE)));
            ne.setDescripcion(c.getString(c.getColumnIndex(KEY_NIVELEDUCACION_DESCRIPCION)));
        }

        return ne;
    }

    public int getNiveleducacion_id(String nombre) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_NIVELEDUCACION+ " WHERE "
                + KEY_NIVELEDUCACION_NOMBRE + " = '" + nombre+"'";

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        Niveleducacion ne = new Niveleducacion();

        if (c.moveToFirst()){
            ne.setIdniveleducacion(c.getInt(c.getColumnIndex(KEY_ID)));
            ne.setNombre(c.getString(c.getColumnIndex(KEY_NIVELEDUCACION_NOMBRE)));
            ne.setDescripcion(c.getString(c.getColumnIndex(KEY_NIVELEDUCACION_DESCRIPCION)));
        }

        return ne.getIdniveleducacion();
    }
    //

    //GET RECLAMO
    public Reclamo getReclamo(long reclamo_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_RECLAMO+ " WHERE "
                + KEY_ID + " = " + reclamo_id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Reclamo r = new Reclamo();
        r.setIdreclamo((c.getInt(c.getColumnIndex(KEY_ID))));
        r.setNombresapellidosdenunciante((c.getString(c.getColumnIndex(KEY_RECLAMO_NOMBRESAPELLIDOSDENUNCIANTE))));
        r.setNumidenti((c.getString(c.getColumnIndex(KEY_RECLAMO_NUMIDENTI))));
        r.setDireccion((c.getString(c.getColumnIndex(KEY_RECLAMO_DIRECCION))));
        r.setEmail((c.getString(c.getColumnIndex(KEY_RECLAMO_EMAIL))));
        r.setNombresapellidosdenunciado((c.getString(c.getColumnIndex(KEY_RECLAMO_NOMBRESAPELLIDOSDENUNCIADO))));
        r.setTelefono((c.getString(c.getColumnIndex(KEY_RECLAMO_TELEFONO))));
        r.setCargo((c.getString(c.getColumnIndex(KEY_RECLAMO_CARGO))));
        r.setComparecer((c.getString(c.getColumnIndex(KEY_RECLAMO_COMPARECER))));
        r.setDocumentores((c.getString(c.getColumnIndex(KEY_RECLAMO_DOCUMENTORES))));
        r.setIdentidadreservada((c.getString(c.getColumnIndex(KEY_RECLAMO_IDENTIDADRESERVADA))));
        r.setResideextrangero((c.getString(c.getColumnIndex(KEY_RECLAMO_RESIDEEXTRANJERO))));
        r.setCiudaddeldenuncianteid((c.getInt(c.getColumnIndex(KEY_CIUDADDENUNCIANTE_ID))));
        r.setCiudaddeldenunciadoid((c.getInt(c.getColumnIndex(KEY_CIUDADDENUNCIADO_ID))));
        r.setInstitucionimplicadaid((c.getInt(c.getColumnIndex(KEY_RECLAMOINSTITUCIONIMPLICADA_ID))));
        r.setProvinciadenuncianteid((c.getInt(c.getColumnIndex(KEY_PROVINCIADENUNCIANTE_ID))));
        r.setProvinciadenunciadoid((c.getInt(c.getColumnIndex(KEY_PROVINCIADENUNCIADO_ID))));

        return r;
    }






    //---------------------- SELECT ALL ------------------------

    //SELECT ALL REGION
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

    public List<String> getAllProvinciasNombres() {
        List<String> tags = new ArrayList<String>();
        String selectQuery = "SELECT  * FROM " + TABLE_PROVINCIA;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                // adding to tags list
                tags.add(c.getString(c.getColumnIndex(KEY_PROVINCIA_NOMBRE)));
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

    public List<String> getAllCiudadesNombres_prov(long provinciaid) {
        List<String> tags = new ArrayList<String>();
        String selectQuery = "SELECT  * FROM " + TABLE_CIUDAD+ " where "+KEY_PROVINCIA_ID+"='"+provinciaid+"'";

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                // adding to tags list
                tags.add(c.getString(c.getColumnIndex(KEY_CIUDAD_NOMBRE)));
            } while (c.moveToNext());
        }
        return tags;
    }

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
   //             n.setS_img(c.getString(c.getColumnIndex(KEY_NOTICIA_URLIMAGEN)));
   //             n.setDia(c.getInt(c.getColumnIndex(KEY_NOTICIA_DIA)));
   //             n.setMes(c.getInt(c.getColumnIndex(KEY_NOTICIA_MES)));
//
   //             // adding to tags list
   //             tags.add(n);
   //         } while (c.moveToNext());
   //     }
   //     return tags;
   // }

    //SELECT ALL NACIONALIDAD
    public List<Nacionalidad> getAllNacionalidades() {
        List<Nacionalidad> tags = new ArrayList<Nacionalidad>();
        String selectQuery = "SELECT  * FROM " + TABLE_NACIONALIDAD;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Nacionalidad n = new Nacionalidad();
                n.setIdnacionalidad(c.getInt(c.getColumnIndex(KEY_ID)));
                n.setNombre(c.getString(c.getColumnIndex(KEY_NACIONALIDAD_NOMBRE)));

                // adding to tags list
                tags.add(n);
            } while (c.moveToNext());
        }
        return tags;
    }

    public List<String> getAllNacionalidadNombres() {
        List<String> tags = new ArrayList<String>();
        String selectQuery = "SELECT  * FROM " + TABLE_NACIONALIDAD;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                // adding to tags list
                tags.add(c.getString(c.getColumnIndex(KEY_NACIONALIDAD_NOMBRE)));
            } while (c.moveToNext());
        }
        return tags;
    }

    //SELECT ALL SECTOR
    public List<Sector> getAllSector() {
        List<Sector> todos = new ArrayList<Sector>();
        String selectQuery = "SELECT  * FROM " + TABLE_SECTOR;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Sector s = new Sector();
                s.setIdsector(c.getInt(c.getColumnIndex(KEY_ID)));
                s.setNombre(c.getString(c.getColumnIndex(KEY_SECTOR_NOMBRE)));
                s.setDescripcion(c.getString(c.getColumnIndex(KEY_SECTOR_DESCRIPCION)));
                s.setControl(c.getString(c.getColumnIndex(KEY_SECTOR_CONTROL)));
                s.setMensaje(c.getString(c.getColumnIndex(KEY_SECTOR_MENSAJE)));


                todos.add(s);
            } while (c.moveToNext());
        }

        return todos;
    }

    //SELECT ALL OCUPACION
    public List<Ocupacion> getAllOcupacion() {
        List<Ocupacion> todos = new ArrayList<Ocupacion>();
        String selectQuery = "SELECT  * FROM " + TABLE_OCUPACION;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Ocupacion o = new Ocupacion();
                o.setIdocupacion(c.getInt(c.getColumnIndex(KEY_ID)));
                o.setNombre(c.getString(c.getColumnIndex(KEY_OCUPACION_NOMBRE)));
                o.setDescripcion(c.getString(c.getColumnIndex(KEY_OCUPACION_DESCRIPCION)));

                todos.add(o);
            } while (c.moveToNext());
        }

        return todos;
    }

    public List<String> getAllOcupacionNombres() {
        List<String> todos = new ArrayList<String>();
        String selectQuery = "SELECT  * FROM " + TABLE_OCUPACION;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {

                todos.add(c.getString(c.getColumnIndex(KEY_OCUPACION_NOMBRE)));
            } while (c.moveToNext());
        }

        return todos;
    }

    //SELECT ALL INSTITUCION
    public List<Institucion> getAllInstitucion() {
        List<Institucion> todos = new ArrayList<Institucion>();
        String selectQuery = "SELECT  * FROM " + TABLE_INSTITUCION;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Institucion i = new Institucion();
                i.setIdinstitucion(c.getInt(c.getColumnIndex(KEY_ID)));
                i.setNombre(c.getString(c.getColumnIndex(KEY_INSTITUCION_NOMBRE)));
                i.setDescripcion(c.getString(c.getColumnIndex(KEY_INSTITUCION_DESCRIPCION)));
                i.setUrl(c.getString(c.getColumnIndex(KEY_INSTITUCION_URL)));
                i.setEmail(c.getString(c.getColumnIndex(KEY_INSTITUCION_EMAIL)));
                i.setCompetencia(c.getString(c.getColumnIndex(KEY_INSTITUCION_COMPETENCIA)));
                i.setRepresentante(c.getString(c.getColumnIndex(KEY_INSTITUCION_REPRESENTANTE)));
                i.setPublica(c.getString(c.getColumnIndex(KEY_INSTITUCION_PUBLICA)));
                i.setSectorid(c.getInt(c.getColumnIndex(KEY_SECTOR_ID)));

                todos.add(i);
            } while (c.moveToNext());
        }

        return todos;
    }

    public List<String> getAllInstitucionNombres() {
        List<String> todos = new ArrayList<String>();
        String selectQuery = "SELECT  * FROM " + TABLE_INSTITUCION;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                todos.add(c.getString(c.getColumnIndex(KEY_INSTITUCION_NOMBRE)));
            } while (c.moveToNext());
        }

        return todos;
    }

    //SELECT ALL ESTADOCIVIL
    public List<Estadocivil> getAllEstadocivil() {
        List<Estadocivil> todos = new ArrayList<Estadocivil>();
        String selectQuery = "SELECT  * FROM " + TABLE_ESTADOCIVIL;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Estadocivil e = new Estadocivil();
                e.setIdestadocivil(c.getInt(c.getColumnIndex(KEY_ID)));
                e.setNombre(c.getString(c.getColumnIndex(KEY_ESTADOCIVIL_NOMBRE)));

                todos.add(e);
            } while (c.moveToNext());
        }

        return todos;
    }

    //SELECT ALL ESTADOCIVIL nombres
    public List<String> getAllEstadocivilNombres() {
        List<String> todos = new ArrayList<String>();
        String selectQuery = "SELECT  * FROM " + TABLE_ESTADOCIVIL;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                todos.add(c.getString(c.getColumnIndex(KEY_ESTADOCIVIL_NOMBRE)));
            } while (c.moveToNext());
        }
        return todos;
    }

    //SELECT ALL NIVELEDUCACION
    public List<Niveleducacion> getAllNiveleducacion() {
        List<Niveleducacion> todos = new ArrayList<Niveleducacion>();
        String selectQuery = "SELECT  * FROM " + TABLE_NIVELEDUCACION;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Niveleducacion ne = new Niveleducacion();
                ne.setIdniveleducacion(c.getInt(c.getColumnIndex(KEY_ID)));
                ne.setNombre(c.getString(c.getColumnIndex(KEY_NIVELEDUCACION_NOMBRE)));
                ne.setDescripcion(c.getString(c.getColumnIndex(KEY_NIVELEDUCACION_DESCRIPCION)));

                todos.add(ne);
            } while (c.moveToNext());
        }

        return todos;
    }

    public List<String> getAllNiveleducacionNombres() {
        List<String> todos = new ArrayList<String>();
        String selectQuery = "SELECT  * FROM " + TABLE_NIVELEDUCACION;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                todos.add(c.getString(c.getColumnIndex(KEY_NIVELEDUCACION_NOMBRE)));
            } while (c.moveToNext());
        }

        return todos;
    }

    //SELECT ALL RECLAMO
    public List<Reclamo> getAllReclamo() {
        List<Reclamo> todos = new ArrayList<Reclamo>();
        String selectQuery = "SELECT  * FROM " + TABLE_RECLAMO;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Reclamo r = new Reclamo();
                r.setIdreclamo(c.getInt(c.getColumnIndex(KEY_ID)));
                r.setNombresapellidosdenunciante(c.getString(c.getColumnIndex(KEY_RECLAMO_NOMBRESAPELLIDOSDENUNCIANTE)));
                r.setNumidenti(c.getString(c.getColumnIndex(KEY_RECLAMO_NUMIDENTI)));
                r.setDireccion(c.getString(c.getColumnIndex(KEY_RECLAMO_DIRECCION)));
                r.setEmail(c.getString(c.getColumnIndex(KEY_RECLAMO_EMAIL)));
                r.setNombresapellidosdenunciado(c.getString(c.getColumnIndex(KEY_RECLAMO_NOMBRESAPELLIDOSDENUNCIADO)));
                r.setTelefono(c.getString(c.getColumnIndex(KEY_RECLAMO_TELEFONO)));
                r.setCargo(c.getString(c.getColumnIndex(KEY_RECLAMO_CARGO)));
                r.setComparecer(c.getString(c.getColumnIndex(KEY_RECLAMO_COMPARECER)));
                r.setDocumentores(c.getString(c.getColumnIndex(KEY_RECLAMO_DOCUMENTORES)));
                r.setIdentidadreservada(c.getString(c.getColumnIndex(KEY_RECLAMO_IDENTIDADRESERVADA)));
                r.setResideextrangero(c.getString(c.getColumnIndex(KEY_RECLAMO_RESIDEEXTRANJERO)));
                r.setCiudaddeldenuncianteid(c.getInt(c.getColumnIndex(KEY_CIUDADDENUNCIANTE_ID)));
                r.setCiudaddeldenunciadoid(c.getInt(c.getColumnIndex(KEY_CIUDADDENUNCIADO_ID)));
                r.setInstitucionimplicadaid(c.getInt(c.getColumnIndex(KEY_RECLAMOINSTITUCIONIMPLICADA_ID)));
                r.setProvinciadenuncianteid(c.getInt(c.getColumnIndex(KEY_PROVINCIADENUNCIANTE_ID)));
                r.setProvinciadenunciadoid(c.getInt(c.getColumnIndex(KEY_PROVINCIADENUNCIADO_ID)));


                todos.add(r);
            } while (c.moveToNext());
        }

        return todos;
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

    public void updateNoticia_Titulo(int id,String titulo){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NOTICIA_TITULO, titulo);
        db.update(TABLE_NOTICIA, values, KEY_ID+"='"+id+"'", null);
    }

    public void updateNacionalidad_Nombre(int id,String nombre){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NACIONALIDAD_NOMBRE, nombre);
        db.update(TABLE_NACIONALIDAD, values, KEY_ID+"='"+id+"'", null);
    }

    public void updateSector_Nombre(int id,String nombre){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_SECTOR_NOMBRE, nombre);
        db.update(TABLE_SECTOR, values, KEY_ID+"='"+id+"'", null);
    }

    public void updateOcupacion_Nombre(int id,String nombre){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_OCUPACION_NOMBRE, nombre);
        db.update(TABLE_OCUPACION, values, KEY_ID+"='"+id+"'", null);
    }

    public void updateInstitucion_Nombre(int id,String nombre){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_INSTITUCION_NOMBRE, nombre);
        db.update(TABLE_INSTITUCION, values, KEY_ID+"='"+id+"'", null);
    }

    public void updateEstadocivil_Nombre(int id,String nombre){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ESTADOCIVIL_NOMBRE, nombre);
        db.update(TABLE_ESTADOCIVIL, values, KEY_ID+"='"+id+"'", null);
    }

    public void updateNivelEducacion_Nombre(int id,String nombre){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NIVELEDUCACION_NOMBRE, nombre);
        db.update(TABLE_NIVELEDUCACION, values, KEY_ID+"='"+id+"'", null);
    }

    public void updateReclamo_Nombre(int id,String denunciante, String denunciado){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_RECLAMO_NOMBRESAPELLIDOSDENUNCIANTE, denunciante);
        values.put(KEY_RECLAMO_NOMBRESAPELLIDOSDENUNCIADO, denunciado);
        db.update(TABLE_RECLAMO, values, KEY_ID+"='"+id+"'", null);
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

    public boolean deleteNoticia(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NOTICIA, KEY_ID + "='" + id+"'", null) > 0;
    }

    public boolean deleteNacionalidad(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NACIONALIDAD, KEY_ID + "='" + id+"'", null) > 0;
    }

    public boolean deleteSector(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_SECTOR, KEY_ID + "='" + id+"'", null) > 0;
    }

    public boolean deleteOcupacion(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_OCUPACION, KEY_ID + "='" + id+"'", null) > 0;
    }

    public boolean deleteInstitucion(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_INSTITUCION, KEY_ID + "='" + id+"'", null) > 0;
    }

    public boolean deleteEstadocivil(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_ESTADOCIVIL, KEY_ID + "='" + id+"'", null) > 0;
    }

    public boolean deleteNiveleducacion(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NIVELEDUCACION, KEY_ID + "='" + id+"'", null) > 0;
    }

    public boolean deleteReclamo(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_RECLAMO, KEY_ID + "='" + id+"'", null) > 0;
    }

}