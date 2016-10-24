'use strict';

angular.module('webappApp').directive('matrixEditor', function() {
	return {
		templateUrl : 'me',
		restrict : 'E',
		scope	 : {
			matrix  : "=",
			headers : "="
		},
		bindToController: true,
		controllerAs: 'meCtrl',
		controller : function($scope,ngTreetableParams, matrixHandler) {
					
			var ctrl = this;
			
			this.clear = matrixHandler.clearCategory;
			
			this.getValue = function(node, head) {
				if (node.indexes && node.indexes.hasOwnProperty(head)) {
					return node.indexes[head];
				} else {
					return "";
				}
			};

			this.setToWPath = function(path,category, code, matrix){
				for(var i in matrix) {
    				
    				var elm = matrix[i];
    				
    				if(elm.hasOwnProperty("path") && elm.path === path){
    					if(elm.hasOwnProperty("indexes")){
            				if(elm.indexes.hasOwnProperty(category) && elm.indexes[category] !== -1 ){
            					elm.indexes[category] = code;
            				}
            			}
    					return;
    				}
    				
    				
        			if(elm.hasOwnProperty("children") && elm.children && elm.children.length > 0) {
        				ctrl.setToWPath(path, category, code, elm.children);
        			}
				}
    		};
    		
			this.setToWUsage = function(usage,category, code, matrix){
				for(var i in matrix) {
    				
    				var elm = matrix[i];
    				
    				if(elm.hasOwnProperty("usage") && elm.usage === usage){
    					if(elm.hasOwnProperty("indexes")){
            				if(elm.indexes.hasOwnProperty(category) && elm.indexes[category] !== -1 ){
            					elm.indexes[category] = code;
            				}
            			}
    				}
    				
        			if(elm.hasOwnProperty("children") && elm.children && elm.children.length > 0) {
        				ctrl.setToWUsage(usage, category, code, elm.children);
        			}
				}
    		};
    		
    		this.edit = function(){
    			if(ctrl.a === "usage"){
    				console.log("using U");
    				console.log(ctrl.usageS);
    				console.log(ctrl.categ);
    				console.log(ctrl.setTo);
    				ctrl.setToWUsage(ctrl.usageS,ctrl.categ,ctrl.setTo, ctrl.matrix);
    			}
    			else if(ctrl.a === "path"){
    				console.log("using P");
    				console.log(ctrl.path);
    				console.log(ctrl.categ);
    				console.log(ctrl.setTo);
    				ctrl.setToWPath(ctrl.path,ctrl.categ,ctrl.setTo, ctrl.matrix);
    			}
    		};
			
			this.dynamic_params = new ngTreetableParams({
				
				getNodes : function(parent) {
					if (parent && parent.children) {
						return parent.children;
					} else {
						return ctrl.matrix;
					}
				},
				getTemplate : function() {
					return 'tree_node';
				}
			});

			this.isExpandable = function(node) {
				if (node && node.hasOwnProperty("children") && node.children) {
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
					function(){
						ctrl.dynamic_params.refresh();
					}
			);
		}
	};
});