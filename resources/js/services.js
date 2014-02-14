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
      get: {method:'GET', headers: {'Accept':'application/json'}, cache:true}
    });
  }]);

services.factory('DeviceCmd', ['$resource', function($resource){
return $resource('/api/devices/:id/commands/:command', {id:"@id", command: "@command"}, {
    update: {method:'PUT'}
  });
}]);

services.factory('DeviceStatus', ['$resource', function($resource){
    return $resource('/api/devices/status', {}, {
      get: {method:'GET', headers: {'Accept':'application/json'}}
    });
  }]);

services.factory('Bundle', ['$resource', function($resource){
    return $resource('/admin/system/bundlemanager/bundles', {}, {
      query: {method:'GET', headers: {'Accept':'application/json'}, isArray:true, cache:true }
    });
}]);

services.factory('BundleStats', ['$resource', function($resource){
	return $resource('/admin/system/bundlemanager/bundles/statistics', {}, {
		get: {method:'GET', headers: {'Accept':'application/json'}}
	});
}]);

services.factory('RuntimeMemory', ['$resource', function($resource){
    return $resource('/admin/framework/memory/runtime', {}, {
      get: {method:'GET', headers: {'Accept':'application/json'} }
    });
  }]);

services.factory('FreeMemory', ['$resource', function($resource){
    return $resource('/admin/framework/memory/free', {}, {
      get: {method:'GET', headers: {'Accept':'application/json'} }
    });
  }]);

services.factory('UsedMemory', ['$resource', function($resource){
    return $resource('/admin/framework/memory/used', {}, {
      get: {method:'GET', headers: {'Accept':'application/json'} }
    });
  }]);

services.factory('DeviceStats', ['$resource', function($resource){
    return $resource('/admin/framework/devices/statistics', {}, {
      get: {method:'GET', headers: {'Accept':'application/json'} }
    });
  }]);