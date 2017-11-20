package espol.example.personal.comunitarias.Youtube;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.example.personal.comunitarias.R;

public class IntroVideos extends AppCompatActivity {

    private VideoReader reader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_videos);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        reader = new VideoReader(this);
        reader.execute();






    }

}