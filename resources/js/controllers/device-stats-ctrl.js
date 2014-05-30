'use strict';

/* Controller for handling framework-related information */
dogUIController.controller('DeviceStatsCtrl', ['$scope', 'DeviceStats', '$timeout', function($scope, DeviceStats, $timeout) {
    //init
	$scope.data = {};
    $scope.data.devices = {};
    $scope.data.devices.stats = {};
    
    //init polling time and related variables
    var pollingTime = 2500;
    var cancelDevPoll;
    
    // a promise is needed to avoid the flickering during the value updates
    // get the devices statistics
    var devStats = DeviceStats.get(function(){
    	$scope.data.devices.stats = devStats;
    });
    
    // device stats poller
    var devPoller = function() {
      var previousDevNumber = $scope.data.devices.stats.active;
      cancelDevPoll = $timeout(function() {
        // get the devices statistics
        var devStats = DeviceStats.get(function(){
          $scope.data.devices.stats = devStats;
          if((previousDevNumber!=null) && (previousDevNumber != devStats.active))
            $scope.$emit('devicesUpdate');
        });
        devPoller();
      }, pollingTime);
    };
    
    // first poller exec
    devPoller();

    // stop the poller when changing view
    $scope.$on('$destroy', function(event) {
      $timeout.cancel(cancelDevPoll);
    });
}]);