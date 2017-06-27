package swes.swes.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import swes.swes.R;
import swes.swes.classes.Student;

public class SiginUpActivity extends AppCompatActivity {


    private EditText inputEmail, inputPassword, inputName;
    private Button  btnSignUp  , signmein;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private static final String TAG = "SignUpActivity";
    private Student temp;
    Boolean value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout. activity_sigin_up);

        //Get Firebase instance
        database = FirebaseDatabase.getInstance();
              //Get Firebase users reference
                      myRef = database.getReference(getString(R.string.fb_users));
        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();
        temp = new Student();


        btnSignUp = (Button) findViewById(R.id.sign_up_button);
        signmein= (Button) findViewById(R.id.sign_me_in);
        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        inputName =(EditText) findViewById(R.id.username);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);


        signmein.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SiginUpActivity.this , SiginInActivity.class);
                startActivity(intent);
                finish();
            }
        });


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                //create user
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SiginUpActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(SiginUpActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Toast.makeText(SiginUpActivity.this, "Authentication failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                 //   startActivity(new Intent(SiginUpActivity.this, SiginInActivity.class));
                                  //  finish();
                                    temp.setName(inputName.getText().toString());
                                    temp.setEmail(email);
                                    myRef.child(auth.getCurrentUser().getUid().toString()).setValue(temp);

                                    sendVerificationEmail();

                                            // Read from the database
                                    myRef = myRef.child("students").child(auth.getCurrentUser().getUid().toString());
                                    myRef.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            // This method is called once with the initial value and again
                                            // whenever data at this location is updated.
                                            value = dataSnapshot.child("is_verified").getValue(Boolean.class);
                                            if( value ==  Boolean.TRUE  )


                                            {

                                                Intent intent = new Intent(SiginUpActivity.this, MainActivity.class);

                                                startActivity(intent);
                                                finish();
                                                Toast.makeText(getApplicationContext(), "Login Successfully", Toast.LENGTH_SHORT);
                                            }

                                            else
                                            {
                                                auth.signOut();
                                                Intent intent = new Intent(SiginUpActivity.this, activationWarning.class);
                                                startActivity(intent);
                                                finish();
                                            }
                                            Log.d(TAG, "Value is: " + value);
                                        }

                                        @Override
                                        public void onCancelled(DatabaseError error) {
                                            // Failed to read value
                                            Log.w(TAG, "Failed to read value.", error.toException());
                                        }
                                    });

                                    startActivity(new Intent(SiginUpActivity.this, SiginInActivity.class));
                                    finish();
                                }
                            }
                        });

            }
        });
    }

    public void sendVerificationEmail() {
        FirebaseUser user = auth.getCurrentUser();

        if (user != null) {
            user.sendEmailVerification()
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(SiginUpActivity.this, "Signup successful Verification email sent", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(SiginUpActivity.this, "verification failed !!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }
}