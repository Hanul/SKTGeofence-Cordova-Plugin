package com.btncafe.cordova.sktgeofence;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;

import com.skt.geofence.GeoConstData;

public class GeoServiceReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {

		if (intent.getAction().equals("com.btncafe.cordova.sktgeofence.GEOEVENT")) {

			int fenceId = intent.getIntExtra(GeoConstData.FENCE_ID, 0);
			String eventName = intent.getStringExtra(GeoConstData.META_INFO);
			Intent content = new Intent(context, MainActivity.class);
			content.putExtra(GeoConstData.FENCE_ID, fenceId);
			content.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
			PendingIntent resultPendingIntent = PendingIntent.getActivity(context, 0, content, 0);
			NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
			NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context.getApplicationContext());
			mBuilder.setSmallIcon(R.drawable.ic_launcher);
			mBuilder.setContentTitle(eventName);
			mBuilder.setContentText(String.valueOf(fenceId));
			mBuilder.setTicker("testTicker" + String.valueOf(fenceId));
			mBuilder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
			mBuilder.setAutoCancel(false);

			mBuilder.setDefaults(Notification.DEFAULT_VIBRATE);
			mBuilder.setContentIntent(resultPendingIntent);// required
			mNotificationManager.notify(fenceId, mBuilder.build());
		}
	}
}