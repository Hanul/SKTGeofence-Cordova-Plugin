package com.btncafe.cordova.sktgeofence;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.skt.geofence.AppData;
import com.skt.geofence.GeoFenceSyncState;
import com.skt.geofence.agent.service.AgentManager;
import com.skt.geofence.agent.service.AgentManager.GeoFenceAgentException;
import com.skt.geofence.agent.service.GeoFenceAgentListener;

public class SKTGeofence {

	private AgentManager agentManager;
	private AppData appData;
	private ConnectedListener connectedListener;

	private List<Handler> handlers = new ArrayList<Handler>();
	private List<ListHandler> listHandlers = new ArrayList<ListHandler>();

	private GeoFenceAgentListener agentListener = new GeoFenceAgentListener() {

		@Override
		public void onError(int arg0, String arg1) {
			// ignore.
		}

		@Override
		public void onResponseReceived(int resultCode, String json) {

			try {
				JSONObject result = new JSONObject(json);
				String dataType = result.getString("data_type");

				if (dataType.equals("StoreGroupList") || dataType.equals("StoreList")) {

					List<JSONObject> dataSet = new ArrayList<JSONObject>();

					JSONArray jsonArray = result.getJSONArray("data");

					for (int i = 0; i < jsonArray.length(); i += 1) {
						dataSet.add((JSONObject) jsonArray.get(i));
					}

					listHandlers.remove(0).handle(dataSet);

				} else {
					handlers.remove(0).handle(result.getJSONObject("data"));
				}

			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void onHttpStatusChanged(int httpState) {
			// ignore.
		}

		@Override
		public void onSynchronizeStatusChanged(GeoFenceSyncState arg0, boolean arg1) {
			// ignore.
		}

		@Override
		public void onServiceConnected() {
			agentManager.setAppData(appData);
			agentManager.syncWDB(appData);

			connectedListener.onConnected();
		}
	};

	public SKTGeofence(Context context, String tdcProjectKey, ConnectedListener connectedListener) {

		appData = new AppData();
		appData.packageName = "com.btncafe.cordova.sktgeofence";
		appData.tdcProjectKey = tdcProjectKey;

		this.connectedListener = connectedListener;

		agentManager = AgentManager.getInstance();
		try {
			agentManager.initialize(context, agentListener);
		} catch (GeoFenceAgentException e) {
			e.printStackTrace();
		}
	}

	public void addStoreGroup(JSONObject data, Handler handler) {
		handlers.add(handler);
		agentManager.setWStoreGroup(data.toString());
	}

	public void getStoreGroupList(ListHandler listHandler) {
		listHandlers.add(listHandler);
		agentManager.getWStoreGroupAll();
	}

	public void addStore(JSONObject data, Handler handler) {
		handlers.add(handler);
		agentManager.setWStore(data.toString());
	}

	public void getStoreList(int storeId, ListHandler listHandler) {
		listHandlers.add(listHandler);
		agentManager.getWStoreAll(String.valueOf(storeId));
	}

	public void release() {
		try {
			agentManager.release();
		} catch (GeoFenceAgentException e) {
			e.printStackTrace();
		}
	}
}
