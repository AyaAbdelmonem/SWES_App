package swes.swes.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import swes.swes.R;

public class CaseStudyActivity extends AppCompatActivity {

    WebView wv_casestudy;
    private Toolbar toolbar;
    private ProgressDialog progressBar;

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
        final String casestudy = intent.getStringExtra(getString(R.string.lesson_extra));
        wv_casestudy = (WebView) findViewById(R.id.wv_casestudy);
        wv_casestudy.getSettings().setJavaScriptEnabled(true);
        wv_casestudy.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();

        progressBar = ProgressDialog.show(CaseStudyActivity.this, "Loading casestudy", "Loading...");

        wv_casestudy.setWebViewClient(new WebViewClient() {


            public void onPageFinished(WebView view, String url) {
                if (progressBar.isShowing()) {
                    progressBar.dismiss();
                }
            }


        });

        if(casestudy != null) {
            wv_casestudy.loadUrl(casestudy);
        }
        else
        {Toast.makeText(this,"There's no case study in this level",Toast.LENGTH_SHORT).show();
        onBackPressed();}
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (wv_casestudy.canGoBack()) {
                        wv_casestudy.goBack();
                    } else {
                        finish();
                    }
                    return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }
}
