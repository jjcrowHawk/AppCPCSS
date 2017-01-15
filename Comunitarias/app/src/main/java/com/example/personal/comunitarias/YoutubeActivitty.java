package com.example.personal.comunitarias;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.ViewTarget;

public class YoutubeActivitty extends AppCompatActivity implements View.OnClickListener{

    private ShowcaseView showcaseView;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_videos);





        WebView myWebView = (WebView) findViewById(R.id.webview);
        pd = new ProgressDialog(YoutubeActivitty.this);
        pd.setMessage("Cargando...");
        pd.show();
        myWebView.setWebViewClient(new MyWebViewClient());
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.loadUrl("https://www.youtube.com/embed?listType=user_uploads&list=cpccsec");


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        showcaseView.hide();


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
                pd.dismiss();
                showcaseView = new ShowcaseView.Builder(YoutubeActivitty.this)
                        .setTarget(new ViewTarget(findViewById(R.id.textView)))
                        .setOnClickListener(YoutubeActivitty.this)
                        .build();


                showcaseView.setContentTitle("Ver más videos");
                showcaseView.setStyle(R.style.CustomShowcaseTheme2);
                showcaseView.setButtonText("OK");
            }

        }
    }
}
