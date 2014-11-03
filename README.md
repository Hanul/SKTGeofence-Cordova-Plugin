# SKT Geofence API Cordova Plugin
SKT Geofence API를 Cordova에서 사용 가능하게 하는 플러그인 입니다.
* [UPPERCASE.JAVA-JSON-UTIL.jar](https://github.com/Hanul/UPPERCASE.JAVA-JSON-UTIL)가 필요합니다.

## 설치
```
cordova plugin add https://github.com/Hanul/SKTGeofence-Cordova-Plugin.git
```

## API
해당 플러그인은 Store Group, Store 두 가지 데이터 처리를 제공합니다.
#### Store Group
###### Data
| Name      | Type   | Description |
|-----------|--------|-------------|
| groupName | String | 매장 그룹명   |

#### Store
###### Data
| Name         | Type    | Description  |
|--------------|---------|--------------|
| storeGroupId | Integer | 매장 그룹 ID  |
| name         | String  | 매장명        |
| latitude     | Double  | 매장 위치 위도 |
| longitude    | Double  | 매장 위치 경도 |

## License
[MIT](LICENSE)
* Geofence API와 관련된 License는 [SK Telecom](http://www.sktelecom.com)에 있습니다.

## 작성자
[심영재](https://github.com/Hanul)
