package swes.swes.activity;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import swes.swes.Adapters.NotificationReyclerViewAdapter;
import swes.swes.Models.FAQ;
import swes.swes.Models.NotificationModel;
import swes.swes.R;
import swes.swes.SharedPref.PrefManager;

public class NotificationActivity extends AppCompatActivity {

    static int rt;
    static int theme;
    RecyclerView recyclerView;

    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        SharedPreferences sharedPreferences;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SettingsActivity.theme = sharedPreferences.getInt("theme", 0);




        rt = SettingsActivity.theme;

        if (SettingsActivity.theme == 1) {
            setTheme(R.style.DarkAppTheme);

        }

        setContentView(R.layout.activity_notification);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        recyclerView  = (RecyclerView)findViewById(R.id.notification_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                 fetchResults();
            }
        });
        swipeRefreshLayout.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        swipeRefreshLayout.setRefreshing(true);
                                        fetchResults();


                                    }
                                }
        );

        Gson gson =new Gson();

        ArrayList<NotificationModel> list = new ArrayList<NotificationModel>();
        PrefManager prefManager= new PrefManager(this) ;
        String json= prefManager.getNotificationList();
        list = gson.fromJson(json, new TypeToken<ArrayList<NotificationModel>>(){}.getType());

        for (NotificationModel n :list)
        Log.d("n111111111111",n.getDetails());


        RecyclerView.Adapter adapter = new NotificationReyclerViewAdapter(list);
        recyclerView.setAdapter(adapter);





        if (SettingsActivity.theme==1) {
            recyclerView.setBackgroundColor(getResources().getColor(R.color.grey_rec));
        }





    }
    void fetchResults(){

        Toast.makeText(NotificationActivity.this,"Refreshing",Toast.LENGTH_SHORT).show();
        swipeRefreshLayout.setRefreshing(false);

        Gson gson =new Gson();

        ArrayList<NotificationModel> list = new ArrayList<NotificationModel>();
        PrefManager prefManager= new PrefManager(this) ;
        String json= prefManager.getNotificationList();
        list = gson.fromJson(json, new TypeToken<ArrayList<NotificationModel>>(){}.getType());


        RecyclerView.Adapter adapter = new NotificationReyclerViewAdapter(list);
        recyclerView.setAdapter(adapter);

    }
}
