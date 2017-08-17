package com.example.tmha.pushnotification;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Aka on 8/17/2017.
 */

public class NotificationSingleton {

    private static NotificationSingleton mInstance;
    private static Context mContext;
    private RequestQueue mRequestQueue;

    public NotificationSingleton(Context mContext) {
        this.mContext = mContext;
        mRequestQueue = getRequestQueue();
    }

    private RequestQueue getRequestQueue(){
        if (mRequestQueue == null){
            mRequestQueue = Volley.newRequestQueue(mContext);
        }
        return mRequestQueue;
    }

    public static synchronized NotificationSingleton getInstance(Context context){
        if(mInstance == null){
            mInstance = new NotificationSingleton(context);
        }
        return mInstance;
    }

    public <T>void addToRequestQue(Request<T> request){
        getRequestQueue().add(request);
    }

}
