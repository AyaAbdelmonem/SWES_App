package swes.swes.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.widget.Toast;

import swes.swes.R;

public class CaseStudyActivity extends AppCompatActivity {

    WebView wv_casestudy;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case_study);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Case study");
        Intent intent = getIntent();
        String casestudy = intent.getStringExtra(getString(R.string.lesson_extra));
        wv_casestudy = (WebView) findViewById(R.id.wv_casestudy);
        if(casestudy != null) {
            wv_casestudy.loadUrl(casestudy);
        }
        else
        {Toast.makeText(this,"There's no case study in this level",Toast.LENGTH_SHORT).show();
        onBackPressed();}
    }
}
