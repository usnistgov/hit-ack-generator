'use strict';

angular.module('webappApp').directive('matrixEditor', function() {
	return {
		templateUrl : 'me',
		restrict : 'E',
		scope	 : {
			matrix  : "=",
			headers : "=",
			controls   : "="
		},
		bindToController: true,
		link : function postLink(scope, element, attrs) {
			
		},
		controllerAs: 'meCtrl',
		controller : function($scope,ngTreetableParams, $http) {
					
			var ctrl = this;
			
			
			this.getValue = function(node, head) {
				if (node.indexes && node.indexes.hasOwnProperty(head)) {
					return node.indexes[head];
				} else {
					return "";
				}
			};

			this.dynamic_params = new ngTreetableParams({
				
				getNodes : function(parent) {
					if (parent) {
						return parent.children;
					} else {
						return ctrl.matrix;
					}
				},
				getTemplate : function(node) {
					return 'tree_node';
				}
			});

			this.isExpandable = function(node) {
				console.log("TEEEST");
				if (node && node.hasOwnProperty("children")) {
					return node.children.length > 0;
				} else {
					return false;
				}
			};

			this.labelClass = function(node) {
				if (node && node.hasOwnProperty("type")) {
					return "label-" + angular.lowercase(node.type);
				} else {
					return "";
				}
			};

			this.labelText = function(node) {
				if (node && node.hasOwnProperty("type")) {
					return node.type.charAt(0);
				} else {
					return "";
				}
			};

			this.cellClass = function(i) {
				return "t" + i;
			};
			
			$scope.$watch(
					
					function() {
						return ctrl.matrix;
					},
					function(newValue,oldValue){
						ctrl.dynamic_params.refresh();
					}
			);
		}
	};
});