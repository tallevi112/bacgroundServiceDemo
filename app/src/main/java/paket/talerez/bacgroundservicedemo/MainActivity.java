package paket.talerez.bacgroundservicedemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView txvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txvResult = (TextView)findViewById(R.id.txvResult);
    }

    public void srtartService(View view) {

        Intent intent = new Intent(this, MyBackgroundService.class);
        startService(intent);
    }

    public void stopservice(View view) {
        Intent intent = new Intent(this, MyIntentService.class);
        stopService(intent);
    }

    public void startIntentSrevice(View view) {
        Intent intent = new Intent(this, MyIntentService.class);
        intent.putExtra("sleepTime", 12);
        startService(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter("my.own.brodcast");
        LocalBroadcastManager.getInstance(this).registerReceiver(myLocalBrodcastResiver,intentFilter);

    }

    private BroadcastReceiver myLocalBrodcastResiver= new BroadcastReceiver(){

        @Override
        public void onReceive(Context context, Intent intent) {

            int result = intent.getIntExtra("result",-1);
            txvResult.setText("Task executed in"+result+" sec");
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(myLocalBrodcastResiver);
    }
}
