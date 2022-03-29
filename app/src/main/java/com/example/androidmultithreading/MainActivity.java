package com.example.androidmultithreading;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    public WorkerThread workerThread;
    public Handler handler=new Handler(Looper.getMainLooper()){
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            textView.setText((String)msg.obj);
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=findViewById(R.id.text_view_msg);
        workerThread=new WorkerThread();
        workerThread.executeTasks(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message msg=Message.obtain();
            msg.obj="Task A Completed...";
            handler.sendMessage(msg);

        }).executeTasks(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message msg=Message.obtain();
            msg.obj="Task B Completed...";
            handler.sendMessage(msg);
        }).executeTasks(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message msg=Message.obtain();
            msg.obj="Task C Completed...";
            handler.sendMessage(msg);
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        workerThread.quit();
    }
}