'use strict';

/* Controllers */

angular.module('dogUI.controllers', [])
  .controller('firstCtrl', ['$scope', function($scope) {
	  /*$scope.singleModel = 1;*/
  }])
  .controller('MyCtrl2', [function() {

  }])
  .controller('DeviceListCtrl', ['$scope', 'Device', 'DeviceStatus', '$timeout', function($scope, Device, DeviceStatus, $timeout) {
    var devicesList = Device.get(function() {
	  $scope.devices = devicesList.devices;
	});
    
    var poll = function() {
        $timeout(function() {
            $scope.deviceStatus = DeviceStatus.get();
            poll();
        }, 5000);
    };     
   poll();
  }]);
