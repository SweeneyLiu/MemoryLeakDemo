package com.lsw.memoryleakdemo;

import android.content.Context;

/**
 * Created by liushuwei on 2017/3/3.
 */

public class LoginManager {
    private static LoginManager mInstance;
    private Context mContext;

    private LoginManager(Context context) {
        this.mContext = context;//this.mContext = context.getApplicationContext();
    }


    public static LoginManager getInstance(Context context) {
        if (mInstance == null) {
            synchronized (LoginManager.class) {
                if (mInstance == null) {
                    mInstance = new LoginManager(context);
                }
            }
        }
        return mInstance;
    }

    public void dealData() {
    }

}
