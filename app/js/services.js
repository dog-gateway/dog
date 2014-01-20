'use strict';

/* Services */
/*
http://docs.angularjs.org/api/ngResource.$resource

Default ngResources are defined as

'get':    {method:'GET'},
'save':   {method:'POST'},
'query':  {method:'GET', isArray:true},
'remove': {method:'DELETE'},
'delete': {method:'DELETE'}
*/

var services = angular.module('dogUI.services', ['ngResource']);

services.factory('Device', ['$resource', function($resource){
    return $resource('/api/devices', {}, {
      get: {method:'GET', headers: {'Accept':'application/json'}}
    });
  }]);

services.factory('DeviceStatus', ['$resource', function($resource){
    return $resource('/api/devices/status', {}, {
      get: {method:'GET', headers: {'Accept':'application/json'}}
    });
  }]);