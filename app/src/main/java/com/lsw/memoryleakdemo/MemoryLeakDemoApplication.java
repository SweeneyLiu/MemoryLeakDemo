package com.lsw.memoryleakdemo;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

public class MemoryLeakDemoApplication extends Application {
    private static Context context;
    private static RefWatcher mRefWatcher;
    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        mRefWatcher = LeakCanary.install(this);

        context = getApplicationContext();
    }

    public static Context getMemoryLeakDemoApplicationContext(){
        return context;
    }

    public static RefWatcher getWatcher(){
        return mRefWatcher;
    }
}
