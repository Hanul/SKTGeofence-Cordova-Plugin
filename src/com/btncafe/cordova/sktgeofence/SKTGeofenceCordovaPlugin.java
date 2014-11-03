package com.btncafe.cordova.sktgeofence;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SKTGeofenceCordovaPlugin extends CordovaPlugin {

	private SKTGeofence sktgeofence;

	@Override
	public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {

		if (action.equals("init")) {
			JSONObject params = args.getJSONObject(0);
			sktgeofence = new SKTGeofence(this.cordova.getActivity(), params.getString("packageName"), params.getString("tdcProjectKey"));
		}

		else if (action.equals("createStoreGroup")) {

			sktgeofence.createStoreGroup(args.getJSONObject(0), new Handler() {

				@Override
				public void handle(JSONObject data) {
					callbackContext.success(data);
				}
			});
		}

		else if (action.equals("createStore")) {

			sktgeofence.createStore(args.getJSONObject(0), new Handler() {

				@Override
				public void handle(JSONObject data) {
					callbackContext.success(data);
				}
			});

		} else {
			// Returning false results in a "MethodNotFound" error.
			return false;
		}

		return true;
	}
}
