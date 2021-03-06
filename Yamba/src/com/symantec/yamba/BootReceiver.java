package com.symantec.yamba;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.util.Log;

public class BootReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		long interval = Long.parseLong(PreferenceManager
				.getDefaultSharedPreferences(context)
				.getString("interval", "0"));

		PendingIntent operation = PendingIntent.getService(context, 0,
				new Intent(context, RefreshService.class),
				PendingIntent.FLAG_UPDATE_CURRENT);

		AlarmManager alarmManager = (AlarmManager) context
				.getSystemService(Context.ALARM_SERVICE);

		alarmManager.setInexactRepeating(AlarmManager.RTC,
				System.currentTimeMillis(), interval, operation);

		Log.d("BootReceiver", "onReceived for interval: " + interval);
	}
}
