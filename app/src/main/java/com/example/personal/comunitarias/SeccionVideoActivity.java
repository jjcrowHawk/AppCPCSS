package com.example.personal.comunitarias;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.personal.comunitarias.Facebook.FacebookActivity;

public class SeccionVideoActivity extends AppCompatActivity {

    private ProgressDialog pd;
    private String embedYoutube;
    private WebView wb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seccion_video);

        String link= getIntent().getStringExtra("LINK");
        System.out.println("THIS LINK_-----:"+link);
        //embebido
        String embedLink= link.replace("watch?v=","embed/");

        //progrees
        pd = new ProgressDialog(this);
        pd.setMessage("Cargando...");
        pd.setCancelable(true);
        pd.show();

        //webview
        wb = (WebView) findViewById(R.id.videoWebView);
        wb.setWebViewClient(new SeccionVideoActivity.MyWebViewClient());
        WebSettings webSettings = wb.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);

        embedYoutube = "<iframe width=\""+wb.getWidth()+"\" height=\""+wb.getHeight()+"\" src=\""+embedLink+"\" frameborder=\"0\" allowfullscreen></iframe>";

        //wb.loadDataWithBaseURL("https://youtube.com", embedYoutube, "text/html", "UTF-8", null);

        wb.loadUrl(link);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(wb.canGoBack()) {
            wb.loadUrl(embedYoutube);
            wb.goBack();
            wb.goBack();
            wb.goBack();
        }
        else if(!wb.canGoBack()){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }


    private class MyWebViewClient extends WebViewClient {


        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if(isOnlineNet()){
                view.loadUrl(url);

                if (!pd.isShowing()) {
                    pd.show();
                }
            }

            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            if (pd.isShowing()) {
                pd.cancel();

            }

        }
    }

    public Boolean isOnlineNet() {

        try {
            Process p = Runtime.getRuntime().exec("ping -c 1 www.google.es");

            int val = p.waitFor();
            boolean reachable = (val == 0);
            return reachable;

        } catch (Exception e) {

            e.printStackTrace();
        }
        return false;
    }



}
