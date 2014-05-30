'use strict';

/* Controller for handling framework-related information */
dogUIController.controller('FrameworkCtrl', ['$scope', 'RuntimeMemory', 'FreeMemory', 'UsedMemory', 'BundleStats', 'DeviceStats', '$timeout', function($scope, RuntimeMemory, FreeMemory, UsedMemory, BundleStats, DeviceStats, $timeout) {
    //init
	$scope.data = {};
    $scope.data.memory = {};
    $scope.data.memory.total = {};
    $scope.data.memory.free = {};
    $scope.data.memory.used = {};
    $scope.data.bundles = {};
    $scope.data.bundles.stats = {};
    $scope.data.devices = {};
    $scope.data.devices.stats = {};
    
    // a promise is needed to avoid the flickering during the value updates
    // get the total memory from the Dog APIs
    var totalMemory = RuntimeMemory.get(function() {
    	$scope.data.memory.total = totalMemory;
    });
    // get the free memory from the Dog APIs
    var freeMemory = FreeMemory.get(function() {
    	$scope.data.memory.free = freeMemory;
    });
    // get the used memory from the Dog APIs
    var usedMemory = UsedMemory.get(function() {
    	$scope.data.memory.used = usedMemory;
    });
    // get the bundles statistics
    var bundleStats = BundleStats.get(function(){
    	$scope.data.bundles.stats = bundleStats;
    });
    // get the devices statistics
    var devStats = DeviceStats.get(function(){
    	$scope.data.devices.stats = devStats;
    });
    
    // poller: each 1 second, ask for updates
    var poll = function() {
      $timeout(function() {
        var totalMemory = RuntimeMemory.get(function() {
    	  $scope.data.memory.total = totalMemory;
    	});
    	// get the free memory from the Dog APIs
    	var freeMemory = FreeMemory.get(function() {
    	  $scope.data.memory.free = freeMemory;
    	});
    	// get the used memory from the Dog APIs
    	var usedMemory = UsedMemory.get(function() {
    	  $scope.data.memory.used = usedMemory;
    	});
    	// get the bundles statistics
    	var bundleStats = BundleStats.get(function(){
    	  $scope.data.bundles.stats = bundleStats;
    	});
    	// get the devices statistics
    	var devStats = DeviceStats.get(function(){
    	  $scope.data.devices.stats = devStats;
    	});
        poll();
      }, 1000);
    };     
   poll();
}]);