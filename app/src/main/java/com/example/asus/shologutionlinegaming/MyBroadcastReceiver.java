package com.example.asus.shologutionlinegaming;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.accessibility.AccessibilityManager;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context,"i am broad cast",Toast.LENGTH_LONG).show();
    }
}
