package com.example.vincent.lab4;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.RemoteViews;

/**
 * Created by Vincent on 2016/10/20.
 */

public class DynamicBroadcast extends BroadcastReceiver {
    public static final String DYNAMICACTION = "com.example.vincent.lab4.MydBroadcast";
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(DYNAMICACTION)) {
            Bundle bundle = intent.getExtras();
            String text = bundle.getString("text");
            Notification.Builder builder = new Notification.Builder(context);
            Bitmap icon = BitmapFactory.decodeResource(context.getResources(), R.mipmap.dynamic);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, new Intent(context, MainActivity.class), 0);
            builder.setContentTitle("动态广播").setContentText(text).setTicker("您有一条新消息")
                    .setLargeIcon(icon).setSmallIcon(R.mipmap.dynamic).setAutoCancel(true).setDefaults(Notification.DEFAULT_SOUND)
                    .setContentIntent(pendingIntent);
            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            Notification notify = builder.build();
            manager.notify(0, notify);
            RemoteViews remote = new RemoteViews(context.getPackageName(), R.layout.widget_demo);
            remote.setTextViewText(R.id.appwidget_text, text);
            remote.setImageViewResource(R.id.widget_image, R.mipmap.dynamic);
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            ComponentName componentName = new ComponentName(context,WidgetDemo.class);
            appWidgetManager.updateAppWidget(componentName, remote);
        }
    }
}
