package com.lsw.memoryleakdemo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MatTestActivity extends AppCompatActivity {

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mat_test);

        final TextView textView = (TextView) findViewById(R.id.textView);

        if (mHandler != null) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    textView.setText("MAT");
                    mHandler.postDelayed(this, 1000);
                }
            });
        }
    }



    //内存泄露：解决方法在onDestroy()中添加
    /*mHandler.removeCallbacksAndMessages(null);
    mHandler = null;*/
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
