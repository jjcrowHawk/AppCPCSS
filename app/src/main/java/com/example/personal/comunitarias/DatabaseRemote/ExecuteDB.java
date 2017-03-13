package com.example.personal.comunitarias.DatabaseRemote;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.example.personal.comunitarias.Denuncias.Peticionario;
import com.example.personal.comunitarias.Menu;

import java.sql.Connection;
import java.sql.ResultSet;

/**
 * Created by sianna-chan on 21/02/2017.
 */
public class ExecuteDB extends AsyncTask<String,Void,ResultSet> {

    private Connection connection;
    private String query;
    ProgressDialog mProgressDialog;

    public ExecuteDB(Connection connection, String query) {
        this.connection = connection;
        this.query = query;
    }

    /*
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mProgressDialog = new ProgressDialog(new Menu());
        mProgressDialog.setMessage("Estableciendo conección...");
        //mProgressDialog.setIndeterminate(false);
        mProgressDialog.show();
    }

    @Override
    protected void onPostExecute(ResultSet resultSet) {
        super.onPostExecute(resultSet);
        mProgressDialog.dismiss();
        mProgressDialog.cancel();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
        //mProgressDialog.show();
    }

    @Override
    protected void onCancelled(ResultSet resultSet) {
        super.onCancelled(resultSet);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }*/


    @Override
    protected ResultSet doInBackground(String... params) {
        ResultSet resultSet = null;
        try {
            resultSet = connection.prepareStatement(query).executeQuery();
        } catch (Exception e) {

        } finally {
            try {
                connection.close();
            } catch (Exception ex) {

            }
        }
        return resultSet;
    }

}
