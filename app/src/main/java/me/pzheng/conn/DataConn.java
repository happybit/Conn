package me.pzheng.conn;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;

import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by k on 3/29/15.
 */

public class DataConn {

    static Context mContext;

    public DataConn(Context mContext) {
        this.mContext = mContext;
    }

    public Boolean[] connecting() {
        Boolean[] connectResults;
        connectResults = new Boolean[3];
        connectResults[0] = true; // status of Airplane Mode
        connectResults[1] = true; // status of mobile data
        connectResults[2] = true; // status of wifi data

        if (isAirplaneModeEnabled(mContext)) {
            return connectResults;
        } else if (isWifiConnected()) {
            connectResults[0] = false;
            return connectResults;
        } else {
            connectResults[0] = false;
            connectResults[2] = false;
            int i = 0;
            int MAXRETRY = 10;

            setMobileDataConnection(false);
            waitFor(5000);


            while ((!isDataConnected())&&(i<MAXRETRY)) {

                Log.d("ADebugTag", "Value: " + String.valueOf(isDataConnected()));
                Log.d("ADebugTag", "Value: " + String.valueOf(i));

                setMobileDataConnection(false);
                waitFor(20);
                setMobileDataConnection(true);
                Log.d("ADebugTag", "Connected finished!");
                waitFor(10000);
                Log.d("ADebugTag", "Waiting finished!");
                i+=1;

                if (isDataConnected()) {
                    connectResults[1] = true;
                    return connectResults;
                }
                else if (i == MAXRETRY) {
                    return connectResults;
                }
            }
        }
        return connectResults;
    }
    private boolean isDataConnected() {
        ConnectivityManager connMgr = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        return networkInfo.isConnected();
    }

    private boolean isWifiConnected() {
        ConnectivityManager connMgr = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        return networkInfo.isConnected();
    }
    private static boolean isAirplaneModeEnabled(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
            return Settings.Global.getInt(context.getContentResolver(),
                    Settings.Global.AIRPLANE_MODE_ON, 0) != 0;
        }

        else {
            return Settings.System.getInt(context.getContentResolver(),
                    Settings.System.AIRPLANE_MODE_ON, 0) != 0;
        }
    }

    private static void setMobileDataEnabled(Context context, boolean enabled) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            String state = (enabled) ? "enable" : "disable";

            String command = String.format("svc data " + state + "\n");

            try{
                Process su = Runtime.getRuntime().exec("su");
                DataOutputStream outputStream = new DataOutputStream(su.getOutputStream());

                outputStream.writeBytes(command);
                outputStream.flush();

                outputStream.writeBytes("exit\n");
                outputStream.flush();
                try {
                    su.waitFor();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                outputStream.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }

        else {
            final ConnectivityManager conman = (ConnectivityManager)  context.getSystemService(Context.CONNECTIVITY_SERVICE);
            final Class conmanClass = Class.forName(conman.getClass().getName());
            final Field connectivityManagerField = conmanClass.getDeclaredField("mService");
            connectivityManagerField.setAccessible(true);
            final Object connectivityManager = connectivityManagerField.get(conman);
            final Class connectivityManagerClass =  Class.forName(connectivityManager.getClass().getName());
            final Method setMobileDataEnabledMethod = connectivityManagerClass.getDeclaredMethod("setMobileDataEnabled", Boolean.TYPE);
            setMobileDataEnabledMethod.setAccessible(true);

            setMobileDataEnabledMethod.invoke(connectivityManager, enabled);
        }

    }


    public static void setMobileDataConnection(Boolean connectionMode) {
        try {
            Log.d("ADebugTag", "Try to connect: " + String.valueOf(connectionMode));
            setMobileDataEnabled(mContext, connectionMode);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    private void waitFor(int duration) {
        try {
            Log.d("ADebugTag", "Waiting for" + String.valueOf(duration) + "ms started!");
            Thread.sleep(duration);
            Log.d("ADebugTag", "Waiting for" + String.valueOf(duration) + "ms finished!");
        } catch (InterruptedException e) {
            e.printStackTrace();
            // handle the exception...
            // For example consider calling Thread.currentThread().interrupt(); here.
        }
    }

    //private void showToast(CharSequence displayedText) {
    //    int duration = Toast.LENGTH_SHORT;
    //    Toast.makeText(mContext, displayedText, duration).show();
    //}
}
