package swes.swes;

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;

import swes.swes.Models.NotificationModel;
import swes.swes.Network.CheckNetwork;
import swes.swes.PostsFeature.Post;
import swes.swes.SharedPref.PrefManager;
import swes.swes.classes.NotificationHolder;
import swes.swes.classes.Ref;

public class MyService extends Service {
    public MyService() {
    }

    NotificationCompat.Builder  builder;
    String Node="Levels";
    ArrayList<NotificationModel> NoticationList ;

    String uid;

    FirebaseDatabase database = FirebaseDatabase.getInstance();



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        NoticationList = new ArrayList<NotificationModel>();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {

             uid = user.getUid();
        }

        DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference(Node);
        final Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        builder = new NotificationCompat.Builder(getApplicationContext());
        mRootRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                builder.setSmallIcon(R.mipmap.ic_launcher);
               builder.setContentTitle(Node);
                builder.setContentText(dataSnapshot.getKey().toString());
                builder.setSound(defaultSoundUri);
                NotificationManager notificationManager = (NotificationManager) getApplicationContext(). getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify(1, builder.build());

                for(DataSnapshot d :dataSnapshot.getChildren()) {
                   // Log.d("99999999999999999", d.getKey().toString());

                }




//                FirebaseDatabase database = FirebaseDatabase.getInstance();
//                DatabaseReference myRef = database.getReference("Notification");
//                NotificationModel NH =new NotificationModel(Node,
//                        dataSnapshot.getKey().toString());
//                myRef.push().setValue(NH);
                NotificationModel notificationModel =new NotificationModel(
                        Node,
                       dataSnapshot.getKey().toString()
                );
              NoticationList.add(notificationModel);
                Gson gson =new Gson();
                String jsonList = gson.toJson(NoticationList);
                PrefManager prefManager = new PrefManager(getApplicationContext());
                prefManager.setNotificationList(jsonList);
                Log.d("jjjjjjjjjjjjjjjj",jsonList);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        CheckNetwork cn = CheckNetwork.getInstance();
        cn.setContext(getApplicationContext());
//        while (true) {
//            if (!cn.isNetworkAvailable()) {
//                try {
//                    AlertDialog alertDialog = new AlertDialog.Builder(getApplicationContext()).create();
//
//                    alertDialog.setTitle("Info");
//                    alertDialog.setMessage("Internet not available, Cross check your internet connectivity and try again");
//                    alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
//                    alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int which) {
//                            //finish();
//
//                        }
//                    });
//
//                    alertDialog.show();
//                } catch (Exception e) {
//                    Log.d("Dialog Exception", "Show Dialog: " + e.getMessage());
//                }
//            }



    }



    @Override
    public IBinder onBind(Intent intent) {



            throw new UnsupportedOperationException("Not yet implemented");

        }




}
