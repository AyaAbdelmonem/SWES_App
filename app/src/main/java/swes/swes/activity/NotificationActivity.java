package swes.swes.activity;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import swes.swes.R;

public class NotificationActivity extends AppCompatActivity {

    static int rt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        SharedPreferences sharedPreferences;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SettingsActivity.theme = sharedPreferences.getInt("theme", 0);

        rt = SettingsActivity.theme;

        if (SettingsActivity.theme == 1) {
            setTheme(R.style.DarkAppTheme);
        }
        setContentView(R.layout.activity_notification);


    }
}
