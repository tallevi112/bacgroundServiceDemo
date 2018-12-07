package paket.talerez.bacgroundservicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

public class MyBackgroundService extends Service {

    private static final String TAG = MyBackgroundService.class.getSimpleName();
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG,"OnCreate, Thread Name: "+Thread.currentThread().getName());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG,"onStartCommand , Thread Name: "+Thread.currentThread().getName());

        //perform task
        new MyAsyncTask().execute();

        return START_STICKY;

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG,"onBind , Thread Name: "+Thread.currentThread().getName());
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    class MyAsyncTask extends AsyncTask<Void,String,Void>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.i(TAG,"onPreExecute , Thread Name: "+Thread.currentThread().getName());
        }

        @Override //preform tasks in Background or worker Threads
        protected Void doInBackground(Void... voids) {
            Log.i(TAG,"doInBackground , Thread Name: "+Thread.currentThread().getName());

            int ctr = 1;
            while (ctr<=12)
            {
                publishProgress("Time elaped:"+ctr+"sec");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ctr++;
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            Log.i(TAG,"onProgressUpdate,Counter Value"+values[0] +", Thread Name: "+Thread.currentThread().getName());
            Toast.makeText(MyBackgroundService.this, values[0], Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Log.i(TAG,"onPostExecute , Thread Name: "+Thread.currentThread().getName());
            stopSelf();

        }
    }
}
