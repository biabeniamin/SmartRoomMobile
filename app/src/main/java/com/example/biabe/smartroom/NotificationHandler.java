package com.example.biabe.smartroom;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.view.accessibility.AccessibilityEvent;

import java.util.ArrayList;

public class NotificationHandler extends AccessibilityService {

    ArrayList<String> expections = new ArrayList<String>(){{
        add("com.sec.android.gallery3d");
        add("com.microsoft.skydrive");
    }};

    ArrayList<String> allowed = new ArrayList<String>(){{
        add("com.instagram.android");
        add("com.facebook.orca");
        add("com.samsung.android.messaging");
    }};

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {


        if(allowed.contains(event.getPackageName()))
        {
            if(event.getPackageName().equals("com.samsung.android.messaging"))
            {
                Room.GetInstance().Alert(3);
            }
            else
            {
                Room.GetInstance().Alert(2);
            }
            //new HttpBackgroundWorker().execute("3");
            System.out.println("merge2");
        }

    }
    @Override
    public void onInterrupt() {
        // TODO Auto-generated method stub.
        System.out.println("merge3");
    }
    @Override
    protected void onServiceConnected() {
        AccessibilityServiceInfo info = new AccessibilityServiceInfo();
        info.feedbackType = 1;
        info.eventTypes = AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED;
        info.notificationTimeout = 2000;
        setServiceInfo(info);
        System.out.println("merge");
    }
}
