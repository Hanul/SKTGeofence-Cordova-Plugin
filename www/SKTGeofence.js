module.exports = {

	init : function(params, callback) {
		//REQUIRED: params
		//REQUIRED: params.packageName
		//REQUIRED: params.tdcProjectKey
		//OPTIONAL: callback

		cordova.exec(callback, undefined, 'SKTGeofenceCordovaPlugin', 'init', [params]);
	},

	createStoreGroup : function(storeGroupData, callback) {
		//REQUIRED: storeGroupData
		//OPTIONAL: callback

		cordova.exec(callback, undefined, 'SKTGeofenceCordovaPlugin', 'createStoreGroup', [storeGroupData]);
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

	removeStore : function(storeId, callback) {
		//REQUIRED: storeId
		//OPTIONAL: callback

		cordova.exec(callback, undefined, 'SKTGeofenceCordovaPlugin', 'removeStore', [storeId]);
	}
};
