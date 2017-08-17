package com.example.tmha.pushnotification.Service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.example.tmha.pushnotification.manager.NotificationSingleton;
import com.example.tmha.pushnotification.R;
import com.example.tmha.pushnotification.activity.MainActivity;
import com.google.firebase.messaging.RemoteMessage;

import static com.example.tmha.pushnotification.activity.MainActivity.EXTRA_MESSAGE_TEXT;

/**
 * Created by Aka on 8/17/2017.
 */

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if (remoteMessage.getData().size()>0){

            String title, message, img_url;

            title = remoteMessage.getData().get("title");
            message = remoteMessage.getData().get("message");
            img_url = remoteMessage.getData().get("img_url");


            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(EXTRA_MESSAGE_TEXT, remoteMessage.getNotification().getBody());
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
            Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
            final NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
            builder.setContentTitle(title);
            builder.setContentText(message);
            builder.setContentIntent(pendingIntent);
            builder.setAutoCancel(true);
            builder.setSound(sound);
            builder.setSmallIcon(R.mipmap.ic_launcher);


            ImageRequest imageRequest = new ImageRequest(img_url, new Response.Listener<Bitmap>() {
                @Override
                public void onResponse(Bitmap response) {
                    builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(response));
                    NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    notificationManager.notify(0, builder.build());
                }
            }, 0, 0, null, Bitmap.Config.RGB_565, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });

            NotificationSingleton.getInstance(this).addToRequestQue(imageRequest);
        }else {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(EXTRA_MESSAGE_TEXT, remoteMessage.getNotification().getBody());
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
            Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            final NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
            builder.setContentTitle("Notification");
            builder.setContentText(remoteMessage.getNotification().getBody());
            builder.setContentIntent(pendingIntent);
            builder.setAutoCancel(true);
            builder.setSound(sound);
            builder.setSmallIcon(R.mipmap.ic_launcher);
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(0, builder.build());

        }

    }
}
