'use strict';

/* Controller for handling framework-related information */
dogUIController.controller('FrameworkCtrl', ['$scope', 'RuntimeMemory', 'FreeMemory', 'UsedMemory', '$timeout', function($scope, RuntimeMemory, FreeMemory, UsedMemory, $timeout) {
    //init
	$scope.data = {};
    $scope.data.memory = {};
    $scope.data.memory.total = {};
    $scope.data.memory.free = {};
    $scope.data.memory.used = {};
    
    //set polling-related variables for this controller
    var pollingTime = 3000;
    var cancelPoll;
    
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
    
    // poller: each "pollingTime" milliseconds, ask for updates
    var poll = function() {
    	cancelPoll = $timeout(function() {
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
        poll();
      }, pollingTime);
    };     
   poll();
   
   // stop the poller when changing view
   $scope.$on('$destroy', function(event) {
       $timeout.cancel(cancelPoll);
   });
}]);