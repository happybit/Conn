package me.pzheng.conn;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

/**
 * Created by k on 1/20/15.
 */
public class DataConnAppWidget extends AppWidgetProvider {
    public static String CLICK_ACTION = "WidgetClickAction";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        final int N = appWidgetIds.length;

        for (int i = 0; i<N; i++) {
            int appWidgetId = appWidgetIds[i];

            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.data_conn_app_widget);
            views.setOnClickPendingIntent(R.id.widget_button, getPendingSelfIntent(context, CLICK_ACTION));

            //Intent intent = new Intent(context, DisplayMessageActivity.class);
            //PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
            //views.setOnClickPendingIntent(R.id.widget_button,pendingIntent);

            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }

    protected PendingIntent getPendingSelfIntent(Context context, String action) {
        Intent intent = new Intent(context, getClass());
        intent.setAction(action);
        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        if (intent.getAction().equals(CLICK_ACTION)) {
            //Toast.makeText(context, "action is: "+intent.getAction(), Toast.LENGTH_SHORT).show();
            Intent intentService = new Intent(context, WidgetIntentService.class);
            context.startService(intentService);
        }

    }

}
