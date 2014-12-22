'use strict';

/* Helpers */

services.factory('CleanStatus', function() {
	return function(completeStatus) {
		var status = {};
		angular.forEach(completeStatus, function(value, key) {
			if(key == "OnOffState")
		    {
				key = "Status";
		    }
		   	else
		   	{
		   		var end = key.indexOf("Measurement");
		       	if(end==-1)
		       	   end = key.indexOf("State");
		       	 
		       	key = key.slice(0, end);
		   	}
			status[key] = value;
		});
		return status;
	};
});

services.factory('PrepareDevice', ['Device', function(Device) {
	return function(device){
		var currentDevice = new Device();
		currentDevice.id = device.id;
        currentDevice.isIn = device.isIn;
        currentDevice.description = device.description;
        currentDevice.type = angular.lowercase(device.class);
        currentDevice.controlFunctionality = device.controlFunctionality;
        return currentDevice;
	};
}]);