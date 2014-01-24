'use strict';

/* Controllers */

angular.module('dogUI.controllers', [])
  /* Navbar Controller: set as active the current view */
  .controller('HeaderController', ['$scope', '$location', function($scope, $location) {
    $scope.isActive = function (viewLocation) { 
      return viewLocation === $location.path();
	  }
  }])
  /* Controller for handling devices-related information */
  .controller('DeviceCtrl', ['$scope', 'Device', 'DeviceStatus', 'DeviceCmd', '$timeout', function($scope, Device, DeviceStatus, DeviceCmd, $timeout) {
	  // init
	  $scope.data = {};
	  $scope.data.devices = {};
	  
	  // get the devices list from the Dog APIs, and elaborate it in a promise
	  var devicesList = Device.get(function() {
        for(var num in devicesList.devices) {
          // take only the needed information
          var currentDevice = new Device();
          currentDevice.id = devicesList.devices[num].id;
          currentDevice.isIn = devicesList.devices[num].isIn;
          currentDevice.description = devicesList.devices[num].description;
          currentDevice.type = angular.lowercase(devicesList.devices[num].class);
          currentDevice.controlFunctionality = devicesList.devices[num].controlFunctionality;
          $scope.data.devices[devicesList.devices[num].id] = currentDevice;
      }
	});
    
	// status poller: each 1 second, ask for devices status update
    var poll = function() {
      $timeout(function() {
        var deviceStatus = DeviceStatus.get(function(){
          for(var num in deviceStatus.devicesStatus) {
        	// associate the right device status with the already known device information
            $scope.data.devices[deviceStatus.devicesStatus[num].id].status = deviceStatus.devicesStatus[num].status;
          }
        });
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
   }
  }]);
