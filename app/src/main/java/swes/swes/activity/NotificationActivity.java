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
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import swes.swes.Adapters.NotificationReyclerViewAdapter;
import swes.swes.Models.NotificationModel;
import swes.swes.R;

public class NotificationActivity extends AppCompatActivity {

    static int rt;
    static int theme;

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





        TextView textView1= (TextView) findViewById(R.id.notification_text_title);
        TextView textView2= (TextView) findViewById(R.id.notification_text_details);


        NotificationModel model1 =new NotificationModel("First Notification","Here's Notifaction in Details");
        NotificationModel model2 =new NotificationModel("Second Notification","Here's Notifaction in Details");
        NotificationModel model3 =new NotificationModel("Third Notification","Here's Notifaction in Details");

        final ArrayList<NotificationModel> notifications =new ArrayList<NotificationModel>();
        notifications.add(model1);
        notifications.add(model2);
        notifications.add(model3);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.notification_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        if (SettingsActivity.theme==1) {
            recyclerView.setBackgroundColor(getResources().getColor(R.color.grey_rec));
        }

            RecyclerView.Adapter adapter = new NotificationReyclerViewAdapter(notifications);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            GestureDetector gestureDetector = new GestureDetector(getApplicationContext(), new GestureDetector.SimpleOnGestureListener() {

                @Override public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

            });
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

                View child = rv.findChildViewUnder(e.getX(), e.getY());
                if(child != null && gestureDetector.onTouchEvent(e)) {
                    int position = rv.getChildAdapterPosition(child);

                    Toast.makeText(getApplicationContext(), notifications.get(position).getTittle(), Toast.LENGTH_SHORT).show();
                }

                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

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
    }
}
