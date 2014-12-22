'use strict';

/* Controller for handling bundle-related information */
dogUIController.controller('BundleCtrl', ['$scope', 'Bundle', '$timeout', function($scope, Bundle, $timeout) {
	//init
	$scope.data = {};
	$scope.data.bundles = {};
	$scope.data.available = {};
	$scope.data.available.bundles = null;
	
	// get the bundle list from the Dog APIs
	var bundleList = Bundle.query(function(){
	  var len = bundleList.length;
	  $scope.data.bundles['second'] = bundleList.splice(len/2, len);
	  $scope.data.bundles['first'] = bundleList;
	  $scope.data.available.bundles = true;
	});
	
	$scope.$on('bundlesUpdate', function (event, args) {
	  event.stopPropagation();
	  var bundleList = Bundle.query(function(){
	    var len = bundleList.length;
	    $scope.data.bundles['second'] = bundleList.splice(len/2, len);
	    $scope.data.bundles['first'] = bundleList;
	  });
	});
	
 }]);