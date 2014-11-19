# SKT Geofence API Cordova Plugin
SKT Geofence API를 Cordova에서 사용 가능하게 하는 플러그인 입니다.

## 설치
1. 프로젝트에 플러그인을 추가합니다.
```
cordova plugin add https://github.com/Hanul/SKTGeofence-Cordova-Plugin.git
```
2. `/platforms/android/AndroidManifest.xml`의 `application` 이하에 하단의 내용을 추가합니다. `{{YOUR PACKAGE NAME}}` 부분에는 현재 프로젝트의 패키지 명을 입력합니다.
```XML
<receiver android:enabled="true" android:exported="true" android:name="com.btncafe.cordova.SKTGeofence.SKTGeofenceServiceReceiver">
	<intent-filter>
    	<action android:name="{{YOUR PACKAGE NAME}}.GEOEVENT" />
    </intent-filter>
</receiver>
```

## API
#### 초기화
* `SKTGeofence.init({packageName, tdcProjectKey}, connectedListener)` Geofence API를 초기화하고, SKT GeoFenceAgent와 연결합니다. 연결이 되면, connectedListener을 실행합니다.
* `SKTGeofence.setCheckInHandler(checkInHandler)` 특정 펜스 내에 Check In 하였을때 실행할 Handler를 등록합니다. Handler는 Store Id를 가져옵니다.

해당 플러그인은 Store Group, Store, Event Group, Event 네가지 데이터의 처리를 제공합니다.
#### Store Group
* `SKTGeofence.createStoreGroup(data, handler)` 매장 그룹 정보를 생성하고, 정보를 가져옵니다.
* `SKTGeofence.getStoreGroup(storeGroupId, handler)` storeGroupId에 해당하는 매장 그룹 정보를 가져옵니다.
* `SKTGeofence.updateStoreGroup(data, handler)` data의 storeGroupId에 해당하는 매장 그룹 정보를 수정하고, 수정된 정보를 가져옵니다.
* `SKTGeofence.removeStoreGroup(storeGroupId)` storeGroupId에 해당하는 매장 그룹 정보를 삭제합니다.
* `SKTGeofence.getAllStoreGroupList(listHandler)` 모든 매장 그룹 정보를 가져옵니다.

###### Store Group Data 형태
| Name      | Type   | Description |
|-----------|--------|-------------|
| groupName | String | 매장 그룹명   |

#### Store
* `SKTGeofence.createStore(data, handler)` 매장 정보를 생성하고, 정보를 가져옵니다.
* `SKTGeofence.getStore(storeId, handler)` storeId에 해당하는 매장 정보를 가져옵니다.
* `SKTGeofence.updateStore(data, handler)` data의 storeId에 해당하는 매장 정보를 수정하고, 수정된 정보를 가져옵니다.
* `SKTGeofence.removeStore(storeId)` storeId에 해당하는 매장 정보를 삭제합니다.
* `SKTGeofence.getStoreListInGroup(storeGroupId, listHandler)` storeGroupId에 해당하는 매장 그룹 내의 모든 매장 정보를 가져옵니다.

###### Strore Data 형태
| Name         | Type    | Description  |
|--------------|---------|--------------|
| storeGroupId | Integer | 매장 그룹 ID  |
| name         | String  | 매장명        |
| latitude     | Double  | 매장 위치 위도 |
| longitude    | Double  | 매장 위치 경도 |

#### Event Group
* `SKTGeofence.createEventGroup(data, handler)` 이벤트 그룹 정보를 생성하고, 정보를 가져옵니다.
* `SKTGeofence.getEventGroup(eventGroupId, handler)` eventGroupId에 해당하는 이벤트 그룹 정보를 가져옵니다.
* `SKTGeofence.updateEventGroup(data, handler)` data의 eventGroupId에 해당하는 이벤트 그룹 정보를 수정하고, 수정된 정보를 가져옵니다.
* `SKTGeofence.removeEventGroup(eventGroupId)` eventGroupId에 해당하는 이벤트 그룹 정보를 삭제합니다.
* `SKTGeofence.getAllEventGroupList(listHandler)` 모든 이벤트 그룹 정보를 가져옵니다.

###### Event Group Data 형태
| Name      | Type   | Description |
|-----------|--------|-------------|
| groupName | String | 이벤트 그룹명   |
| startDate | String | 이벤트 시작 일자   |
| endDate   | String | 이벤트 종료 일자 |

#### Event
* `SKTGeofence.createEvent(data, handler)` 이벤트 정보를 생성하고, 정보를 가져옵니다.
* `SKTGeofence.getEvent(eventId, handler)` eventId에 해당하는 이벤트 정보를 가져옵니다. (현재 Geofence API 에서는 작동하지 않습니다. 추후 업데이트 예정)
* `SKTGeofence.updateEvent(data, handler)` eventId에 해당하는 이벤트 정보를 수정하고, 수정된 정보를 가져옵니다. (현재 Geofence API 에서는 작동하지 않습니다. 추후 업데이트 예정)
* `SKTGeofence.removeEvent(eventId)` eventId에 해당하는 이벤트 정보를 삭제합니다.
* `SKTGeofence.getEventListByStore(storeId, listHandler)` 특정 매장이 속해있는 모든 이벤트 정보를 가져옵니다.

###### Event Data 형태
| Name           | Type    | Description  |
|----------------|---------|--------------|
| eventGroupId   | Integer | 매장 그룹 ID  |
| eventName      | String  | 이벤트 명        |
| eventCheckType | String  | 이벤트 체크 방법 (CheckIn : 팬스 안으로 진입 시, CheckOut : 팬스 밖으로 나갈 때, Stay : 팬스 안에서 머무를 때) |

## License
[MIT](LICENSE)
* Geofence API와 관련된 License는 [SK Telecom](http://www.sktelecom.com)에 있습니다.

## 작성자
[심영재](https://github.com/Hanul)
