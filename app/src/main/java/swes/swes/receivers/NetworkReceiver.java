package swes.swes.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
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
            Toast.makeText(context,"Check internet connection",Toast.LENGTH_SHORT).show();
        }
    }
}
