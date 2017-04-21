package swes.swes.activity;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ProgressBar;

import swes.swes.R;

public class SplashScreen extends Activity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;



    //private ProgressBar progressBar;

    ProgressBar mprogressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        mprogressBar = (ProgressBar) findViewById(R.id.progressBar);
        mprogressBar.setVisibility(View.VISIBLE);
        mprogressBar.getIndeterminateDrawable().setColorFilter(getResources()
                .getColor(R.color.dot_dark_screen3)
                , android.graphics.PorterDuff.Mode.MULTIPLY);

        ObjectAnimator anim = ObjectAnimator.ofInt(mprogressBar, "progress", 0, 100);
        anim.setDuration(3000);
        anim.setInterpolator(new DecelerateInterpolator());
        anim.start();
        new Handler().postDelayed(new Runnable() {



            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashScreen.this, SiginINorUP.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);

/*
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);*/
    }


}
