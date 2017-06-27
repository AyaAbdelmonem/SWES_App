package swes.swes.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import swes.swes.R;

public class AboutUsActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;

    private Toolbar toolbar;
    TextView about_us_text;

    private static final String TAG = "AboutUsActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        about_us_text = (TextView) findViewById(R.id.about_us_paragraph);


        //Back button
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        // getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("About us");
     //   setTitle(getResources().getString(R.string.arabic)+"                              ");
//        ActionBar actionBar = getActionBar();
//
//        // show the action bar title
//        actionBar.setDisplayShowTitleEnabled(true);
//
//        actionBar.setTitle("     "+getResources().getString(R.string.arabic)+"       ");
//        getSupportActionBar(). setDisplayShowTitleEnabled(true);
    //   getSupportActionBar().setTitle("     "+getResources().getString(R.string.arabic)+"       ");

        // Read from the database

               mDatabase = FirebaseDatabase.getInstance().getReference();
        DatabaseReference ref = mDatabase.child("ِAboutus").child("paragraph");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String paragraph = dataSnapshot.getValue(String.class);
                about_us_text.setText(paragraph);
                Log.d(TAG, "aboutus: " + paragraph);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "Failed to read value.",databaseError.toException());
            }
        });


    }

}
