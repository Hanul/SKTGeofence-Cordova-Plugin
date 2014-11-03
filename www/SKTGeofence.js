module.exports = {

	init : function(params) {
		//REQUIRED: params
		//REQUIRED: params.packageName
		//REQUIRED: params.tdcProjectKey

		cordova.exec(undefined, undefined, 'SKTGeofenceCordovaPlugin', 'init', [params]);
	},

	createStoreGroup : function(storeGroupData, callback) {
		//REQUIRED: storeGroupData
		//OPTIONAL: callback

		cordova.exec(callback, undefined, 'SKTGeofenceCordovaPlugin', 'createStoreGroup', [storeGroupData]);
	},

	createStore : function(storeData, callback) {
		//REQUIRED: storeData
		//OPTIONAL: callback

		cordova.exec(callback, undefined, 'SKTGeofenceCordovaPlugin', 'createStore', [storeData]);
	}
};
