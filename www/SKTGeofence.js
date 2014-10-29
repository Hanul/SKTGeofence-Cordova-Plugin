module.exports = {
	echo : function(msg, success, failure) {
		cordova.exec(success, failure, 'SKTGeofence', 'echo', [msg]);
	}
};
