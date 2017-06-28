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

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import swes.swes.PostsFeature.User;
import swes.swes.R;
import swes.swes.classes.Student;

public class SiginInActivity extends AppCompatActivity  implements GoogleApiClient.OnConnectionFailedListener,
        View.OnClickListener {


    private EditText inputEmail, inputPassword , inputResetEmail;

    private FirebaseAuth auth;
    private SignInButton mSignInButton;
    private GoogleApiClient mGoogleApiClient;
    private FirebaseAuth mFirebaseAuth;
    GoogleApiClient googleApiClient;
    Boolean value;

    private FirebaseAuth.AuthStateListener mAuthListener;
    private ProgressBar progressBar;
    private Button  btnLogin, btnReset , signmeup;
    private static final String TAG = "SignInActivity";
    private static final int RC_SIGN_IN = 9001;
    String Reset_email;

    private DatabaseReference mDatabase;
    private Student s ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();
        s = new Student();

        if (auth.getCurrentUser() != null) {
             startActivity(new Intent(SiginInActivity.this, MainActivity.class));
             finish();
        }

        // set the view now
        setContentView(R.layout.activity_sigin_in);

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnReset = (Button) findViewById(R.id.btn_reset_password);
        signmeup = (Button) findViewById(R.id.sign_me_up);


        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        mDatabase = FirebaseDatabase.getInstance().getReference();

       signmeup.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent intent = new Intent(SiginInActivity.this , SiginUpActivity.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                    });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                startActivity(new Intent(SiginInActivity.this, ResetPasswordActivity.class));
            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inputEmail.getText().toString();
                final String password = inputPassword.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                   //authenticate user
                   auth.signInWithEmailAndPassword(email, password)
                           .addOnCompleteListener(SiginInActivity.this, new OnCompleteListener<AuthResult>() {
                               @Override
                               public void onComplete(@NonNull final Task<AuthResult> task) {
                                   // If sign in fails, display a message to the user. If sign in succeeds
                                   // the auth state listener will be notified and logic to handle the
                                   // signed in user can be handled in the listener.
                                   progressBar.setVisibility(View.GONE);
                                   if (!task.isSuccessful()) {
                                       // there was an error
                                       if (password.length() < 6) {
                                           inputPassword.setError("Must be More than 6");
                                       } else {
                                           Toast.makeText(SiginInActivity.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                                       }
                                   } else {
                                       // Read from the database
                                       mDatabase = mDatabase.child("students").child(auth.getCurrentUser().getUid().toString());
                                       mDatabase.addValueEventListener(new ValueEventListener() {
                                           @Override
                                           public void onDataChange(DataSnapshot dataSnapshot) {
                                               // This method is called once with the initial value and again
                                               // whenever data at this location is updated.
                                                value = dataSnapshot.child("is_verified").getValue(Boolean.class);
                                               if( value ==  Boolean.TRUE  )


                                               {
                                                   onAuthSuccess(task.getResult().getUser());
                                                   Intent intent = new Intent(SiginInActivity.this, MainActivity.class);



                                                   startActivity(intent);
                                                   finish();
                                                   Toast.makeText(getApplicationContext(), "Login Successfully", Toast.LENGTH_SHORT);
                                               }

                                               else
                                               {
                                                   Intent intent = new Intent(SiginInActivity.this, activationWarning.class);
                                                   //     Log.d(TAG, mDatabase.child("students").child(auth.getCurrentUser().getUid().toString()).child("is_verified").tgetoString());
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


                                   }
                               }
                           });


            }

        });

                                                /*    Login using google account */

        // Assign fields
        mSignInButton = (SignInButton) findViewById(R.id.sign_in_button);
        // Set click listeners
        mSignInButton.setOnClickListener(this);
        // Initialize FirebaseAuth
        mFirebaseAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                signIn();
                break;
            default:
                return;
        }
    }
    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            } else {
                // Google Sign In failed
                Log.e(TAG, "Google Sign In failed.");
                String error =  result.getStatus().getStatusMessage();
                Log.e(TAG , "error " + error);
            }
        }
    }
    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mFirebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithCredential", task.getException());
                            Toast.makeText(SiginInActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            startActivity(new Intent(SiginInActivity.this, MainActivity.class));
                            finish();
                        }
                    }
                });
    }
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
        Toast.makeText(this, "Google Play Services error.", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                //  .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        mGoogleApiClient.connect();
        super.onStart();
    }




    private void onAuthSuccess(FirebaseUser user) {
        String username = user.getEmail();

        // Write new user
        writeNewUser(user.getUid(), username, user.getEmail());

        // Go to MainActivity

    }
    private void writeNewUser(String userId, String name, String email) {
        User user = new User(name, email);

        mDatabase.child("users").child(userId).setValue(user);
    }



}