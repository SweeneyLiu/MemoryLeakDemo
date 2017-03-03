package com.lsw.memoryleakdemo;

import android.content.Context;

/**
 * Created by Administrator on 2017/3/1 0001.
 */

public class SingletonBad {
    private static SingletonBad singletonBad;
    private Context context;

    private SingletonBad(Context context){
        this.context = context;//内存泄露：this.mContext = context.getApplicationContext();（修改）
    }

    public static SingletonBad getInstance(Context context){
        if(singletonBad == null){
            synchronized (SingletonBad.class){
                if (singletonBad == null) {
                    singletonBad = new SingletonBad(context);
                }
            }
        }
        return singletonBad;
    }
}
