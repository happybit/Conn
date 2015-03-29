package me.pzheng.conn;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

public class WidgetIntentService extends IntentService {
    public WidgetIntentService() {
        super("WidgetIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        DataConn dataConn = new DataConn(this);
        Boolean[] result = dataConn.connecting();
        String notifString;

        if (result[0] == true) {
            notifString = getString(R.string.airplane_mode_warning);
        } else if (result[2] == true) {
            notifString = getString(R.string.wifi_connected_warning);
        } else if (result[1] == false) {
            notifString = getString(R.string.max_retrans_warning);
        } else if (result[1] == true) {
            notifString = getString(R.string.data_connected_notif);
        } else {
            notifString = getString(R.string.other_warning);
        }

        showNotif(notifString);

    }

    private void showNotif(String notifString) {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle("Data Conn")
                .setContentText(notifString);

        int mNotificationId = 001;
        NotificationManager mNotifyMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mNotifyMgr.notify(mNotificationId, mBuilder.build());
    }

}

