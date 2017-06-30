package swes.swes.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import swes.swes.R;

public class activationWarning extends AppCompatActivity {
    ImageButton reactivate_button ;
    private FirebaseAuth auth;
    private Toolbar toolbar;
    ImageButton logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activation_warning);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        logout = (ImageButton)findViewById(R.id.logout_icon);

        // getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Activation Warning ");
        reactivate_button = (ImageButton)findViewById(R.id.resend_verification);
        auth = FirebaseAuth.getInstance();
        reactivate_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user = auth.getCurrentUser();

                if (user != null) {
                    user.sendEmailVerification()
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(activationWarning.this, "Signup successful Verification email sent", Toast.LENGTH_SHORT).show();
                                    }
                                    else
                                    {
                                        Toast.makeText(activationWarning.this, "verification failed !!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               auth.signOut();
                startActivity(new Intent(activationWarning.this, SiginInActivity.class));
                finish();
            }
        });


    }

    @Override
    public void onBackPressed() {
       // super.onBackPressed();
        auth.signOut();
        Intent intent = new Intent(activationWarning.this, SiginInActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
