package swes.swes.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import swes.swes.R;

public class SiginINorUP extends AppCompatActivity {

    Button button_SiginIn, button_sigin_UP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sigin_inor_up);
        button_SiginIn = (Button) findViewById(R.id.button_SiginIn);
        button_sigin_UP= (Button) findViewById(R.id.button_SiginUP);

        button_SiginIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SiginINorUP.this,SiginInActivity.class);
                startActivity(intent);
            }
        });
        button_sigin_UP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SiginINorUP.this,SiginUpActivity.class);
                startActivity(intent);
            }
        });

    }
}
