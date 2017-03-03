package com.lsw.memoryleakdemo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class AsyncTaskLeakActivity extends AppCompatActivity {

    private AsyncTask<Void, Void, Integer> asyncTask;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task_leak);
        mTextView = (TextView) findViewById(R.id.text_view);
        //耗时会造成内存泄露
        testAsyncTask();
        finish();
    }

    private void testAsyncTask() {
        asyncTask = new AsyncTask<Void, Void, Integer>() {
            @Override
            protected Integer doInBackground(Void... params) {
                int i = 0;
                //模拟耗时操作
                while (!isCancelled()) {
                    i++;
                    if (i > 1000000000) {
                        break;
                    }
                    Log.e("LeakCanary", "asyncTask---->" + i);
                }
                return i;
            }

            @Override
            protected void onPostExecute(Integer integer) {
                super.onPostExecute(integer);
                mTextView.setText(String.valueOf(integer));
            }
        };
        asyncTask.execute();

    }


    //退出时取消任务，避免内存泄露
    private void destroyAsyncTask() {
        if (asyncTask != null && !asyncTask.isCancelled()) {
            asyncTask.cancel(true);
        }
        asyncTask = null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MemoryLeakDemoApplication.getWatcher().watch(this);
        destroyAsyncTask();
    }

}
