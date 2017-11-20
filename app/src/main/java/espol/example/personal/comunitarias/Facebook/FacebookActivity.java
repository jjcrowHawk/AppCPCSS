package espol.example.personal.comunitarias.Facebook;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.personal.comunitarias.R;


public class FacebookActivity extends AppCompatActivity {

    private ProgressDialog pd;
    private String embedFacebook;
    private WebView FacebookTimeline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_facebook);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //embebido
        embedFacebook = "<iframe src=\"https://www.facebook.com/plugins/page.php?href=https%3A%2F%2Fwww.facebook.com%2FParticipaEcuador&tabs=timeline&small_header=false&adapt_container_width=true&hide_cover=false&show_facepile=false&appId\" width=\"340\" height=\"500\" style=\"border:none;overflow:hidden\" scrolling=\"no\" frameborder=\"0\" allowTransparency=\"true\"></iframe>";

        //progrees
        pd = new ProgressDialog(this);
        pd.setMessage("Cargando...");
        pd.setCancelable(true);
        pd.show();

        //webview
        FacebookTimeline = (WebView) findViewById(R.id.mWebView);
        FacebookTimeline.setWebViewClient(new FacebookActivity.MyWebViewClient());
        WebSettings webSettings = FacebookTimeline.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);

        FacebookTimeline.loadDataWithBaseURL("https://facebook.com", embedFacebook, "text/html", "UTF-8", null);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(FacebookTimeline.canGoBack()) {
            FacebookTimeline.loadUrl(embedFacebook);
            FacebookTimeline.goBack();
            FacebookTimeline.goBack();
            FacebookTimeline.goBack();
        }
        else if(!FacebookTimeline.canGoBack()){
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