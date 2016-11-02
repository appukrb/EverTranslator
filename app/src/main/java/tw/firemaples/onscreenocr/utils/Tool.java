package tw.firemaples.onscreenocr.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.github.johnpersano.supertoasts.SuperToast;
import com.github.johnpersano.supertoasts.util.Style;

/**
 * Created by Louis on 2016/3/1.
 */
public class Tool {
    private static final String KEY_DEBUG_MODE = "KEY_DEBUG_MODE";
    private static Tool _instance;
    private Context context;

    private static String LOG_TAG = "OnScreenOcr";

    public static void init(Context context) {
        _instance = new Tool(context);
    }

    public static Tool getInstance() {
        return _instance;
    }

    private Tool(Context context) {
        this.context = context;
    }

    public static boolean isServiceRunning(Context context, Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    public static void logError(String msg) {
        Log.e(LOG_TAG, msg);
    }

    public static void logInfo(String msg) {
        Log.i(LOG_TAG, msg);
    }

    public void showMsg(String msg) {
        if (context == null) {
            return;
        }
        SuperToast.create(context, msg, SuperToast.Duration.LONG,
                Style.getStyle(Style.GREEN, SuperToast.Animations.FADE)).show();
    }

    public void showErrorMsg(String msg) {
        if (context == null) {
            return;
        }
        SuperToast.create(context, msg, SuperToast.Duration.LONG,
                Style.getStyle(Style.RED, SuperToast.Animations.FADE)).show();
    }

    private SharedPreferences getSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }
}
