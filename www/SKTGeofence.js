module.exports = {

	init : function(params) {
		//REQUIRED: params
		//REQUIRED: params.packageName
		//REQUIRED: params.tdcProjectKey

		cordova.exec(function() {
			// ignore.
		}, function() {
			// ignore.
		}, 'SKTGeofenceCordovaPlugin', 'init', [params]);
	},

	createStoreGroup : function(storeGroupData, callback) {
		//REQUIRED: storeGroupData
		//OPTIONAL: callback

		cordova.exec(callback, function() {
			// ignore.
		}, 'SKTGeofenceCordovaPlugin', 'createStoreGroup', [storeGroupData]);
	},

	createStore : function(storeData, callback) {
		//REQUIRED: storeData
		//OPTIONAL: callback

		cordova.exec(callback, function() {
			// ignore.
		}, 'SKTGeofenceCordovaPlugin', 'createStore', [storeData]);
	}
};
