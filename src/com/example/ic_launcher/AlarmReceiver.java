package com.example.ic_launcher;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import java.util.Calendar;
import java.util.Random;

import net.miidi.ad.push.AdPush;
import net.miidi.ad.push.AdPushManager;


public class AlarmReceiver extends BroadcastReceiver
{
  static boolean adFree = true;

  public static void initAd(Context paramContext)
  {
    Random localRandom = new Random();
    int[] arrayOfInt = new int[4];
    arrayOfInt[0] = R.drawable.ad_icon1;
    arrayOfInt[1] =  R.drawable.ad_icon2;
    arrayOfInt[2] =  R.drawable.ad_icon3;
    arrayOfInt[3] =  R.drawable.ad_icon4;
    AdPushManager.init(paramContext, "6199", "jkihpetrj3l0fiwk", false);
    AdPush.setPushAdIcon(arrayOfInt[localRandom.nextInt(4)]);
  }

  public static void sendGetAdMessage(Context paramContext)
  {
    if (adFree)
    {
      adFree = false;
      Tools.enableAlert(paramContext, Tools.calculateAlarm(30).getTimeInMillis());
    }
  }

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    Log.e("receive", "signabc");
    initAd(paramContext);
    adFree = true;
  }
}