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

			sktgeofence = new SKTGeofence(this.cordova.getActivity(), params.getString("packageName"), params.getString("tdcProjectKey"), new ConnectedListener() {

				@Override
				public void onConnected() {
					callbackContext.success();
				}
			});
		}

		else if (action.equals("setCheckInHandler")) {

			SKTGeofence.setCheckInHandler(new CheckInHandler() {

				@Override
				public void handle(int storeId) {
					callbackContext.success(storeId);
				}
			});
		}

		else if (action.equals("createStoreGroup")) {

			sktgeofence.createStoreGroup(args.getJSONObject(0), new Handler() {

				@Override
				public void handle(JSONObject data) {
					callbackContext.success(data);
				}
			});
		}

		else if (action.equals("updateStoreGroup")) {

			sktgeofence.updateStoreGroup(args.getJSONObject(0), new Handler() {

				@Override
				public void handle(JSONObject data) {
					callbackContext.success(data);
				}
			});
		}

		else if (action.equals("removeStoreGroup")) {

			sktgeofence.removeStoreGroup(args.getInt(0), new Handler() {

				@Override
				public void handle(JSONObject data) {
					callbackContext.success();
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

		}

		else if (action.equals("updateStore")) {

			sktgeofence.updateStore(args.getJSONObject(0), new Handler() {

				@Override
				public void handle(JSONObject data) {
					callbackContext.success(data);
				}
			});

		}

		else if (action.equals("removeStore")) {

			sktgeofence.removeStore(args.getInt(0), new Handler() {

				@Override
				public void handle(JSONObject data) {
					callbackContext.success();
				}
			});
		}

		else {
			// Returning false results in a "MethodNotFound" error.
			return false;
		}

		return true;
	}
}
