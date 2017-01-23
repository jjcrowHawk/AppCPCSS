package com.example.personal.comunitarias.tweets;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.personal.comunitarias.R;


public class TwitterActivity extends AppCompatActivity {

    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_tweets);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String embedTweet = "<a class=\"twitter-timeline\" href=\"https://twitter.com/CPCCS\"></a> <script async src=\"//platform.twitter.com/widgets.js\" charset=\"utf-8\"></script>";


        WebView displayTweeteTimeline = (WebView) findViewById(R.id.mWebView);
        pd = new ProgressDialog(this);
        pd.setMessage("Cargando...");
        pd.show();
        displayTweeteTimeline.setWebViewClient(new TwitterActivity.MyWebViewClient());
        WebSettings webSettings = displayTweeteTimeline.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        displayTweeteTimeline.loadDataWithBaseURL("https://twitter.com", embedTweet, "text/html", "UTF-8", null);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }


    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);

            if (!pd.isShowing()) {
                pd.show();
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



}