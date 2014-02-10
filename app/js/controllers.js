'use strict';

/* Controllers */

angular.module('dogUI.controllers', [])
  /* Navbar Controller: set as active the current view */
  .controller('NavController', ['$scope', '$location', function($scope, $location) {
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
        
      //set the initial status... a promise inside a promise...
      var deviceStatus = DeviceStatus.get(function(){
        for(var num in deviceStatus.devicesStatus) {
          var status = {};
          // remove 'State' and 'MeasurementState' from the status or rename 'OnOffState'
          angular.forEach(deviceStatus.devicesStatus[num].status, function(value, key)
          {
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
        	 
         	 status[key]=value;
          });          
          // associate the right device status with the already known device information
          $scope.data.devices[deviceStatus.devicesStatus[num].id].status = status;
        }
      });
	});
    
	// status poller: each 1 second, ask for devices status update
    var poll = function() {
      $timeout(function() {
        var deviceStatus = DeviceStatus.get(function(){
          for(var num in deviceStatus.devicesStatus) {
        	 var status = {};
             // remove 'State' and 'MeasurementState' from the status or rename 'OnOffState'
             angular.forEach(deviceStatus.devicesStatus[num].status, function(value, key)
             {
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
            	 
             	status[key]=value;
             });
        	// associate the right device status with the already known device information
            $scope.data.devices[deviceStatus.devicesStatus[num].id].status = status;
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
  }])
   /* Controller for handling bundle-related information */
  .controller('BundleCtrl', ['$scope', 'Bundle', '$timeout', function($scope, Bundle, $timeout) {
	//init
	$scope.data = {};
	$scope.data.bundles = {};
	
	// get the bundle list from the Dog APIs
	var bundleList = Bundle.query(function(){
	  var len = bundleList.length;
	  $scope.data.bundles['second'] = bundleList.splice(len/2, len);
	  $scope.data.bundles['first'] = bundleList;
	});
	
	// poller: each 1 second, ask for bundle list update
    var poll = function() {
      $timeout(function() {
    	// get the bundle list from the Dog APIs
        var bundleList = Bundle.query(function(){
    	  var len = bundleList.length;
    	  $scope.data.bundles['second'] = bundleList.splice(len/2, len);
    	  $scope.data.bundles['first'] = bundleList;
    	});
        poll();
      }, 1000);
    };     
   poll();
  }])
    /* Controller for handling framework-related information */
  .controller('FrameworkCtrl', ['$scope', 'RuntimeMemory', 'FreeMemory', 'UsedMemory', '$timeout', function($scope, RuntimeMemory, FreeMemory, UsedMemory, $timeout) {
    //init
	$scope.data = {};
    $scope.data.memory = {};
    $scope.data.memory.total = {};
    $scope.data.memory.free = {};
    $scope.data.memory.used = {};
    
    // get the total memory from the Dog APIs
    $scope.data.memory.total = RuntimeMemory.get();
    // get the free memory from the Dog APIs
    $scope.data.memory.free = FreeMemory.get();
    // get the used memory from the Dog APIs
    $scope.data.memory.used = UsedMemory.get();
    
    // poller: each 1 second, ask for bundle list update
    var poll = function() {
      $timeout(function() {
    	// get the total memory from the Dog APIs
    	$scope.data.memory.total = RuntimeMemory.get();
    	// get the free memory from the Dog APIs
    	$scope.data.memory.free = FreeMemory.get();
    	// get the used memory from the Dog APIs
    	$scope.data.memory.used = UsedMemory.get();
        poll();
      }, 1000);
    };     
   poll();
}]);