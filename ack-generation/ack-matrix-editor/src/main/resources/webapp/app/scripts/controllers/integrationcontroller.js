'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:IntegrationcontrollerCtrl
 * @description
 * # IntegrationcontrollerCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
  .controller('IntegrationcontrollerCtrl',function (matrixHandler,$scope, $rootScope) {
	$scope.mtx = [];
	$scope.headers = [];
	$scope.ctrls = {
			clear : matrixHandler.clearCategory,
			aaa : "fff"
	}
    matrixHandler.getMatrix().then(function(data){
    	$scope.mtx = data.message.children;
    	$scope.headers = data.header;
    });
    
    
  });
