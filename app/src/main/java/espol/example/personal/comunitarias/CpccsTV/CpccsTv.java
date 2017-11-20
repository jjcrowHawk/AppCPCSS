package espol.example.personal.comunitarias.CpccsTV;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
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

        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // url de tranmision de livestream de la cuenta del cpcs
        myurl = "https://livestream.com/accounts/1785250/events/2708656/player?width=560&height=315&autoPlay=true&mute=false";

        // progress
        pd = new ProgressDialog(this);
        pd.setMessage("Cargando...");
        pd.setCancelable(false);
        pd.show();

        //webview
        myWebView = (WebView) findViewById(R.id.mWebView);
        myWebView.setWebViewClient(new MyWebViewClient());
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.loadUrl(myurl);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(myWebView.canGoBack()) {
            myWebView.goBack();
        }
        else if(!myWebView.canGoBack()){
            myWebView.destroy();
            finish();
        }



        return super.onOptionsItemSelected(item);
    }

    //clase complementaria para el webview
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
                pd.cancel();

            }

        }
    }




}
