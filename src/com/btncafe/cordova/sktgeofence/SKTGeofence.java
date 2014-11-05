package com.btncafe.cordova.sktgeofence;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import UPPERCASE.JAVA.JSON.UTIL;
import android.content.Context;
import android.os.AsyncTask;

import com.skt.geofence.AppData;
import com.skt.geofence.GeoFenceSyncState;
import com.skt.geofence.agent.service.AgentManager;
import com.skt.geofence.agent.service.AgentManager.GeoFenceAgentException;
import com.skt.geofence.agent.service.GeoFenceAgentListener;

public class SKTGeofence {

	private static AppData appData;

	private AgentManager agentManager;
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

				if (dataType.equals("StoreGroupList") || dataType.equals("StoreList") || dataType.equals("EventList") || dataType.equals("FenceList")) {

					List<JSONObject> dataSet = new ArrayList<JSONObject>();

					JSONArray jsonArray = result.getJSONArray("data");

					for (int i = 0; i < jsonArray.length(); i += 1) {
						dataSet.add((JSONObject) jsonArray.get(i));
					}

					listHandlers.remove(0).handle(dataSet);

				} else if (!result.isNull("data")) {
					handlers.remove(0).handle(result.getJSONObject("data"));
				} else {
					handlers.remove(0).handle(null);
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
		public void onSynchronizeStatusChanged(GeoFenceSyncState state, boolean arg1) {
			// ignore.
		}

		@Override
		public void onServiceConnected() {

			// GeoFenceAgent에 App의 정보를 설정
			agentManager.setAppData(appData);

			// Web-Poc와 단말간의 데이터 동기화
			agentManager.syncWDB(appData);

			if (connectedListener != null) {
				connectedListener.onConnected();
			}
		}
	};

	/**
	 * SKT GeoFenceAgent와 연결합니다.
	 * 
	 * @param context
	 * @param tdcProjectKey
	 * @param connectedListener
	 */
	public SKTGeofence(Context context, String packageName, String tdcProjectKey, ConnectedListener connectedListener) {

		appData = new AppData();
		appData.packageName = packageName;
		appData.tdcProjectKey = tdcProjectKey;

		this.connectedListener = connectedListener;

		agentManager = AgentManager.getInstance();
		try {
			agentManager.initialize(context, agentListener);
		} catch (GeoFenceAgentException e) {
			e.printStackTrace();
		}
	}

	/**
	 * SKT GeoFenceAgent와 연결합니다.
	 * 
	 * @param activity
	 * @param tdcProjectKey
	 */
	public SKTGeofence(Context context, String packageName, String tdcProjectKey) {
		this(context, packageName, tdcProjectKey, null);
	}

	private Handler emptyHandler = new Handler() {

		@Override
		public void handle(JSONObject data) {
			// ignore.
		}
	};

