module.exports = {

	init : function(params, callback) {
		//REQUIRED: params
		//REQUIRED: params.packageName
		//REQUIRED: params.tdcProjectKey
		//OPTIONAL: callback

		cordova.exec(callback, undefined, 'SKTGeofenceCordovaPlugin', 'init', [params]);
	},

	setCheckInHandler : function(callback) {
		//REQUIRED: callback

		cordova.exec(callback, undefined, 'SKTGeofenceCordovaPlugin', 'setCheckInHandler', []);
	},

	createStoreGroup : function(storeGroupData, callback) {
		//REQUIRED: storeGroupData
		//OPTIONAL: callback

		cordova.exec(callback, undefined, 'SKTGeofenceCordovaPlugin', 'createStoreGroup', [storeGroupData]);
	},

	updateStoreGroup : function(storeGroupData, callback) {
		//REQUIRED: storeGroupData
		//OPTIONAL: callback

		cordova.exec(callback, undefined, 'SKTGeofenceCordovaPlugin', 'updateStoreGroup', [storeGroupData]);
	},

	removeStoreGroup : function(storeGroupId, callback) {
		//REQUIRED: storeGroupId
		//OPTIONAL: callback

		cordova.exec(callback, undefined, 'SKTGeofenceCordovaPlugin', 'removeStoreGroup', [storeGroupId]);
	},

	createStore : function(storeData, callback) {
		//REQUIRED: storeData
		//OPTIONAL: callback

		cordova.exec(callback, undefined, 'SKTGeofenceCordovaPlugin', 'createStore', [storeData]);
	},

	updateStore : function(storeData, callback) {
		//REQUIRED: storeData
		//OPTIONAL: callback

		cordova.exec(callback, undefined, 'SKTGeofenceCordovaPlugin', 'updateStore', [storeData]);
	},

	removeStore : function(storeId, callback) {
		//REQUIRED: storeId
		//OPTIONAL: callback

		cordova.exec(callback, undefined, 'SKTGeofenceCordovaPlugin', 'removeStore', [storeId]);
	}
};
