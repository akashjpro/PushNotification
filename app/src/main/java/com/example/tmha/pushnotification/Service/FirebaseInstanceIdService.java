package com.example.tmha.pushnotification.Service;

import android.os.Build;
import android.util.Log;

import com.example.tmha.pushnotification.model.Device;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

/**
 * Created by Aka on 8/17/2017.
 */

public class FirebaseInstanceIdService extends com.google.firebase.iid.FirebaseInstanceIdService {
    private DatabaseReference mDatabase;
    private static final String REG_TOKEN = "REG_TOKEN";

    @Override
    public void onTokenRefresh() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        String recent_token = FirebaseInstanceId.getInstance().getToken();
        String reqString = Build.MANUFACTURER
                + " " + Build.MODEL + " " + Build.VERSION.RELEASE
                + " " + Build.VERSION_CODES.class.getFields()[android.os.Build.VERSION.SDK_INT].getName();

        Device device = new Device(recent_token, reqString);
        mDatabase.child("Device").push().setValue(device);
        Log.d(REG_TOKEN, "onTokenRefresh:" + recent_token);
    }
}
