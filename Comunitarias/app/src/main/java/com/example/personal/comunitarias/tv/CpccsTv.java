package com.example.personal.comunitarias.tv;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.personal.comunitarias.R;

public class CpccsTv extends AppCompatActivity {

    private ProgressDialog pd;
    private String myurl;
    private  WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpccs_tv);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        myurl = "https://livestream.com/accounts/1785250/events/2708656/player?width=560&height=315&autoPlay=true&mute=false";



        myWebView = (WebView) findViewById(R.id.mWebView);
        pd = new ProgressDialog(this);
        pd.setMessage("Cargando...");
        pd.show();
        myWebView.setWebViewClient(new MyWebViewClient());
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.loadUrl(myurl);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        myWebView.destroy();
        finish();

        return super.onOptionsItemSelected(item);
    }


    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if(url.contains(myurl)){
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
                pd.dismiss();

            }

        }
    }




}
