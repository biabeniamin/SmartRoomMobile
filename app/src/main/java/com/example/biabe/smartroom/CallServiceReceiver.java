package com.example.biabe.smartroom;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

public class CallServiceReceiver extends BroadcastReceiver {
    TelephonyManager telephony;

    public void onReceive(Context context, Intent intent) {
        IncomingCallInterceptor phoneListener = new IncomingCallInterceptor();
        telephony = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        telephony.listen(phoneListener, PhoneStateListener.LISTEN_CALL_STATE);
    }

    public void onDestroy() {
        telephony.listen(null, PhoneStateListener.LISTEN_NONE);
    }

}