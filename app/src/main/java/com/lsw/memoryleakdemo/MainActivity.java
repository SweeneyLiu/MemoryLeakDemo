package com.lsw.memoryleakdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private static TestInnerBad testInnerBad = null;
    class TestInnerBad{

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate: ");
        setContentView(R.layout.activity_main);

//        SingletonBad.getInstance(this);//1.当MainActivity退出时会出现内存泄漏

//        LoginManager.getInstance(this).dealData();//2.当MainActivity退出时会出现内存泄漏

        //3.Handler造成的内存泄漏
        Button button = (Button)findViewById(R.id.handler_bad_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this,HandlerBadActivity.class);
                startActivity(intent);
            }
        });

        //4.Handler造成的内存泄漏
        Button button1 = (Button)findViewById(R.id.mat_button);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this,MatTestActivity.class);
                startActivity(intent1);
            }
        });

        //5.AsyncTask造成的内存泄漏
        Button button2 = (Button)findViewById(R.id.mat_button);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MainActivity.this,AsyncTaskLeakActivity.class);
                startActivity(intent2);
            }
        });


        //6.非静态内部类创建静态实例造成的内存泄漏
        /*if(testInnerBad == null){
            testInnerBad = new TestInnerBad();
        }
        finish();*/

        //由WebView引起的内存泄漏
        Button button3 = (Button)findViewById(R.id.web_button);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,WebViewActivity.class);
                startActivity(intent);
            }
        });

        Runnable runnable1 = new MyRunnable();

        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {

            }
        };

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
        MemoryLeakDemoApplication.getWatcher().watch(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");
    }

    private static class MyRunnable implements Runnable {
        @Override
        public void run() {

        }
    }

}
