package com.btncafe.cordova.sktgeofence;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import android.util.Log;

public class SKTGeofenceCordovaPlugin extends CordovaPlugin {

	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		if (action.equals("echo")) {
			String message = args.getString(0); // null도 "null"로 오는구만?
			this.echo(message, callbackContext);
			return true;
		}
		return false;
	}

	private void echo(String message, CallbackContext callbackContext) {
		if (message != null && message.length() > 0) {
			Log.i("test", message);
			callbackContext.success(message);
		} else {
			callbackContext.error("Expected one non-empty string argument.");
		}
	}
}
