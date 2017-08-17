package com.example.tmha.pushnotification.model;

/**
 * Created by tmha on 8/17/2017.
 */

public class Device {
    private String mIdDeviceToken;
    private String mName;

    public Device() {
    }

    public Device(String mIdDeviceToken, String mName) {
        this.mIdDeviceToken = mIdDeviceToken;
        this.mName = mName;
    }

    public String getmIdDeviceToken() {
        return mIdDeviceToken;
    }

    public void setmIdDeviceToken(String mIdDeviceToken) {
        this.mIdDeviceToken = mIdDeviceToken;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }
}
