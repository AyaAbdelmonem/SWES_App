package swes.swes.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import swes.swes.R;
import swes.swes.classes.Student;

public class VerifyMeActivity extends AppCompatActivity {
    Student s ;
    ImageView verify ;
    private DatabaseReference mDatabase;
    private FirebaseAuth  auth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_me);

      //final   Student st = new Student();
        auth = FirebaseAuth.getInstance();

        mDatabase = FirebaseDatabase.getInstance().getReference();
        user = auth.getCurrentUser();
        verify = (ImageView) findViewById(R.id.verify_me_button);


        verify.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


            //updateData();
             startActivity(new Intent(VerifyMeActivity.this, MainActivity.class));
             finish();
            }


            }
       );

    }

   public void updateData (View view)
    {

        HashMap<String, Object> result = new HashMap<>();
        result.put("is_verified", true);
       mDatabase.child("students").child(auth.getCurrentUser().getUid().toString()).updateChildren(result);
        startActivity(new Intent(VerifyMeActivity.this, MainActivity.class));
            finish();

    }


}
