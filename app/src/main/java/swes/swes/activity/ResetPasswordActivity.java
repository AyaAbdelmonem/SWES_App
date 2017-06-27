package swes.swes.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import swes.swes.R;

public class ResetPasswordActivity extends AppCompatActivity {
    private EditText inputResetEmail;
    private FirebaseAuth auth;
    String Reset_email;
    Button button_ok;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);


        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();
        inputResetEmail=(EditText) findViewById(R.id.reset_email);

        button_ok = (Button) findViewById(R.id.resend_forget_verification);


        button_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Reset_email = inputResetEmail.getText().toString().trim();
                if (TextUtils.isEmpty(Reset_email)) {
                    Toast.makeText(getApplication(), "Enter your registered email id", Toast.LENGTH_SHORT).show();
                    return;
                }

                auth.sendPasswordResetEmail(Reset_email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(ResetPasswordActivity.this, " Reset email sent ", Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    Toast.makeText(ResetPasswordActivity.this, " Failed ", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

    }
}
