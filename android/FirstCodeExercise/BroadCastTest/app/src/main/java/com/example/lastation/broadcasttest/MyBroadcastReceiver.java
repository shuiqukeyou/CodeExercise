package com.example.lastation.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"received in MybroadcastReceiver",Toast.LENGTH_SHORT).show();
        abortBroadcast();
    }
}
