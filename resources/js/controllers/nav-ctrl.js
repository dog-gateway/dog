'use strict';

/* Navbar Controller: set as active the current view */
dogUIController.controller('NavController', ['$scope', '$location', function($scope, $location) {
    $scope.isActive = function (viewLocation) { 
      return viewLocation === $location.path();
	  }
  }]);