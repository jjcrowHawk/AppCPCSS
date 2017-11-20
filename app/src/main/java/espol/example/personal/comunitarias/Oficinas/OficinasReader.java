package espol.example.personal.comunitarias.Oficinas;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

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
import java.util.TreeMap;


public class OficinasReader extends AsyncTask<String, Void, String> {
    private Context context;
    private String TAG = OficinasReader.class.getSimpleName();
    private ProgressDialog pDialog;
    private TreeMap<String, Oficina> provincias;


    private static String url = "http://denunciaec.co.nf/oficinas.json";

    OficinasReader(Context ctx) {
        provincias = new TreeMap<>();
        context = ctx;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pDialog = new ProgressDialog(context);
        pDialog.setMessage("Cargando oficinas...");
        pDialog.setCancelable(false);
        pDialog.show();


    }

    @Override
    protected String doInBackground(String... arg0) {

        String jsonStr = makeServiceCall(url);
        Log.e(TAG, "Response from url: " + jsonStr);
        if (jsonStr != null) {
            try {

                JSONObject jsonObj = new JSONObject(jsonStr);
                JSONObject oficinas = jsonObj.getJSONObject("oficinas");
                JSONArray provincias = oficinas.getJSONArray("provincia");
                for (int i = 0; i < provincias.length(); i++) {
                    JSONObject provincia = provincias.getJSONObject(i);
                    String nombre = corrector(provincia.getString("nombre"));
                    String latitud = provincia.getString("latitud");
                    String longitud = provincia.getString("longitud");
                    String telefono = provincia.getString("telefono");
                    String ciudad = provincia.getString("ciudad");
                    String direccion = provincia.getString("direccion");
                    String twitter = provincia.getString("twitter");
                    LatLng ubicacion = new LatLng(Double.parseDouble(latitud), Double.parseDouble(longitud));
                    this.provincias.put(nombre, new Oficina(nombre, ciudad, telefono, ubicacion, direccion,"@"+twitter));

                }
            } catch (final JSONException e) {
                Log.e(TAG, "Json parsing error: " + e.getMessage());


            }
        } else {
            Log.e(TAG, "Couldn't get json from server.");

        }

        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
       // ((OficinasActivity) context).anadirMarcador(provincias);
        if (pDialog.isShowing()) {
            pDialog.cancel();
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

    private String corrector(String palabra) {
        if (palabra.equals("Canar")) return "Cañar";
        if (palabra.equals("Manabi")) return "Manabí";
        if (palabra.equals("Los Rios")) return "Los Ríos";
        if (palabra.equals("Bolivar")) return "Bolívar";
        if (palabra.equals("Galapagos")) return "Galápagos";
        else return palabra;

    }

    public TreeMap<String, Oficina> getProvincias() {
        return provincias;
    }

    public void setProvincias(TreeMap<String, Oficina> provincias) {
        this.provincias = provincias;
    }
}