package com.lsw.memoryleakdemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

public class HandlerBadActivity extends AppCompatActivity {

    private final Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_bad);

        //出现内存泄露
        //延迟10s发送一个消息
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //
            }
        },10000);
        this.finish();
    }

    //内存泄露解决方法
    //解决方法在onDestroy()中添加
    /*Handler.removeCallbacksAndMessages(null);
    Handler = null;*/

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MemoryLeakDemoApplication.getWatcher().watch(this);
    }
}
