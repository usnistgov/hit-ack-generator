'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:IntegrationcontrollerCtrl
 * @description
 * # IntegrationcontrollerCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
  .controller('IntegrationcontrollerCtrl',function (matrixHandler,$scope, $rootScope, $http) {
	$scope.mtx = [];
	$scope.headers = [];
	$scope.cotrls = {
			clear : matrixHandler.clearCategory,
			aaa : "fff"
	};
	
	var ctrl = this;
	var message = "";
	var active = 0;
	
	this.setActive = function(i){
		active = i;
	};
	
	this.isActive = function(i){
		return active === i;
	};
	
	
	$scope.validate = function(){

		ctrl.message = $scope.editor1.getValue("\n");
		$scope.matrixS = {
				message : {
					id : "1", 
					desc : "desc", 
					children : $scope.mtx
				},
				header : $scope.headers
		};
		console.log(this.message);
		$http.post('/ack-webapp/api/validate',{ matrix : $scope.matrixS, message : ctrl.message}, {}).then(function(data){
			
			$scope.rsp = data.data.content;
			$scope.report = data.data.report;
			$scope.editor2.setValue($scope.rsp);
			console.log(data.data.content);
		
		});
	};
	
    this.initCodemirror = function () {
        $scope.editor1 = CodeMirror.fromTextArea(document.getElementById("cfTextArea1"), {
            lineNumbers: true,
            fixedGutter: true,
            theme: "elegant",
            readOnly: false,
            mode: "hl7v2",
            showCursorWhenSelecting: true,
            gutters: ["CodeMirror-linenumbers", "cm-edi-segment-name"]
        });
        
        $scope.editor2 = CodeMirror.fromTextArea(document.getElementById("cfTextArea2"), {
            lineNumbers: true,
            fixedGutter: true,
            theme: "elegant",
            readOnly: false,
            mode: "hl7v2",
            showCursorWhenSelecting: true,
            gutters: ["CodeMirror-linenumbers", "cm-edi-segment-name"]
        });
    };
        
    matrixHandler.getMatrix().then(function(data){
    	$scope.mtx = data.message.children;
    	$scope.headers = data.header;
    });
    
    
  });
