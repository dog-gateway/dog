'use strict';

/* Controller for handling framework-related information */
dogUIController.controller('BundleStatsCtrl', ['$scope', 'BundleStats', '$timeout', function($scope, BundleStats, $timeout) {
    //init
	$scope.data = {};
    $scope.data.bundles = {};
    $scope.data.bundles.stats = {};
    
    //init polling times and related variables
    var defaultPollingTime = 2500;
    var bundlePollingTime = defaultPollingTime;
    var maxPollingTime = 10000;
    var changed = true;
    var cancelDevPoll, cancelBundlePoll;
    
    // a promise is needed to avoid the flickering during the value updates
    // get the bundles statistics
    var bundleStats = BundleStats.get(function(){
    	$scope.data.bundles.stats = bundleStats;
    });
    
    // bundle stats poller
    var bundlePoller = function(bundlePollingTime) {
      var prevActiveBundles = $scope.data.bundles.stats.active;
      cancelBundlePoll = $timeout(function() {
        // get the bundles statistics
        var bundleStats = BundleStats.get(function(){
          $scope.data.bundles.stats = bundleStats;
          if((prevActiveBundles!=null) && (prevActiveBundles != bundleStats.active))
        	  $scope.$emit('bundlesUpdate');
        });
        
        if(prevActiveBundles == $scope.data.bundles.stats.active) {
            if(bundlePollingTime < maxPollingTime) 
            	bundlePollingTime += 1000;
          }
          else if(bundlePollingTime != defaultPollingTime)
        	  bundlePollingTime = defaultPollingTime;
        
        bundlePoller(bundlePollingTime);
      }, bundlePollingTime);
    };
    
    // first poller exec
    bundlePoller(bundlePollingTime);

    // stop the poller when changing view
    $scope.$on('$destroy', function(event) {
      $timeout.cancel(cancelBundlePoll);
    });
}]);