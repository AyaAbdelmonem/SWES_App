package swes.swes.SharedPref;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Sameh on 6/16/2017.
 */

public class PrefManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    // shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "SWES_DATA";

    private static final String NOTIFICATION_LIST = "NOTIFICATION_LIST";

    public PrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setNotificationList(String s) {
        editor.putString(NOTIFICATION_LIST, s);
        editor.commit();
    }

    public String getNotificationList() {
        return pref.getString(NOTIFICATION_LIST, "");
    }

}
