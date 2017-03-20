package com.example.personal.comunitarias.DatabaseRemote;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Sianna-chan on 20/03/2017.
 */

public class Conexion {
    private String host = "babar.elephantsql.com";
    private String db = "omkbmadd";
    private int port = 5432;
    private String user = "omkbmadd";
    private String pass = "jK72m7jV5797WwXGbFX9CV0p86-gBU4x";
    private String url = "jdbc:postgresql://%s:%d/%s";
    private java.sql.Connection conn;

    public Conexion() throws ClassNotFoundException, SQLException {
        this.url = String.format(this.url,this.host, this.port, this.db);
        Class.forName("org.postgresql.Driver");
        this.conn = DriverManager.getConnection(this.url,this.user,this.pass);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }
}
