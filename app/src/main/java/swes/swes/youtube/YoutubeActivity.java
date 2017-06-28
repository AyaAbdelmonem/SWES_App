package swes.swes.youtube;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.PlayerStyle;
import com.google.android.youtube.player.YouTubePlayerView;

import swes.swes.R;

public class YoutubeActivity extends YouTubeBaseActivity implements
        YouTubePlayer.OnInitializedListener , YouTubePlayer.PlaybackEventListener{

    private static final int RECOVERY_DIALOG_REQUEST = 1;

    int backpressed=0;

    // YouTube player view
    private YouTubePlayerView youTubeView;
    String YOUTUBE_VIDEO_CODE;
    Boolean isLandscape;
    TextView status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_youtube);
        Intent intent = getIntent();
        String video = intent.getStringExtra("Lesson_video");
        String story = intent.getStringExtra("Lesson_story");
        status =(TextView)findViewById(R.id.tv_status);
        youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);

        youTubeView.setVisibility(View.INVISIBLE);
        status.setVisibility(View.INVISIBLE);
        if(video==null&&story==null){
            youTubeView.setVisibility(View.INVISIBLE);
            status.setVisibility(View.VISIBLE);
            status.setText("No story for this lesson");
        }
        else if(video!=null){
            status.setVisibility(View.INVISIBLE);
            youTubeView.setVisibility(View.VISIBLE);
            // Initializing video player with developer key
            youTubeView.initialize(Config.DEVELOPER_KEY, this);
            YOUTUBE_VIDEO_CODE = video;
        }
        else if(story!=null){
            status.setVisibility(View.INVISIBLE);
            youTubeView.setVisibility(View.VISIBLE);
            // Initializing video player with developer key
            youTubeView.initialize(Config.DEVELOPER_KEY, this);
            YOUTUBE_VIDEO_CODE = story;
        }







    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                        YouTubeInitializationResult errorReason) {
        if (errorReason.isUserRecoverableError()) {
            errorReason.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
        } else {
            String errorMessage = String.format(
                    getString(R.string.error_player), errorReason.toString());
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                        YouTubePlayer player, boolean wasRestored) {
        if (!wasRestored) {

            // loadVideo() will auto play video
            // Use cueVideo() method, if you don't want to play it automatically
            player.loadVideo(YOUTUBE_VIDEO_CODE);


            player.setPlayerStyle(PlayerStyle.DEFAULT);
            if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
               player.setFullscreen(true);
            }
            if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){

                player.setFullscreen(false);
            }

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RECOVERY_DIALOG_REQUEST) {
            // Retry initialization if user performed a recovery action
            getYouTubePlayerProvider().initialize(Config.DEVELOPER_KEY, this);
        }
    }

    private YouTubePlayer.Provider getYouTubePlayerProvider() {
        return (YouTubePlayerView) findViewById(R.id.youtube_view);
    }

    @Override
    public void onPlaying() {

    }

    @Override
    public void onPaused() {

    }

    @Override
    public void onStopped() {

    }

    @Override
    public void onBuffering(boolean b) {

    }

    @Override
    public void onSeekTo(int i) {

    }

    @Override
    public void onBackPressed() {
        backpressed++;

        Toast.makeText(getApplicationContext(),"Press back again to exit",Toast.LENGTH_LONG).show();
        if (backpressed>1)
        {super.onBackPressed();}
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                backpressed=0;
            }
        }, 2000);
    }

    @Override //reconfigure display properties on screen rotation
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        //Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT)
        {
              isLandscape=false;
        }
        else if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            isLandscape=true;
        }
    }
}