	/**
	 * Store Group을 생성합니다.
	 * 
	 * @param data
	 * @param handler
	 */
	public void createStoreGroup(JSONObject data, Handler handler) {

		JSONObject copy = UTIL.COPY_DATA(data);

		try {
			copy.put("groupIcon", 0);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		handlers.add(handler);
		agentManager.setWStoreGroup(copy.toString());
	}

	/**
	 * Store Group을 생성합니다.
	 * 
	 * @param data
	 */
	public void createStoreGroup(JSONObject data) {
		createStoreGroup(data, emptyHandler);
	}

	/**
	 * storeGroupId에 해당하는 Store Group 정보를 가져옵니다.
	 * 
	 * @param storeGroupId
	 * @param handler
	 */
	public void getStoreGroup(int storeGroupId, Handler handler) {
		handlers.add(handler);
		agentManager.getWStoreGroup(String.valueOf(storeGroupId));
	}

	/**
	 * storeGroupId에 해당하는 Store Group 정보를 수정합니다.
	 * 
	 * @param data
	 * @param handler
	 */
	public void updateStoreGroup(final JSONObject data, final Handler handler) {

		try {
			getStoreGroup(data.getInt("storeGroupId"), new Handler() {

				@Override
				public void handle(JSONObject originData) {

					UTIL.EXTEND_DATA(originData, data);

					handlers.add(handler);
					agentManager.updateWStoreGroup(originData.toString());
				}
			});

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	/**
	 * storeGroupId에 해당하는 Store Group 정보를 수정합니다.
	 * 
	 * @param data
	 */
	public void updateStoreGroup(JSONObject data) {
		updateStoreGroup(data, emptyHandler);
	}

	/**
	 * storeGroupId에 해당하는 Store Group 정보를 삭제합니다.
	 * 
	 * @param storeGroupId
	 * @param handler
	 */
	public void removeStoreGroup(int storeGroupId, Handler handler) {
		handlers.add(handler);
		agentManager.deleteWStoreGroup(String.valueOf(storeGroupId));
	}

	/**
	 * storeGroupId에 해당하는 Store Group 정보를 삭제합니다.
	 * 
	 * @param storeGroupId
	 */
	public void removeStoreGroup(int storeGroupId) {
		removeStoreGroup(storeGroupId, emptyHandler);
	}

	/**
	 * 모든 Store Group 정보를 불러옵니다.
	 * 
	 * @param listHandler
	 */
	public void getStoreGroupList(ListHandler listHandler) {
		listHandlers.add(listHandler);
		agentManager.getWStoreGroupAll();
	}

	public class CreateStoreTask extends AsyncTask<Void, Void, Void> {

		private JSONObject data;

		public CreateStoreTask(JSONObject data) {
			this.data = data;
		}

		@Override
		protected Void doInBackground(Void... params) {

			JSONObject copy = UTIL.COPY_DATA(data);

			try {

				BufferedInputStream in = null;
				StringBuffer sb = new StringBuffer();

				URL url = new URL("http://maps.googleapis.com/maps/api/geocode/json?latlng=" + data.getDouble("latitude") + "," + data.getDouble("longitude"));

				URLConnection urlConnection = url.openConnection();
				urlConnection.setRequestProperty("Accept-Language", "ko");

				in = new BufferedInputStream(urlConnection.getInputStream());

				byte[] bufRead = new byte[4096];
				int lenRead = 0;
				while ((lenRead = in.read(bufRead)) > 0) {
					sb.append(new String(bufRead, 0, lenRead));
				}

				copy.put("address", new JSONObject(sb.toString()).getJSONArray("results").getJSONObject(0).getString("formatted_address"));
				copy.put("floor", 1);

			} catch (JSONException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			agentManager.setWStore(copy.toString());

			return null;
		}
	}

	/**
	 * Store를 생성합니다.
	 * 
	 * @param data
	 * @param handler
	 */
	public void createStore(JSONObject data, Handler handler) {

		handlers.add(handler);

		CreateStoreTask createStoreTask = new CreateStoreTask(data);
		createStoreTask.execute();
	}

	/**
	 * Store를 생성합니다.
	 * 
	 * @param data
	 */
	public void createStore(JSONObject data) {
		createStore(data, emptyHandler);
	}

	/**
	 * storeId에 해당하는 Store 정보를 가져옵니다.
	 * 
	 * @param storeId
	 * @param handler
	 */
	public void getStore(int storeId, Handler handler) {
		handlers.add(handler);
		agentManager.getWStore(String.valueOf(storeId));
	}

	/**
	 * storeId에 해당하는 Store 정보를 수정합니다.
	 * 
	 * @param data
	 * @param handler
	 */
	public void updateStore(final JSONObject data, final Handler handler) {

		try {
			getStore(data.getInt("storeId"), new Handler() {

				@Override
				public void handle(JSONObject originData) {

					UTIL.EXTEND_DATA(originData, data);

					handlers.add(handler);
					agentManager.updateWStore(originData.toString());
				}
			});

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	/**
	 * storeId에 해당하는 Store 정보를 수정합니다.
	 * 
	 * @param data
	 */
	public void updateStore(JSONObject data) {
		updateStore(data, emptyHandler);
	}

	/**
	 * storeId에 해당하는 Store 정보를 삭제합니다.
	 * 
	 * @param storeId
	 * @param handler
	 */
	public void removeStore(int storeId, Handler handler) {
		handlers.add(handler);
		agentManager.deleteWStore(String.valueOf(storeId));
	}

	/**
	 * storeId에 해당하는 Store 정보를 삭제합니다.
	 * 
	 * @param storeId
	 */
	public void removeStore(int storeId) {
		removeStore(storeId, emptyHandler);
	}

	/**
	 * storeGroupId에 해당하는 Store Group의 모든 Store 정보를 불러옵니다.
	 * 
	 * @param storeGroupId
	 * @param listHandler
	 */
	public void getStoreList(int storeGroupId, ListHandler listHandler) {
		listHandlers.add(listHandler);
		agentManager.getWStoreAll(String.valueOf(storeGroupId));
	}

	/**
	 * OnDestroy등에서 GeoFenceAgent와 연결을 해제합니다.
	 */
	public void release() {
		try {
			agentManager.release();
		} catch (GeoFenceAgentException e) {
			e.printStackTrace();
		}
	}

	private static CheckInHandler checkInHandler;

	public static CheckInHandler getCheckInHandler() {
		return checkInHandler;
	}

	public static void setCheckInHandler(CheckInHandler checkInHandler) {
		SKTGeofence.checkInHandler = checkInHandler;
	}

	public static AppData getAppData() {
		return appData;
	}
}
