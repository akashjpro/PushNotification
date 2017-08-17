package com.example.tmha.pushnotification;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;

/**
 * Created by Aka on 8/17/2017.
 */

public class FirebaseInstanceIdService extends com.google.firebase.iid.FirebaseInstanceIdService {

    private static final String REG_TOKEN = "REG_TOKEN";

    @Override
    public void onTokenRefresh() {
        String recent_token = FirebaseInstanceId.getInstance().getToken();
        Log.d(REG_TOKEN, "onTokenRefresh:" + recent_token);
    }
}
