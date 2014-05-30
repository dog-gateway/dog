'use strict';

/* Controller for handling devices-related information */
dogUIController.controller('DeviceCtrl', ['$scope', 'Device', 'DeviceStatus', 'DeviceCmd', '$timeout', 'CleanStatus', 'PrepareDevice', function($scope, Device, DeviceStatus, DeviceCmd, $timeout, CleanStatus, PrepareDevice) {
	// init
	$scope.data = {};
	$scope.data.devices = {};
	$scope.data.available = {};
	$scope.data.available.devices = null;
	
	var cancelPoll;
	  
	/** define some functions **/
	var getStatus = function() {
	  //set the initial status... a promise inside a promise...
	  var deviceStatus = DeviceStatus.get(function(){
	    for(var num in deviceStatus.devicesStatus) {
	      if($scope.data.devices[deviceStatus.devicesStatus[num].id]) {
	        // associate the active/idle information to the proper device
	        $scope.data.devices[deviceStatus.devicesStatus[num].id].active = deviceStatus.devicesStatus[num].active;
	      
	        // remove 'State' and 'MeasurementState' from the status or rename 'OnOffState'
	        // associate the right device status with the already known device information
	        $scope.data.devices[deviceStatus.devicesStatus[num].id].status = CleanStatus(deviceStatus.devicesStatus[num].status);
	      }
	    }
	  });
	};
	  
	var getDevices = function(){
	  // get the devices list from the Dog APIs, and elaborate it in a promise
	  var devicesList = Device.get(function() {
        for(var num in devicesList.devices) {
          // take only the needed information
          var currentDevice = PrepareDevice(devicesList.devices[num]);
          $scope.data.devices[devicesList.devices[num].id] = currentDevice;
          // assume all devices active
          $scope.data.devices[devicesList.devices[num].id].active = true;
          $scope.data.available.devices = true;
        }
        
        // get the device status
        getStatus();
      });
	};
	/** end function declaration **/
	
	getDevices();

	$scope.$on('devicesUpdate', function (event, args) {
      // stop the event propagation
      event.stopPropagation();
      
      getDevices();
    });
	  
	// status poller: each second, ask for devices status update
    var poll = function() {
      cancelPoll = $timeout(function() {
        getStatus();
        poll();
      }, 1000);
    };     
   poll();
   
   // send command
   $scope.sendCommand = function(deviceId, commandName) {
	 // optional parameters should go as field of devCmd (e.g., devCmd.value=param)
	 // TODO Add optional command params
	 var devCmd = new DeviceCmd();
	 devCmd.$update({id: deviceId, command: commandName});
   };
   
   // stop the poller when changing view
   $scope.$on('$destroy', function(event) {
     $timeout.cancel(cancelPoll);
   });

}]);