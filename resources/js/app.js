'use strict';

// Declare app level module which depends on filters, and services
angular.module('dogUI', [
  'ngRoute',
  'ui.bootstrap',
  'dogUI.filters',
  'dogUI.services',
  'dogUI.directives',
  'dogUI.controllers'
]).
/* Handle app routes */
config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/home', {templateUrl: 'partials/overview.html'});
  $routeProvider.when('/devices', {templateUrl: 'partials/devices.html', controller: 'DeviceCtrl'});
  $routeProvider.when('/components', {templateUrl: 'partials/components.html'});
  $routeProvider.otherwise({redirectTo: '/home'});
}]);
