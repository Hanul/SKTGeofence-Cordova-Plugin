package com.btncafe.cordova.sktgeofence;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
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
					PluginResult result = new PluginResult(PluginResult.Status.OK, data);
					result.setKeepCallback(true);
					callbackContext.sendPluginResult(result);
				}
			});
		}

		else if (action.equals("createStore")) {

			sktgeofence.createStore(args.getJSONObject(0), new Handler() {

				@Override
				public void handle(JSONObject data) {
					PluginResult result = new PluginResult(PluginResult.Status.OK, data);
					result.setKeepCallback(true);
					callbackContext.sendPluginResult(result);
				}
			});
		}

		return false;
	}
}
