module.exports = {

	init : function(params, connectedListener) {
		//REQUIRED: params
		//REQUIRED: params.packageName
		//REQUIRED: params.tdcProjectKey
		//OPTIONAL: connectedListener

		cordova.exec(connectedListener, undefined, 'SKTGeofenceCordovaPlugin', 'init', [params]);
	},

	setCheckInHandler : function(checkInHandler) {
		//REQUIRED: checkInHandler

		cordova.exec(checkInHandler, undefined, 'SKTGeofenceCordovaPlugin', 'setCheckInHandler', []);
	},

	createStoreGroup : function(data, handler) {
		//REQUIRED: data
		//OPTIONAL: handler

		cordova.exec(handler, undefined, 'SKTGeofenceCordovaPlugin', 'createStoreGroup', [data]);
	},
	
	getStoreGroup : function(storeGroupId, handler) {
		//REQUIRED: storeGroupId
		//REQUIRED: handler

		cordova.exec(handler, undefined, 'SKTGeofenceCordovaPlugin', 'getStoreGroup', [storeGroupId]);
	},

	updateStoreGroup : function(data, handler) {
		//REQUIRED: data
		//OPTIONAL: handler

		cordova.exec(handler, undefined, 'SKTGeofenceCordovaPlugin', 'updateStoreGroup', [data]);
	},

	removeStoreGroup : function(storeGroupId, handler) {
		//REQUIRED: storeGroupId
		//OPTIONAL: handler

		cordova.exec(handler, undefined, 'SKTGeofenceCordovaPlugin', 'removeStoreGroup', [storeGroupId]);
	},
	
	getAllStoreGroupList : function(listHandler) {
		//REQUIRED: listHandler

		cordova.exec(listHandler, undefined, 'SKTGeofenceCordovaPlugin', 'getAllStoreGroupList', []);
	},

	createStore : function(data, handler) {
		//REQUIRED: data
		//OPTIONAL: handler

		cordova.exec(handler, undefined, 'SKTGeofenceCordovaPlugin', 'createStore', [data]);
	},
	
	getStore : function(storeId, handler) {
		//REQUIRED: storeId
		//REQUIRED: handler

		cordova.exec(handler, undefined, 'SKTGeofenceCordovaPlugin', 'getStore', [storeId]);
	},

	updateStore : function(data, handler) {
		//REQUIRED: data
		//OPTIONAL: handler

		cordova.exec(handler, undefined, 'SKTGeofenceCordovaPlugin', 'updateStore', [data]);
	},

	removeStore : function(storeId, handler) {
		//REQUIRED: storeId
		//OPTIONAL: handler

		cordova.exec(handler, undefined, 'SKTGeofenceCordovaPlugin', 'removeStore', [storeId]);
	},
	
	getStoreListInGroup : function(storeGroupId, listHandler) {
		//REQUIRED: storeGroupId
		//REQUIRED: listHandler
	
		cordova.exec(listHandler, undefined, 'SKTGeofenceCordovaPlugin', 'getStoreListInGroup', [storeGroupId]);
	},
	
	createEventGroup : function(data, handler) {
		//REQUIRED: data
		//OPTIONAL: handler

		cordova.exec(handler, undefined, 'SKTGeofenceCordovaPlugin', 'createEventGroup', [data]);
	},
	
	getEventGroup : function(eventGroupId, handler) {
		//REQUIRED: eventGroupId
		//REQUIRED: handler

		cordova.exec(handler, undefined, 'SKTGeofenceCordovaPlugin', 'getEventGroup', [eventGroupId]);
	},

	updateEventGroup : function(data, handler) {
		//REQUIRED: data
		//OPTIONAL: handler

		cordova.exec(handler, undefined, 'SKTGeofenceCordovaPlugin', 'updateEventGroup', [data]);
	},

	removeEventGroup : function(eventGroupId, handler) {
		//REQUIRED: eventGroupId
		//OPTIONAL: handler

		cordova.exec(handler, undefined, 'SKTGeofenceCordovaPlugin', 'removeEventGroup', [eventGroupId]);
	},
	
	getAllEventGroupList : function(listHandler) {
		//REQUIRED: listHandler

		cordova.exec(listHandler, undefined, 'SKTGeofenceCordovaPlugin', 'getAllEventGroupList', []);
	},

	createEvent : function(data, handler) {
		//REQUIRED: data
		//OPTIONAL: handler

		cordova.exec(handler, undefined, 'SKTGeofenceCordovaPlugin', 'createEvent', [data]);
	},
	
	getEvent : function(eventId, handler) {
		//REQUIRED: eventId
		//REQUIRED: handler

		cordova.exec(handler, undefined, 'SKTGeofenceCordovaPlugin', 'getEvent', [eventId]);
	},

	updateEvent : function(data, handler) {
		//REQUIRED: data
		//OPTIONAL: handler

		cordova.exec(handler, undefined, 'SKTGeofenceCordovaPlugin', 'updateEvent', [data]);
	},

	removeEvent : function(eventId, handler) {
		//REQUIRED: eventId
		//OPTIONAL: handler

		cordova.exec(handler, undefined, 'SKTGeofenceCordovaPlugin', 'removeEvent', [eventId]);
	},
	
	getEventListByStore : function(storeId, listHandler) {
		//REQUIRED: storeId
		//REQUIRED: listHandler
	
		cordova.exec(listHandler, undefined, 'SKTGeofenceCordovaPlugin', 'getEventListByStore', [storeId]);
	}
};
