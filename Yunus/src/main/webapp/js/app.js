var controllers = angular.module('mainApp.controllers', ['ui.bootstrap']);
var services = angular.module('mainApp.services', ['ngResource']);
var mainApp = angular.module("mainApp", ['ngRoute','mainApp.controllers','angular-loading-bar','ui.bootstrap']);

function NavBarCtrl($scope) {
    $scope.navbarCollapsed = true;
}

mainApp.config(['$routeProvider', '$httpProvider', 'cfpLoadingBarProvider',
   function($routeProvider,$httpProvider,cfpLoadingBarProvider) {

	  cfpLoadingBarProvider.includeBar = false;
      $routeProvider.
         when('/home', {
            templateUrl: 'home.html',
            controller: 'HomeController'
         }).
         when('/matrimonios', {
             templateUrl: 'matrimonios.html',
             controller: 'MatrimoniosController'
          }).
          when('/bautizos', {
              templateUrl: 'bautizos.html',
              controller: 'BautizosController'
           }).
           when('/confirmaciones', {
               templateUrl: 'confirmaciones.html',
               controller: 'ConfirmacionesController'
           }).
           when('/reportes', {
               templateUrl: 'reportes.html',
               controller: 'ReportesController'
           }).
         otherwise({
            redirectTo: '/home'
         });

      $httpProvider.interceptors.push(function($q, $rootScope, $location) {

		    return {
			    'request' : function(config) {
				    return config || $q.when(config);
			    }
		    };
	    });

   }]);

mainApp.run(
		  [
		    "$rootScope",
		    function($rootScope) {
		    	$rootScope.urlServices = 'http://localhost:8181/app';
}]);

mainApp.factory('ServicesFactory', [ '$rootScope','$http', function($rootScope,$http) {

   	var dataFactory = {};

   	dataFactory.guardarMatrimonio = function(matrimonio){
   	 	return $http.put($rootScope.urlServices+'/partidas/matrimonio',matrimonio);
   	},
   	dataFactory.guardarBautizo = function(bautizo){
   	 	return $http.put($rootScope.urlServices+'/partidas/bautizo',bautizo);
   	},
   	dataFactory.guardarConfirmacion = function(confirmacion){
   	 	return $http.put($rootScope.urlServices+'/partidas/confirmacion',confirmacion);
   	},
   	dataFactory.listarMinistrosActivos= function(){
   	 	return $http.get($rootScope.urlServices+'/ministros/activos');
   	},
   	dataFactory.buscarPartidas= function(filter){
   	 	return $http.post($rootScope.urlServices+'/reportes/buscar',filter);
   	},
   	dataFactory.descargarPartidaMatrimonio = function(id){
   		$http.get($rootScope.urlServices+'/reportes/matrimonio/'+id, {responseType: 'arraybuffer'})
        .success(function (data) {
            var file = new Blob([data], {type: 'application/pdf'});
            var fileURL = URL.createObjectURL(file);
           
            var a = document.createElement("a");
            document.body.appendChild(a);
            a.style = "display: none";
            
            a.href = fileURL;
            a.download = 'Partida matrimonio';
            a.click();
            window.open(fileURL);
            window.URL.revokeObjectURL(fileURL);
     })},
   	dataFactory.descargarPartidaBautizo = function(id){
	   		$http.get($rootScope.urlServices+'/reportes/bautizo/'+id, {responseType: 'arraybuffer'})
	        .success(function (data) {
	            var file = new Blob([data], {type: 'application/pdf'});
	            var fileURL = URL.createObjectURL(file);
	           
	            var a = document.createElement("a");
	            document.body.appendChild(a);
	            a.style = "display: none";
	            
	            a.href = fileURL;
	            a.download = 'Partida bautizo';
	            a.click();
	            window.open(fileURL);
	            window.URL.revokeObjectURL(fileURL);
	     })};
   	
   	return dataFactory;
}]);
mainApp.controller('HomeController', ['$route', '$routeParams', '$location','$scope','ServicesFactory',
        function($route, $routeParams, $location,$scope,FacturaFactory) {
	
   }]);
