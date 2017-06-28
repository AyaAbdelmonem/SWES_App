package swes.swes.activity;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import swes.swes.MyService;
import swes.swes.R;

import static android.content.ContentValues.TAG;
//import static com.google.android.gms.internal.zzt.TAG;

public class SplashScreen extends Activity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;


    Boolean value;
     private FirebaseAuth auth;
    private DatabaseReference mDatabase;
    //private ProgressBar progressBar;

    ProgressBar mprogressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        startService(new Intent(this, MyService.class));
        //Get Firebase auth instance
          auth = FirebaseAuth.getInstance();
          mDatabase = FirebaseDatabase.getInstance().getReference();

        mprogressBar = (ProgressBar) findViewById(R.id.progressBar);
        mprogressBar.setVisibility(View.VISIBLE);
        mprogressBar.getIndeterminateDrawable().setColorFilter(getResources()
                        .getColor(R.color.aya2)
                , android.graphics.PorterDuff.Mode.MULTIPLY);

        ObjectAnimator anim = ObjectAnimator.ofInt(mprogressBar, "progress", 0, 100);
        anim.setDuration(1000);
        anim.setInterpolator(new DecelerateInterpolator());
        anim.start();



     /*   String[] questions =getResources().getStringArray(R.array.faq_questions);
        String[] answers =getResources().getStringArray(R.array.faq_Answes);

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("FAQ");

        ArrayList<FAQ> list = new ArrayList<FAQ>();

        for (int i=0;i<questions.length;i++)
        {
            FAQ faq = new FAQ(questions[i],answers[i]);
            list.add(faq);
        }
      //  String userId = mDatabase.push().getKey();

        mDatabase.setValue(list);*/


        if ((auth.getCurrentUser() != null)) {
            mDatabase = mDatabase.child("students").child(auth.getCurrentUser().getUid().toString());
            mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    value = dataSnapshot.child("is_verified").getValue(Boolean.class);
                    if (value == Boolean.TRUE)


                    {

                        Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                        Toast.makeText(getApplicationContext(), "Login Successfully", Toast.LENGTH_SHORT);
                    } else {
                        Intent intent = new Intent(SplashScreen.this, activationWarning.class);
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
        } else {
            new Handler().postDelayed(new Runnable() {


                @Override
                public void run() {

                    // This method will be executed once the timer is over
                    // Start your app main activity
                    Intent i = new Intent(SplashScreen.this, SiginInActivity.class);
                    startActivity(i);
                    // close this activity
                    finish();
                }
            }, SPLASH_TIME_OUT);



        }
    }



}
