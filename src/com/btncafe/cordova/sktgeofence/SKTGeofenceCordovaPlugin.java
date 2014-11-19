package com.btncafe.cordova.sktgeofence;

import java.util.List;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.btncafe.android.sktgeofence.CheckInHandler;
import com.btncafe.android.sktgeofence.ConnectedListener;
import com.btncafe.android.sktgeofence.Handler;
import com.btncafe.android.sktgeofence.ListHandler;
import com.btncafe.android.sktgeofence.SKTGeofence;

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
				public void handle(JSONObject savedData) {
					callbackContext.success(savedData);
				}
			});
		}

		else if (action.equals("getStoreGroup")) {

			sktgeofence.getStoreGroup(args.getInt(0), new Handler() {

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
				public void handle(JSONObject nullData) {
					callbackContext.success();
				}
			});
		}

		else if (action.equals("getAllStoreGroupList")) {

			sktgeofence.getAllStoreGroupList(new ListHandler() {

				@Override
				public void handle(List<JSONObject> dataSet) {

					JSONObject data = new JSONObject();
					try {
						data.put("dataSet", dataSet);
					} catch (JSONException e) {
						e.printStackTrace();
					}

					callbackContext.success(data);
				};
			});
		}

		else if (action.equals("createStore")) {

			sktgeofence.createStore(args.getJSONObject(0), new Handler() {

				@Override
				public void handle(JSONObject savedData) {
					callbackContext.success(savedData);
				}
			});
		}

		else if (action.equals("getStore")) {

			sktgeofence.getStore(args.getInt(0), new Handler() {

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
				public void handle(JSONObject nullData) {
					callbackContext.success();
				}
			});
		}

		else if (action.equals("getStoreListInGroup")) {

			sktgeofence.getStoreListInGroup(args.getInt(0), new ListHandler() {

				@Override
				public void handle(List<JSONObject> dataSet) {

					JSONObject data = new JSONObject();
					try {
						data.put("dataSet", dataSet);
					} catch (JSONException e) {
						e.printStackTrace();
					}

					callbackContext.success(data);
				};
			});
		}

		else if (action.equals("createEventGroup")) {

			sktgeofence.createEventGroup(args.getJSONObject(0), new Handler() {

				@Override
				public void handle(JSONObject savedData) {
					callbackContext.success(savedData);
				}
			});
		}

		else if (action.equals("getEventGroup")) {

			sktgeofence.getEventGroup(args.getInt(0), new Handler() {

				@Override
				public void handle(JSONObject data) {
					callbackContext.success(data);
				}
			});
		}

		else if (action.equals("updateEventGroup")) {

			sktgeofence.updateEventGroup(args.getJSONObject(0), new Handler() {

				@Override
				public void handle(JSONObject data) {
					callbackContext.success(data);
				}
			});
		}

		else if (action.equals("removeEventGroup")) {

			sktgeofence.removeEventGroup(args.getInt(0), new Handler() {

				@Override
				public void handle(JSONObject nullData) {
					callbackContext.success();
				}
			});
		}

		else if (action.equals("getAllEventGroupList")) {

			sktgeofence.getAllEventGroupList(new ListHandler() {

				@Override
				public void handle(List<JSONObject> dataSet) {

					JSONObject data = new JSONObject();
					try {
						data.put("dataSet", dataSet);
					} catch (JSONException e) {
						e.printStackTrace();
					}

					callbackContext.success(data);
				};
			});
		}

		else if (action.equals("createEvent")) {

			sktgeofence.createEvent(args.getJSONObject(0), new Handler() {

				@Override
				public void handle(JSONObject savedData) {
					callbackContext.success(savedData);
				}
			});
		}

		else if (action.equals("getEvent")) {

			sktgeofence.getEvent(args.getInt(0), new Handler() {

				@Override
				public void handle(JSONObject data) {
					callbackContext.success(data);
				}
			});
		}

		else if (action.equals("updateEvent")) {

			sktgeofence.updateEvent(args.getJSONObject(0), new Handler() {

				@Override
				public void handle(JSONObject data) {
					callbackContext.success(data);
				}
			});
		}

		else if (action.equals("removeEvent")) {

			sktgeofence.removeEvent(args.getInt(0), new Handler() {

				@Override
				public void handle(JSONObject nullData) {
					callbackContext.success();
				}
			});
		}

		else if (action.equals("getEventListByStore")) {

			sktgeofence.getEventListByStore(args.getInt(0), new ListHandler() {

				@Override
				public void handle(List<JSONObject> dataSet) {

					JSONObject data = new JSONObject();
					try {
						data.put("dataSet", dataSet);
					} catch (JSONException e) {
						e.printStackTrace();
					}

					callbackContext.success(data);
				};
			});
		}

		else {
			// Returning false results in a "MethodNotFound" error.
			return false;
		}

		return true;
	}
}
