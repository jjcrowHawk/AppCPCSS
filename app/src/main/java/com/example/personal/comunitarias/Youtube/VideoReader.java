package com.example.personal.comunitarias.Youtube;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;


public class VideoReader extends AsyncTask<String, Void, String> {
    private Context context;
    private String TAG = VideoReader.class.getSimpleName();
    private ProgressDialog pDialog;
    public static ArrayList<VideoEntry> videos ;





    private static String url = "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&maxResults=50&playlistId=ULxiRaWL9fl54&key=AIzaSyAaNiwEqjm7Qk0gshB4beCowc1NmXzgx9g";

    VideoReader(Context ctx) {
        videos = new ArrayList<>();
        context = ctx;

    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();



    }

    @Override
    protected String doInBackground(String... arg0) {

        String jsonStr = makeServiceCall(url);
        Log.e(TAG, "Response from url: " + jsonStr);
        if (jsonStr != null) {
            try {

                JSONObject jsonObj = new JSONObject(jsonStr);
                JSONArray videos = jsonObj.getJSONArray("items");
                for (int i = 0; i < videos.length(); i++) {
                    JSONObject c = videos.getJSONObject(i);
                    JSONObject snippet = c.getJSONObject("snippet");
                    String titulo = snippet.getString("title");
                    String fecha = snippet.getString("publishedAt");
                    JSONObject resourceId = snippet.getJSONObject("resourceId");
                    String videoId = resourceId.getString("videoId");
                    this.videos.add(new VideoEntry(titulo,videoId,fecha));


                }
            } catch (final JSONException e) {
                Toast.makeText(context,"Falló la conexión a Internet", Toast.LENGTH_SHORT).show();
                ((IntroVideos)context).finish();


            }
        } else {
            Toast.makeText(context,"Falló la conexión a Internet", Toast.LENGTH_SHORT).show();
            ((IntroVideos)context).finish();


        }

        return null;

    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        if(!VideoReader.videos.isEmpty()){
            Intent i=new Intent(context,YoutubeVideos.class);
            (context).startActivity(i);
            ((IntroVideos)context).finish();
        }
        else{
            ((IntroVideos)context).finish();
        }







    }


    public String makeServiceCall(String reqUrl) {
        String response = null;
        try {
            URL url = new URL(reqUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            // read the response
            InputStream in = new BufferedInputStream(conn.getInputStream());
            response = convertStreamToString(in);
        } catch (MalformedURLException e) {
            Log.e(TAG, "MalformedURLException: " + e.getMessage());
        } catch (ProtocolException e) {
            Log.e(TAG, "ProtocolException: " + e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, "IOException: " + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }
        return response;
    }

    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

}
