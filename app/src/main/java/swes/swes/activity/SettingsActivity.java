package swes.swes.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.Layout;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import swes.swes.R;

public class SettingsActivity extends AppCompatActivity {


   public static int theme;
    static int tom = 0;

    View layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        final CheckBox themed = (CheckBox) findViewById(R.id.dark_ed);
        final CardView card7 = (CardView) findViewById(R.id.c7);
        layout  = findViewById(R.id.actvity_settings);

        themed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                SharedPreferences sharedPreferences;
                sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = sharedPreferences.edit();

                if(themed.isChecked())
                {
                    editor.putInt("theme", 1);
                    theme=1;
                }
                else
                {
                    editor.putInt("theme", 0);
                    theme=0;
                }
                editor.commit();
                if(MainActivity.rt!=theme) {
                    tom = 1;
                    Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        });

        if (theme==1) {
            themed.setChecked(true);
            card7.setCardBackgroundColor(0xff424242);
            layout.setBackgroundColor(0xff424242);
        }
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(SettingsActivity.this,MainActivity.class);

        startActivity(intent);
    }
}
