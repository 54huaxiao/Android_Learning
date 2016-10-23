package com.example.vincent.lab4;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

/**
 * Created by Vincent on 2016/10/18.
 */

public class StaticBroadcast extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("com.example.vincent.lab4.MyBroadcast")) {
            Bundle bundle = intent.getExtras();
            String name = bundle.getString("name");
            int picId = bundle.getInt("picture");
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, new Intent(context, MainActivity.class), 0);
            Notification.Builder builder = new Notification.Builder(context);
            Bitmap icon = BitmapFactory.decodeResource(context.getResources(), picId);
            builder.setContentTitle("静态广播").setContentText(name).setTicker("您有一条新消息").setLargeIcon(icon).setSmallIcon(picId).setAutoCancel(true).setDefaults(Notification.DEFAULT_SOUND)
                    .setContentIntent(pendingIntent);
            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            Notification notify = builder.build();
            manager.notify(0, notify);
        }
    }
}
