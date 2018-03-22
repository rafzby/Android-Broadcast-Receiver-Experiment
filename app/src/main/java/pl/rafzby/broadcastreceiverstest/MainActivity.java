package pl.rafzby.broadcastreceiverstest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String ACTION_MY_NOTIFICATION = "pl.setapp.broadcast.MY_NOTIFICATION";
    private static final String TAG = "MainActivity";

    private Button mRegisterReceiverButton;
    private Button mSendBroadcastMessageButton;
    private Button mUnregisterReceiverButton;

    private BroadcastReceiver myReceiver =
            new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    Log.d(TAG, "Message received");
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSendBroadcastMessageButton = findViewById(R.id.send_notification);
        mRegisterReceiverButton = findViewById(R.id.register_receiver);
        mUnregisterReceiverButton = findViewById(R.id.unregister_receiver);

        final Intent intent = new Intent(ACTION_MY_NOTIFICATION);

        mSendBroadcastMessageButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        sendBroadcast(intent);
                    }
                });

        mRegisterReceiverButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        registerReceiver();
                    }
                });

        mUnregisterReceiverButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        unregisterReceiver();
                    }
                });
    }

    private void registerReceiver() {
        IntentFilter filer = new IntentFilter(ACTION_MY_NOTIFICATION);
        registerReceiver(myReceiver, filer);
        Log.d(TAG, "Registered received");
    }

    private void unregisterReceiver() {
        unregisterReceiver(myReceiver);
        Log.d(TAG, "Unregistered received");
    }
}
