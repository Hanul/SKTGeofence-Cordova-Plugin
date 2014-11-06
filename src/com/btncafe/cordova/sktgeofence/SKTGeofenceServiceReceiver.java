package com.btncafe.cordova.sktgeofence;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.skt.geofence.GeoConstData;

public class SKTGeofenceServiceReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {

		if (intent.getAction().equals(SKTGeofence.getAppData().packageName + ".GEOEVENT")) {

			int storeId = intent.getIntExtra(GeoConstData.STORE_ID, 0);

			Log.i("SKTGeofenceServiceReceiver", "STORE ID:" + String.valueOf(storeId));

			CheckInHandler checkInHandler = SKTGeofence.getCheckInHandler();
			checkInHandler.handle(storeId);
		}
	}
}