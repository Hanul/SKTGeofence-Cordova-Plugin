module.exports = {

	init : function(tdcProjectKey) {
		cordova.exec(undefined, undefined, 'SKTGeofenceCordovaPlugin', 'init', [tdcProjectKey]);
	},

	createStoreGroup : function(storeGroupData, callback) {
		cordova.exec(callback, undefined, 'SKTGeofenceCordovaPlugin', 'createStoreGroup', [storeGroupData]);
	},

	createStore : function(storeData, callback) {
		cordova.exec(callback, undefined, 'SKTGeofenceCordovaPlugin', 'createStore', [storeData]);
	}
};
