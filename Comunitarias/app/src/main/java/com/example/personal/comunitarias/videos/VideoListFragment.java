package com.example.personal.comunitarias.videos;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.ListFragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AbsListView;
import android.widget.ListView;

import com.example.personal.comunitarias.R;
import com.google.android.youtube.player.YouTubeIntents;

import java.util.Collections;
import java.util.List;

/**
 * Created by Palacios on 06/01/2017.
 */
public class VideoListFragment extends ListFragment{


    private static List<VideoEntry> VIDEO_LIST;


    private PageAdapter adapter;
    private View videoBox;
    private static final String CHANNEL_ID = "UCU_ZwZ5NQ9a19uhb6LD1dQw";
    private ProgressDialog pd;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        VIDEO_LIST = VideoReader.videos;
        Collections.reverse(VIDEO_LIST);
        adapter = new PageAdapter(getActivity(), VIDEO_LIST);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        getListView().setOnScrollListener(new AbsListView.OnScrollListener() {

              @Override
              public void onScrollStateChanged(final AbsListView absListView, int i) {
                  if (i == SCROLL_STATE_IDLE) {
                      if (getListView().getLastVisiblePosition() >= getListView().getCount() - 1 ) {
                          Snackbar.make(absListView, "Ver más videos en ", Snackbar.LENGTH_LONG)
                                  //.setActionTextColor(Color.CYAN)
                                  .setActionTextColor(getResources().getColor(R.color.red))
                                  .setAction("Youtube", new View.OnClickListener() {
                                      @Override
                                      public void onClick(View view) {
                                          String version = YouTubeIntents.getInstalledYouTubeVersionName(YoutubeVideos.getVista());
                                          if (version != null) {
                                              Intent intent = YouTubeIntents.createChannelIntent(YoutubeVideos.getVista(), CHANNEL_ID);
                                              intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                              YoutubeVideos.getVista().startActivity(intent);
                                          } else {
                                              youtubeWebView(YoutubeVideos.getVista());
                                          }
                                      }
                                  })
                                  .show();
                      }
                  }

              }

              @Override
              public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

              }
        });
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        setListAdapter(adapter);
        String videoId = VIDEO_LIST.get(0).videoId;
        VideoFragment videoFragment =  (VideoFragment) getFragmentManager().findFragmentById(R.id.video_fragment_container);
        videoFragment.setVideoId(videoId);



    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        String videoId = VIDEO_LIST.get(position).videoId;

        VideoFragment videoFragment =  (VideoFragment) getFragmentManager().findFragmentById(R.id.video_fragment_container);
        videoFragment.setVideoId(videoId);



    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        adapter.releaseLoaders();
    }

    public void setLabelVisibility(boolean visible) {
        adapter.setLabelVisibility(visible);
    }

    public void youtubeWebView(Context c) {

        pd = new ProgressDialog(c);
        pd.setMessage("Cargando...");
        WebView myWebView = new WebView(c);

        myWebView.setWebViewClient(new MyWebViewClient());
        myWebView.loadUrl("https://www.youtube.com/user/CPCCSEc/videos");


        new AlertDialog.Builder(c).setView(myWebView)
                .setTitle("Videos")
                .setPositiveButton("Volver", new DialogInterface.OnClickListener() {
                    @TargetApi(11)
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).show();
        pd.show();
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

            }

        }
    }






}