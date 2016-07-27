'use strict';

/**
 * @ngdoc service
 * @name webappApp.matrixHandler
 * @description
 * # matrixHandler
 * Service in the webappApp.
 */
angular.module('webappApp')
  .service('matrixHandler', function ($q,$http) {
    var instance = {
    		getMatrix : function (){
    			var deferred = $q.defer();
            	$http.get("/matrix").success(function(data){
            		deferred.resolve(data);
                });
            	return deferred.promise;
    		},
    		clearCategory : function(model, category){
    			for(var i in model) {
    				
    				var elm = model[i];
    				
    				if(elm.hasOwnProperty("indexes")){
        				if(elm.indexes.hasOwnProperty(category) && elm.indexes[category] != -1 ){
        					elm.indexes[category] = 0;
        				}
        			}
    				
        			if(elm.hasOwnProperty("children") && elm.children.length > 0) {
        				instance.clearCategory(elm.children,category);
        			}
				}
    		}
    };
    return instance;
});
