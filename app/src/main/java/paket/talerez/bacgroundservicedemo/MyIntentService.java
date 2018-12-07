package paket.talerez.bacgroundservicedemo;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

public class MyIntentService extends IntentService
{
    private static final String TAG = MyIntentService.class.getSimpleName();

    //provideName to worker thread
    public MyIntentService() {
        super("MyBackgroundThread");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG,"OnCreate, Thread Name: "+Thread.currentThread().getName());
        Toast.makeText(this, "Task Execution Starts", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.i(TAG,"onHandleIntent, Thread Name: "+Thread.currentThread().getName());

        //write code here
       int duration =  intent.getIntExtra("sleepTime",-1);
        int ctr = 1;
        while (ctr<=duration)
        {
            Log.i(TAG,"Time selaped:"+ctr+"sec");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ctr++;
        }

        Intent localIntent = new Intent("my.own.brodcast");
        localIntent.putExtra("result",ctr);
        LocalBroadcastManager.getInstance(this).sendBroadcast(localIntent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Task Execution Finish", Toast.LENGTH_SHORT).show();
        Log.i(TAG,"onDestroy, Thread Name: "+Thread.currentThread().getName());
    }
}
