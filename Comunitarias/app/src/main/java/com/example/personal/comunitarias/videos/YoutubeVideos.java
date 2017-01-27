package com.example.personal.comunitarias.videos;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Toast;
import com.example.personal.comunitarias.R;
import com.google.android.youtube.player.YouTubeApiServiceUtil;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer.OnFullscreenListener;


public class YoutubeVideos extends AppCompatActivity implements OnFullscreenListener {


    private static final int RECOVERY_DIALOG_REQUEST = 1;
    private VideoListFragment listFragment;
    private static Context context;
    private View listBox;
    private boolean isFullscreen;
    public static  String DEVELOPER_KEY = "AIzaSyAaNiwEqjm7Qk0gshB4beCowc1NmXzgx9g";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_videos);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        context = this.getBaseContext();
        listFragment = (VideoListFragment) getFragmentManager().findFragmentById(R.id.list_fragment);
        listBox  = findViewById(R.id.list_box);
        layout();
        checkYouTubeApi();
    }

    private void checkYouTubeApi() {
        YouTubeInitializationResult errorReason =
                YouTubeApiServiceUtil.isYouTubeApiServiceAvailable(this);
        if (errorReason.isUserRecoverableError()) {
            errorReason.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
        } else if (errorReason != YouTubeInitializationResult.SUCCESS) {
            String errorMessage =
                    String.format(getString(R.string.error_player), errorReason.toString());
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RECOVERY_DIALOG_REQUEST) {
            recreate();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        layout();
    }

    @Override
    public void onFullscreen(boolean isFullscreen) {
        this.isFullscreen = isFullscreen;
        layout();
    }

    private void layout() {
        boolean isPortrait = getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
        listFragment.getView().setVisibility(isFullscreen ? View.GONE : View.VISIBLE);
        listFragment.setLabelVisibility(isPortrait);

        if (isFullscreen) {
            listBox.setVisibility(View.GONE);
           // ((LinearLayout) findViewById(R.id.contenedor)).setOrientation(LinearLayout.HORIZONTAL);
        } else if (isPortrait) {
           // ((LinearLayout) findViewById(R.id.contenedor)).setOrientation(LinearLayout.VERTICAL);
           // setLayoutSize(listFragment.getView(),MATCH_PARENT, MATCH_PARENT);



        } else {
            listBox.setVisibility(View.GONE);

        }



    }

    private static void setLayoutSize(View view, int width, int height) {
        LayoutParams params = view.getLayoutParams();
        params.width = width;
        params.height = height;
        view.setLayoutParams(params);
    }

    public static Context getContext(){

        return  context;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }


}
