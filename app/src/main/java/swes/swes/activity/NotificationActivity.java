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


       // CardView notiification_card =(CardView) findViewById(R.id.notiification_card);

       // View layout=  findViewById(R.id.activity_notification);

        rt = SettingsActivity.theme;

        if (SettingsActivity.theme == 1) {
            setTheme(R.style.DarkAppTheme);
         //   recyclerView_n.setBackgroundColor(0xff424242);
           //layout.setBackgroundColor(0xff424242);
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


        RecyclerView.Adapter adapter = new NotificationReyclerViewAdapter(list);
        recyclerView.setAdapter(adapter);



//        NotificationModel model1 =new NotificationModel("First Notification","Here's Notifaction in Details");
//        NotificationModel model2 =new NotificationModel("Second Notification","Here's Notifaction in Details");
//        NotificationModel model3 =new NotificationModel("Third Notification","Here's Notifaction in Details");
//
//        notifications.add(model1);
//        notifications.add(model2);
//        notifications.add(model3);

        if (SettingsActivity.theme==1) {
            recyclerView.setBackgroundColor(getResources().getColor(R.color.grey_rec));
        }

//        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
//            GestureDetector gestureDetector = new GestureDetector(getApplicationContext(), new GestureDetector.SimpleOnGestureListener() {
//
//                @Override public boolean onSingleTapUp(MotionEvent e) {
//                    return true;
//                }
//
//            });
//            @Override
//            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
//
//                View child = rv.findChildViewUnder(e.getX(), e.getY());
//                if(child != null && gestureDetector.onTouchEvent(e)) {
//                    int position = rv.getChildAdapterPosition(child);
//
//                    Toast.makeText(getApplicationContext(), notifications.get(position).getTittle(), Toast.LENGTH_SHORT).show();
//                }
//
//                return false;
//            }
//
//            @Override
//            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
//
//            }
//
//            @Override
//            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
//
//            }
//        });

       if (SettingsActivity.theme==1) {
            //textView1.setTextColor(0xffffffff);
            //textView2.setTextColor(0xffffffff);
         //  notiification_card.setCardBackgroundColor(0xff424242);
//           layout.setBackgroundColor(0xff424242);

       }
      /*  else {
            textView1.setTextColor(0xff000000);
            textView2.setTextColor(0xff000000);

        }*/
       /* if (theme==1) {
            notiification_card.setCardBackgroundColor(0xff424242);
            layout.setBackgroundColor(0xff424242);
            recyclerView_n.setBackgroundColor(0xff424242);
        }*/

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
