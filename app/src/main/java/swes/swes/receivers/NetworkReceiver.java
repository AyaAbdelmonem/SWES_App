package swes.swes.receivers;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.util.Log;
import android.widget.Toast;

import swes.swes.Network.CheckNetwork;

public class NetworkReceiver extends BroadcastReceiver {
    public NetworkReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

        CheckNetwork checkNetwork =  CheckNetwork.getInstance();
        checkNetwork.setContext(context);
        boolean network= checkNetwork.isNetworkAvailable();
        if (!network){
            //Toast.makeText(context,"Check internet connection",Toast.LENGTH_SHORT).show();
            try {
                AlertDialog alertDialog = new AlertDialog.Builder(context).create();

                alertDialog.setTitle("Info");
                alertDialog.setMessage("Internet not available, Cross check your internet connectivity and try again");
                alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //finish();

                    }
                });

                alertDialog.show();
            }
            catch(Exception e)
            {
                Log.d("Dialog Exception", "Show Dialog: "+e.getMessage());
            }

            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            android.net.NetworkInfo wifi = cm
                    .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo datac = cm
                    .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if ((wifi != null & datac != null)
                    && (wifi.isConnected() | datac.isConnected())) {
                //connection is avlilable
            }else{
                //no connection
                Toast toast = Toast.makeText(context, "No Internet Connection",
                        Toast.LENGTH_LONG);
                toast.show();
            }
        }
    }
}
