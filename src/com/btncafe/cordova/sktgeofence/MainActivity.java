package com.btncafe.cordova.sktgeofence;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.skt.geofence.AppData;
import com.skt.geofence.GeoFenceSyncState;
import com.skt.geofence.agent.service.AgentManager;
import com.skt.geofence.agent.service.AgentManager.GeoFenceAgentException;
import com.skt.geofence.agent.service.GeoFenceAgentListener;

public class MainActivity extends Activity {

	private String tdcProjectKey;

	private GeoFenceAgentListener agentListener = new GeoFenceAgentListener() {

		@Override
		public void onError(int arg0, String arg1) {
			Log.i("test", "onError: " + arg0 + " " + arg1);
		}

		@Override
		public void onResponseReceived(int resultCode, String data) {
			Log.i("test", "onResponseReceived: " + resultCode + " " + data);
		}

		@Override
		public void onHttpStatusChanged(int httpState) {
			Log.i("test", "onHttpStatusChanged: " + httpState);
		}

		@Override
		public void onSynchronizeStatusChanged(GeoFenceSyncState arg0, boolean arg1) {
			Log.i("test", "onSynchronizeStatusChanged: " + arg0 + " " + arg1);
		}

		@Override
		public void onServiceConnected() {
			Log.i("test", "onServiceConnected");

			AppData appdata = new AppData();
			appdata.appName = "API Test for BTNCafe";
			appdata.packageName = "com.btncafe.cordova.sktgeofence";
			appdata.tdcProjectKey = tdcProjectKey;
			mAgentManager.setAppData(appdata);
		}
	};

	AgentManager mAgentManager = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (mAgentManager == null) {
			mAgentManager = AgentManager.getInstance();
		}
		if (mAgentManager != null) {
			try {
				mAgentManager.initialize(this, agentListener);
			} catch (GeoFenceAgentException e) {
				e.printStackTrace();
			}
		}

		Properties prop = new Properties();
		InputStream input = null;

		AssetManager assetManager = this.getAssets();

		try {

			input = assetManager.open("config.properties");

			// load a properties file
			prop.load(input);

			// get the property value and print it out
			tdcProjectKey = prop.getProperty("tdcProjectKey");

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		final AppData appdata = new AppData();
		appdata.appName = "API Test for BTNCafe";
		appdata.packageName = "com.btncafe.cordova.sktgeofence";
		appdata.tdcProjectKey = tdcProjectKey;

		// 사용할 버튼을 선언해준다.
		Button button;

		// Java의 button 객체를 main.xml의 버튼(id가 main_button)과 연결해준다.
		button = (Button) findViewById(R.id.main_button);

		// 버튼의 클릭이벤트를 처리하기 위해 클릭리스너를 버튼에 등록해준다.
		button.setOnClickListener(new OnClickListener() {
			// 파라미터로 넘어오는 View는 현재 클릭된 View이다. 현재 클릭된 View는 button이다.
			public void onClick(View v) {

				if (mAgentManager != null) {
					mAgentManager.syncWDB(appdata);
				}

				JSONObject data1 = new JSONObject();

				try {
					data1.put("storeGroupName", "TEST");
					data1.put("groupName", "할인사냥테스트1");
					data1.put("groupDesc", "할인사냥테스트1");
					data1.put("groupIcon", 1);
					data1.put("updateUser", 0);
					data1.put("isUse", "Y");

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (mAgentManager != null) {
					// mAgentManager.setWStoreGroup(data1.toString());
				}

				if (mAgentManager != null) {
					mAgentManager.getWStoreGroupAll();
					mAgentManager.getWStoreAll("178");
				}

				JSONObject data = new JSONObject();

				try {
					data.put("storeGroupId", 178);
					data.put("name", "매장10");
					data.put("address", "서울특별시");
					data.put("floor", 1);
					data.put("latitude", 37.4999072);
					data.put("longitude", 127.0373932);
					data.put("telNo", "test");
					data.put("homepage", "test");
					data.put("isUse", "Y");

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (mAgentManager != null) {
					// mAgentManager.setWStore(data.toString());
				}

				if (mAgentManager != null) {
					// mAgentManager.getWFenceAll("API_Test_for_BTNCafe");
				}
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public boolean isPackageExisted(String targetPackage) {

		PackageManager pm = getPackageManager();
		List<ApplicationInfo> packages = pm.getInstalledApplications(0);

		for (ApplicationInfo packageInfo : packages) {
			if (packageInfo.packageName.equals(targetPackage)) {
				return true;
			}
		}

		return false;
	}

	public boolean isServiceRunningCheck() {

		ActivityManager manager = (ActivityManager) this.getSystemService(Activity.ACTIVITY_SERVICE);

		for (RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
			if ("ServiceName".equals(service.service.getClassName())) {
				return true;
			}
		}

		return false;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		if (mAgentManager != null) {
			try {
				mAgentManager.release();
			} catch (GeoFenceAgentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}