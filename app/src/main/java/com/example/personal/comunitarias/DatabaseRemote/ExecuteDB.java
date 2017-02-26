package com.example.personal.comunitarias.DatabaseRemote;

import android.os.AsyncTask;

import java.sql.Connection;
import java.sql.ResultSet;

/**
 * Created by sianna-chan on 21/02/2017.
 */
public class ExecuteDB extends AsyncTask<String,Void,ResultSet> {

    private Connection connection;
    private String query;

    public ExecuteDB(Connection connection, String query) {
        this.connection = connection;
        this.query = query;
    }

    @Override
    protected ResultSet doInBackground(String... params) {
        ResultSet resultSet = null;
        try{
            resultSet = connection.prepareStatement(query).executeQuery();
        }catch (Exception e){

        }finally {
            try {
                connection.close();
            }catch (Exception ex){

            }
        }
        return resultSet;
    }
}
