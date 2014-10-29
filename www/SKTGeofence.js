module.exports = {
	echo : function(msg, success, failure) {
		cordova.exec(success, failure, 'SKTGeofenceCordovaPlugin', 'echo', [msg]);
	}
};
