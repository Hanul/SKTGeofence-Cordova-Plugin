module.exports = {
	echo : function(success, failure) {
		cordova.exec(success, failure, 'SKTGeofence', 'echo', []);
	}
};
