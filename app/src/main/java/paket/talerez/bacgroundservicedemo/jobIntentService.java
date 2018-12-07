package paket.talerez.bacgroundservicedemo;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.JobIntentService;
import android.util.Log;
import android.widget.Toast;

public class jobIntentService extends JobIntentService
{
    private static final String TAG = jobIntentService.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "Task Execution Starts", Toast.LENGTH_SHORT).show();
    }

    public static void enqueueWork(Context context,Intent intent)
    {
        enqueueWork(context,jobIntentService.class,17,intent);
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {

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
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Task Execution Stops", Toast.LENGTH_SHORT).show();
    }
}
