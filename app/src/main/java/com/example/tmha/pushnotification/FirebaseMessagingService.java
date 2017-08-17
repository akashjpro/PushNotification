package com.example.tmha.pushnotification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.app.NotificationCompat;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.google.firebase.messaging.RemoteMessage;

import static com.example.tmha.pushnotification.MainActivity.EXTRA_MESSAGE_TEXT;

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
            final NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
            builder.setContentTitle("FCM Notification");
            builder.setContentText(remoteMessage.getNotification().getBody());
            builder.setAutoCancel(true);
            builder.setSmallIcon(R.mipmap.ic_launcher);
            builder.setContentIntent(pendingIntent);


            ImageRequest imageRequest = new ImageRequest(img_url, new Response.Listener<Bitmap>() {
                @Override
                public void onResponse(Bitmap response) {
                    builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(response));
                    NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    notificationManager.notify(0, builder.build());
                }
            }, 0, 0, null, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });

            NotificationSingleton.getInstance(this).addToRequestQue(imageRequest);
        }

    }
}
