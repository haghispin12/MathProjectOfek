package com.example.mathprojectofek;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BroadCastBattery extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        int battery=intent.getIntExtra("level",0);
        Toast.makeText(context, String.valueOf(battery)+"", Toast.LENGTH_SHORT).show();
    }
}
