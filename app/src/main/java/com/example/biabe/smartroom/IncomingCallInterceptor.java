package com.example.biabe.smartroom;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;

public class IncomingCallInterceptor extends BroadcastReceiver
{
    @Override
    public void onReceive(final Context context, Intent intent)
    {
        String state=intent.getStringExtra(TelephonyManager.EXTRA_STATE);
        if(TelephonyManager.EXTRA_STATE_RINGING.equals(state))
        {
            Room.GetInstance().Alert(4);
        }
    }
}
