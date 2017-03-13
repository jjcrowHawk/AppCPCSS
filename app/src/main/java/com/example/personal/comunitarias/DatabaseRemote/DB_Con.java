package com.example.personal.comunitarias.DatabaseRemote;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

/**
 * Created by Sianna-chan on 12/03/2017.
 */

public class DB_Con extends _Default{
    private Connection conn;
    private String host = "babar.elephantsql.com";
    private String db = "omkbmadd";
    private int port = 5432;
    private String user = "omkbmadd";
    private String pass = "jK72m7jV5797WwXGbFX9CV0p86-gBU4x";
    private String url = "jdbc:postgresql://%s:%d/%s";

    public DB_Con() {
        this.url = String.format(this.url,this.host, this.port, this.db);
        conecta();

    }

    public void conecta(){
        try{
            Class.forName("org.postgresql.Driver");
            this.conn = DriverManager.getConnection(this.url,this.user,this.pass);
        }catch (Exception e){
            this._mensagem = e.getMessage();
            this._status = false;
        }
        //return this.conn;
    }

    public ResultSet select(String query){
        this.conecta();
        /*ResultSet resultSet = null;
        try {
            resultSet = new ExecuteDB(this.conn, query).execute().get();
        }catch (Exception e){
            this._status = false;
            this._mensagem = e.getMessage();
        }
        return resultSet;*/

        ResultSet resultSet = null;
        try{
            resultSet = conn.prepareStatement(query).executeQuery();
        }catch (Exception e){

        }finally {
            try {
                conn.close();
            }catch (Exception ex){

            }
        }
        return resultSet;
    }

    public ResultSet execute(String query){
        this.conecta();
        /*ResultSet resultSet = null;
        try {
            resultSet = new ExecuteDB(this.conn, query).execute().get();
        }catch (Exception e){
            this._status = false;
            this._mensagem = e.getMessage();
        }
        return resultSet;*/

        ResultSet resultSet = null;
        try{
            resultSet = conn.prepareStatement(query).executeQuery();
        }catch (Exception e){

        }finally {
            try {
                conn.close();
            }catch (Exception ex){

            }
        }
        return resultSet;
    }
}
