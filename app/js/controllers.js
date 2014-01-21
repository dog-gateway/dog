'use strict';

/* Controllers */

angular.module('dogUI.controllers', [])
  .controller('firstCtrl', ['$scope', function($scope) {
	  /*$scope.singleModel = 1;*/
  }])
  .controller('MyCtrl2', [function() {

  }])
  .controller('DeviceListCtrl', ['$scope', 'Device', 'DeviceStatus', '$timeout', function($scope, Device, DeviceStatus, $timeout) {
	  $scope.data = {};
	  $scope.data.devices = {};
	  
	  var devicesList = Device.get(function() {
        for(var num in devicesList.devices) {
          var currentDevice = new Device();
          currentDevice.id = devicesList.devices[num].id;
          currentDevice.isIn = devicesList.devices[num].isIn;
          currentDevice.description = devicesList.devices[num].description;
          currentDevice.controlFunctionality = devicesList.devices[num].controlFunctionality;
          $scope.data.devices[devicesList.devices[num].id] = currentDevice;
      }
      
	});
    
    var poll = function() {
        $timeout(function() {
            var deviceStatus = DeviceStatus.get(function(){
              for(var num in deviceStatus.devices) {
                $scope.data.devices[deviceStatus.devices[num].id].status = deviceStatus.devices[num].status;
              }
            });
            poll();
        }, 1000);
    };     
   poll();
  }]);
