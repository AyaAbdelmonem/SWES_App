package swes.swes.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import swes.swes.Network.CheckNetwork;
import swes.swes.R;

public class SiginINorUP extends AppCompatActivity {

    private FirebaseAuth auth;

    Button button_SiginIn, button_sigin_UP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sigin_inor_up);
        auth = FirebaseAuth.getInstance();


      /*CheckNetwork checkNetwork =  CheckNetwork.getInstance();
        checkNetwork.setContext(getApplicationContext());
        boolean network= checkNetwork.isNetworkAvailable();
if (!network){
    Toast.makeText(this,"Check internet connection",Toast.LENGTH_SHORT).show();
}*/
        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(SiginINorUP.this, MainActivity.class));
            finish();
        }
        button_SiginIn = (Button) findViewById(R.id.button_SiginIn);
        button_sigin_UP= (Button) findViewById(R.id.button_SiginUP);

        button_SiginIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SiginINorUP.this,SiginInActivity.class);
                startActivity(intent);
                finish();
            }
        });
        button_sigin_UP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SiginINorUP.this,SiginUpActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}
