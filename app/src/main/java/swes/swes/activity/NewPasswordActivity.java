package swes.swes.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import swes.swes.R;

public class NewPasswordActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private EditText et_password;
    private String oldPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Enter new password");
        et_password=(EditText)findViewById(R.id.et_password);
        Intent intent = getIntent();
        oldPassword = intent.getStringExtra(getString(R.string.old_password));
    }
    public void resetPassword(View view){
        final FirebaseUser user;
        AuthCredential credential;

        if (!TextUtils.isEmpty(et_password.getText().toString())) {
         user = FirebaseAuth.getInstance().getCurrentUser();
         credential = EmailAuthProvider
                .getCredential(user.getEmail(),oldPassword);

// Prompt the user to re-provide their sign-in credentials
        user.reauthenticate(credential)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            user.updatePassword(et_password.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getApplicationContext(),getString(R.string.change_password_suc),Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(NewPasswordActivity.this,UserAccountActivity.class);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(getApplicationContext(),getString(R.string.invalid_password),Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        } else {
                            Log.d("old password class", "Error auth failed");
                            Toast.makeText(getApplicationContext(),getString(R.string.wrong_password),Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
        else et_password.setError("Enter a password");
    }

}
