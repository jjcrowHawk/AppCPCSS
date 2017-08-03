package com.example.personal.comunitarias.Transparencia2;


import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.personal.comunitarias.R;
import com.example.personal.comunitarias.Youtube.YoutubeVideos;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

public class Transparencia2Activity extends AppCompatActivity {

    Button button1,button2,button3,button4;
    Button button_video1,button_video2,button_video3,button_video4;
    LinearLayout panel1,panel2,panel3,panel4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transparencia2);

        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        button4 = (Button)findViewById(R.id.button4);

        button1.setOnClickListener(buttonClickListener);
        button2.setOnClickListener(buttonClickListener);
        button3.setOnClickListener(buttonClickListener);
        button4.setOnClickListener(buttonClickListener);

        button_video1 = (Button)findViewById(R.id.button_video1);
        button_video2 = (Button)findViewById(R.id.button_video2);
        button_video3 = (Button)findViewById(R.id.button_video3);
        button_video4 = (Button)findViewById(R.id.button_video4);


        button_video1.setOnClickListener(videoClickListener);
        button_video2.setOnClickListener(videoClickListener);
        button_video3.setOnClickListener(videoClickListener);
        button_video4.setOnClickListener(videoClickListener);


        panel1 = (LinearLayout)findViewById(R.id.panel1);
        panel2 = (LinearLayout)findViewById(R.id.panel2);
        panel3 = (LinearLayout)findViewById(R.id.panel3);
        panel4 = (LinearLayout)findViewById(R.id.panel4);


        /*We make both the panels, by default, invisible to user
        and make them appear on button click event in follwing manner*/

        panel1.setVisibility(View.GONE);
        panel2.setVisibility(View.GONE);
        panel3.setVisibility(View.GONE);
        panel4.setVisibility(View.GONE);

    }

    public View.OnClickListener buttonClickListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            Button ClickedButton = (Button)v;

            switch(ClickedButton.getId()) {
                case R.id.button1:
                    if(panel1.getVisibility()==View.GONE)
                        panel1.setVisibility(View.VISIBLE);
                    else
                        panel1.setVisibility(View.GONE);
                    panel2.setVisibility(View.GONE);
                    panel3.setVisibility(View.GONE);
                    panel4.setVisibility(View.GONE);

                    break;
                case R.id.button2:
                    if(panel2.getVisibility()==View.GONE)
                        panel2.setVisibility(View.VISIBLE);
                    else
                        panel2.setVisibility(View.GONE);
                    panel1.setVisibility(View.GONE);
                    panel3.setVisibility(View.GONE);
                    panel4.setVisibility(View.GONE);

                    break;
                case R.id.button3:
                    if(panel3.getVisibility()==View.GONE)
                        panel3.setVisibility(View.VISIBLE);
                    else
                        panel3.setVisibility(View.GONE);
                    panel1.setVisibility(View.GONE);
                    panel2.setVisibility(View.GONE);
                    panel4.setVisibility(View.GONE);

                    break;
                case R.id.button4:
                    if(panel4.getVisibility()==View.GONE)
                        panel4.setVisibility(View.VISIBLE);
                    else
                        panel4.setVisibility(View.GONE);
                    panel1.setVisibility(View.GONE);
                    panel2.setVisibility(View.GONE);
                    panel3.setVisibility(View.GONE);

                    break;
            }
        }
    };

    public View.OnClickListener videoClickListener = new View.OnClickListener(){

        private YouTubePlayerSupportFragment mPlayerFragment;
        private YouTubePlayer YPlayer;
        private final String YoutubeDeveloperKey = YoutubeVideos.DEVELOPER_KEY;

        @Override
        public void onClick(View view) {
            int oldOrientation=getRequestedOrientation();
            System.out.println("LISTENED ACTION!!!");
            final YouTubePlayerSupportFragment youTubePlayerFragment = YouTubePlayerSupportFragment.newInstance();
            final android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.youtube_fragment, youTubePlayerFragment).commit();

            youTubePlayerFragment.initialize(YoutubeDeveloperKey, new YouTubePlayer.OnInitializedListener() {

                @Override
                public void onInitializationSuccess(YouTubePlayer.Provider arg0, YouTubePlayer youTubePlayer, boolean b) {
                    if (!b) {
                        YPlayer = youTubePlayer;
                        YPlayer.setFullscreenControlFlags(YPlayer.FULLSCREEN_FLAG_CONTROL_ORIENTATION|YPlayer.FULLSCREEN_FLAG_ALWAYS_FULLSCREEN_IN_LANDSCAPE);
                        YPlayer.setOnFullscreenListener(new YouTubePlayer.OnFullscreenListener() {
                            @Override
                            public void onFullscreen(boolean b) {
                                if (!b){
                                    YPlayer.pause();
                                    YPlayer.release();
                                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                                }
                                else{
                                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                                }
                            }
                        });
                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                        YPlayer.setFullscreen(true);
                        YPlayer.loadVideo("5Umo_kMp8Ak");
                        YPlayer.play();
                    }
                }

                @Override
                public void onInitializationFailure(YouTubePlayer.Provider arg0, YouTubeInitializationResult arg1) {


                }
            });
        }
    };
}
