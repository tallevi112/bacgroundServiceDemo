package paket.talerez.bacgroundservicedemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyBrodcastReciver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //write code in here

       if(intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
           Intent intentB = new Intent(context, jobIntentService.class);
           intentB.putExtra("sleeoTme", 12);
           jobIntentService.enqueueWork(context,intentB);
           //context.startService(intentB);
       }

    }
}
