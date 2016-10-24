'use strict';

/**
 * @ngdoc overview
 * @name webappApp
 * @description
 * # webappApp
 *
 * Main module of the application.
 */
angular
  .module('webappApp', [
    'ngAnimate',
    'ngAria',
    'ngCookies',
    'ngMessages',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch',
//    'ngMockE2E',
    'ngTreetable',
    'ui.bootstrap'
  ])  
//  .config(function ($routeProvider) {
//    $routeProvider
//      .when('/', {
//        templateUrl: 'views/main.html',
//        controller: 'MainCtrl',
//        controllerAs: 'main'
//      })
//      .when('/about', {
//        templateUrl: 'views/about.html',
//        controller: 'AboutCtrl',
//        controllerAs: 'about'
//      })
//      .otherwise({
//        redirectTo: '/'
//      });
//  })
//  .run(function ($httpBackend) {
//	  $httpBackend.whenGET('/ack-webapp/api/matrix').respond(function() {
//		  var request = new XMLHttpRequest();
//
//		  request.open('GET', 'views/data.json', false);
//		  request.send(null);
//
//		  return [request.status, request.response, {}];
//	  });
//	  
//	  $httpBackend.whenGET('views/header.html').respond(function() {
//		  var request = new XMLHttpRequest();
//
//		  request.open('GET', 'views/header.html', false);
//		  request.send(null);
//
//		  return [request.status, request.response, {}];
//	  });
//  })
.directive('convertToNumber', function() {
	  return {
		    require: 'ngModel',
		    link: function(scope, element, attrs, ngModel) {
		      ngModel.$parsers.push(function(val) {
		        return val ? parseInt(val, 10) : null;
		      });
		      ngModel.$formatters.push(function(val) {
		        return val ? '' + val : null;
		      });
		    }
		  };
}).filter("sanitize", ['$sce', function($sce) {
	  return function(htmlCode){
		    return $sce.trustAsHtml(htmlCode);
		  };
}]);
