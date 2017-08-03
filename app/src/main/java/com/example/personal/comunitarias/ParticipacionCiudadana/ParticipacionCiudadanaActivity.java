package com.example.personal.comunitarias.ParticipacionCiudadana;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.personal.comunitarias.R;
import com.example.personal.comunitarias.Youtube.YoutubeVideos;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

public class ParticipacionCiudadanaActivity extends AppCompatActivity {

    Button button1,button2,button3,button4,button5,button6,button7;
    Button button_video1,button_video2,button_video3,button_video4,button_video5,button_video6,button_video7;
    LinearLayout panel1,panel2,panel3,panel4,panel5,panel6,panel7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participacion_ciudadana2);

        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        button4 = (Button)findViewById(R.id.button4);
        button5 = (Button)findViewById(R.id.button5);
        button6 = (Button)findViewById(R.id.button6);
        button7 = (Button)findViewById(R.id.button7);
        button1.setOnClickListener(buttonClickListener);
        button2.setOnClickListener(buttonClickListener);
        button3.setOnClickListener(buttonClickListener);
        button4.setOnClickListener(buttonClickListener);
        button5.setOnClickListener(buttonClickListener);
        button6.setOnClickListener(buttonClickListener);
        button7.setOnClickListener(buttonClickListener);

        button_video1 = (Button)findViewById(R.id.button_video1);
        button_video2 = (Button)findViewById(R.id.button_video2);
        button_video3 = (Button)findViewById(R.id.button_video3);
        button_video4 = (Button)findViewById(R.id.button_video4);
        button_video5 = (Button)findViewById(R.id.button_video5);
        button_video6 = (Button)findViewById(R.id.button_video6);
        button_video7 = (Button)findViewById(R.id.button_video7);

        button_video1.setOnClickListener(videoClickListener);
        button_video2.setOnClickListener(videoClickListener);
        button_video3.setOnClickListener(videoClickListener);
        button_video4.setOnClickListener(videoClickListener);
        button_video5.setOnClickListener(videoClickListener);
        button_video6.setOnClickListener(videoClickListener);
        button_video7.setOnClickListener(videoClickListener);

        panel1 = (LinearLayout)findViewById(R.id.panel1);
        panel2 = (LinearLayout)findViewById(R.id.panel2);
        panel3 = (LinearLayout)findViewById(R.id.panel3);
        panel4 = (LinearLayout)findViewById(R.id.panel4);
        panel5 = (LinearLayout)findViewById(R.id.panel5);
        panel6 = (LinearLayout)findViewById(R.id.panel6);
        panel7 = (LinearLayout)findViewById(R.id.panel7);

        /*We make both the panels, by default, invisible to user
        and make them appear on button click event in follwing manner*/

        panel1.setVisibility(View.GONE);
        panel2.setVisibility(View.GONE);
        panel3.setVisibility(View.GONE);
        panel4.setVisibility(View.GONE);
        panel5.setVisibility(View.GONE);
        panel6.setVisibility(View.GONE);
        panel7.setVisibility(View.GONE);
    }

    public View.OnClickListener buttonClickListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            Button ClickedButton = (Button)v;

            switch(ClickedButton.getId()) {
                case R.id.button1:
                    if(panel1.getVisibility()==View.GONE) {
                        panel1.setVisibility(View.VISIBLE);
                        button1.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.d_flecha_down), null, null, null);
                    }
                    else {
                        panel1.setVisibility(View.GONE);
                        button1.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.d_flecha),null,null,null);
                    }
                    panel2.setVisibility(View.GONE);
                    panel3.setVisibility(View.GONE);
                    panel4.setVisibility(View.GONE);
                    panel5.setVisibility(View.GONE);
                    panel6.setVisibility(View.GONE);
                    panel7.setVisibility(View.GONE);
                    break;
                case R.id.button2:
                    if(panel2.getVisibility()==View.GONE)
                        panel2.setVisibility(View.VISIBLE);
                    else
                        panel2.setVisibility(View.GONE);
                    panel1.setVisibility(View.GONE);
                    panel3.setVisibility(View.GONE);
                    panel4.setVisibility(View.GONE);
                    panel5.setVisibility(View.GONE);
                    panel6.setVisibility(View.GONE);
                    panel7.setVisibility(View.GONE);
                    break;
                case R.id.button3:
                    if(panel3.getVisibility()==View.GONE)
                        panel3.setVisibility(View.VISIBLE);
                    else
                        panel3.setVisibility(View.GONE);
                    panel1.setVisibility(View.GONE);
                    panel2.setVisibility(View.GONE);
                    panel4.setVisibility(View.GONE);
                    panel5.setVisibility(View.GONE);
                    panel6.setVisibility(View.GONE);
                    panel7.setVisibility(View.GONE);
                    break;
                case R.id.button4:
                    if(panel4.getVisibility()==View.GONE)
                        panel4.setVisibility(View.VISIBLE);
                    else
                        panel4.setVisibility(View.GONE);
                    panel1.setVisibility(View.GONE);
                    panel2.setVisibility(View.GONE);
                    panel3.setVisibility(View.GONE);
                    panel5.setVisibility(View.GONE);
                    panel6.setVisibility(View.GONE);
                    panel7.setVisibility(View.GONE);
                    break;
                case R.id.button5:
                    if(panel5.getVisibility()==View.GONE)
                        panel5.setVisibility(View.VISIBLE);
                    else
                        panel5.setVisibility(View.GONE);
                    panel1.setVisibility(View.GONE);
                    panel2.setVisibility(View.GONE);
                    panel3.setVisibility(View.GONE);
                    panel4.setVisibility(View.GONE);
                    panel6.setVisibility(View.GONE);
                    panel7.setVisibility(View.GONE);
                    break;
                case R.id.button6:
                    if(panel6.getVisibility()==View.GONE)
                        panel6.setVisibility(View.VISIBLE);
                    else
                        panel6.setVisibility(View.GONE);
                    panel1.setVisibility(View.GONE);
                    panel2.setVisibility(View.GONE);
                    panel3.setVisibility(View.GONE);
                    panel4.setVisibility(View.GONE);
                    panel5.setVisibility(View.GONE);
                    panel7.setVisibility(View.GONE);
                    break;
                case R.id.button7:
                    if(panel7.getVisibility()==View.GONE)
                        panel7.setVisibility(View.VISIBLE);
                    else
                        panel7.setVisibility(View.GONE);
                    panel1.setVisibility(View.GONE);
                    panel2.setVisibility(View.GONE);
                    panel3.setVisibility(View.GONE);
                    panel4.setVisibility(View.GONE);
                    panel5.setVisibility(View.GONE);
                    panel6.setVisibility(View.GONE);
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
