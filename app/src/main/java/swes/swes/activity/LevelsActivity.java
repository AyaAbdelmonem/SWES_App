package swes.swes.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.GridView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import swes.swes.Adapters.HomeGridViewAdapter;
import swes.swes.R;
import swes.swes.classes.Level;

public class LevelsActivity extends AppCompatActivity {

   private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Course Levels");
         final GridView gridView = (GridView) findViewById(R.id.grid_view);


        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference myRef2 = myRef.child(getString(R.string.fb_levels_node));
        myRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Map<String,Level> levels =new HashMap<String, Level>();
                Level level= new Level();
                for(DataSnapshot temp :dataSnapshot.getChildren()){
                    level = temp.getValue(Level.class);
                    levels.put(temp.getKey(),level);
                    Log.d("testActivity", "Value is: " +"done");
                }

                // Instance of ImageAdapter Class
                List<Level> levelList =new ArrayList<Level>();
                for(Map.Entry<String,Level> entry : levels.entrySet()){
                    levelList.add(entry.getValue());
                    Log.d("Levels",entry.getKey() );

                }
                gridView.setAdapter(new HomeGridViewAdapter(LevelsActivity.this,levelList));
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("testActivity", "Failed to read value.", error.toException());
            }
        });


    }
}
