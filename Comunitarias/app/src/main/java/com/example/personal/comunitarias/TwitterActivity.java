package com.example.personal.comunitarias;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class TwitterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_tweets);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String embedTweet = "<a class=\"twitter-timeline\" href=\"https://twitter.com/CPCCS\"></a> <script async src=\"//platform.twitter.com/widgets.js\" charset=\"utf-8\"></script>";


        WebView displayTweeteTimeline = (WebView) findViewById(R.id.mWebView);
        displayTweeteTimeline.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
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



}