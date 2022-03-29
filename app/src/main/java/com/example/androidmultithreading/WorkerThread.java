package com.example.androidmultithreading;

import android.util.Log;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class WorkerThread extends Thread{
    private static final String TAG = "Worker Thread";
    private AtomicBoolean isAlive = new AtomicBoolean(true);
    ConcurrentLinkedQueue<Runnable> tasks_queue = new ConcurrentLinkedQueue<>();

    public WorkerThread(){
        super(TAG);
        start();
    }

    @Override
    public void run() {
        while (isAlive.get()){
            Runnable task=tasks_queue.poll();
            if(task!=null){
                task.run();
            }
        }
        Log.d(TAG, "run: "+TAG+"Terminated..");
    }
    public WorkerThread executeTasks(Runnable task){
        tasks_queue.add(task);
        return this;
    }
    public void quit(){
        isAlive.set(false);
    }
}
