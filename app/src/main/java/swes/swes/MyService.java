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

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import swes.swes.Network.CheckNetwork;

public class MyService extends Service {
    public MyService() {
    }

    NotificationCompat.Builder  builder;

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
        DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference("CC");
        final Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

          builder = new NotificationCompat.Builder(getApplicationContext());
        mRootRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                builder.setSmallIcon(R.mipmap.ic_launcher);
                builder.setContentTitle("Firebase Push Notification");
                builder.setContentText(dataSnapshot.getKey().toString());
                builder.setSound(defaultSoundUri);
                NotificationManager notificationManager = (NotificationManager) getApplicationContext(). getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify(1, builder.build());
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


    }

    @Override
    public IBinder onBind(Intent intent) {



            throw new UnsupportedOperationException("Not yet implemented");

        }




}
