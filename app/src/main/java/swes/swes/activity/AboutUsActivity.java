package swes.swes.activity;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import swes.swes.R;

public class AboutUsActivity extends AppCompatActivity {


    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        //Back button

      //  ActionBar actionBar = getActionBar();

        // show the action bar title
        //actionBar.setDisplayShowTitleEnabled(true);

        //actionBar.setTitle("     "+getResources().getString(R.string.arabic)+"       ");
       // getSupportActionBar(). setDisplayShowTitleEnabled(true);
       // getSupportActionBar().setTitle("     "+getResources().getString(R.string.arabic)+"       ");

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(getResources().getString(R.string.arabic)+"                              ");
    }
}
