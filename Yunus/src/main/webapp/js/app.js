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
         when('/matrimonios/:id', {
             templateUrl: 'matrimonios.html',
             controller: 'MatrimoniosController'
          }).
          when('/bautizos/:id', {
              templateUrl: 'bautizos.html',
              controller: 'BautizosController'
           }).
           when('/confirmaciones/:id', {
               templateUrl: 'confirmaciones.html',
               controller: 'ConfirmacionesController'
           }).
           when('/reportes', {
               templateUrl: 'reportes.html',
               controller: 'ReportesController'
           }).
           when('/ministros', {
               templateUrl: 'ministros.html',
               controller: 'MinistrosController'
           }).
           when('/configuracion', {
               templateUrl: 'configuracion.html',
               controller: 'ConfiguracionController'
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
		    	//$httpProvider.defaults.headers.post = { 'Content-Type' : 'application/json' }
}]);

mainApp.factory('ServicesFactory', [ '$rootScope','$http', function($rootScope,$http) {

   	var dataFactory = {};

   	dataFactory.guardarMatrimonio = function(matrimonio){
   	 	return $http.post($rootScope.urlServices+'/partidas/matrimonio',matrimonio,{responseType: 'arraybuffer'});
   	},
   	dataFactory.guardarBautizo = function(bautizo){
   	 	return $http.post($rootScope.urlServices+'/partidas/bautizo',bautizo,{responseType: 'arraybuffer'});
   	},
   	dataFactory.guardarConfirmacion = function(confirmacion){
   	 	return $http.post($rootScope.urlServices+'/partidas/confirmacion',confirmacion,{responseType: 'arraybuffer'});
   	},
   	dataFactory.listarMinistrosActivos= function(){
   	 	return $http.get($rootScope.urlServices+'/ministros/activos');
   	},
   	dataFactory.buscarPartidas= function(filter){
   	 	return $http.post($rootScope.urlServices+'/reportes/buscar',filter);
   	},
   	dataFactory.getMinistros= function(){
   	 	return $http.get($rootScope.urlServices+'/ministros');
   	},
   	dataFactory.grabarMinistro= function(ministro){
   	 	return $http.post($rootScope.urlServices+'/ministros',ministro);
   	},
   	dataFactory.actualizarMinistro= function(ministro){
   	 	return $http.put($rootScope.urlServices+'/ministros',ministro);
   	},
   	dataFactory.getConfiguracion= function(ministro){
   	 	return $http.get($rootScope.urlServices+'/parroquia');
   	},
   	dataFactory.actualizarConfiguracion= function(parroquia){
   	 	return $http.put($rootScope.urlServices+'/parroquia',parroquia);
   	},
   	
   	dataFactory.recuperarPartida = function(id){
   		return $http.get($rootScope.urlServices+'/partida/'+id);
   	}
   	dataFactory.descargarPartida = function(sacaramento,id){
	   		$http.get($rootScope.urlServices+'/reportes/'+sacaramento+'/'+id, {responseType: 'arraybuffer'})
	        .success(function (data) {
	            var file = new Blob([data], {type: 'application/pdf'});
	            var fileURL = URL.createObjectURL(file);
	           
	            var a = document.createElement("a");
	            document.body.appendChild(a);
	            a.style = "display: none";
	            
	            a.href = fileURL;
	            a.download = 'Partida '+sacaramento;
	            a.click();
	            window.open(fileURL);
	            window.URL.revokeObjectURL(fileURL);
	     })};
	  dataFactory.descargarPartidaPDF = function(data){
		            var file = new Blob([data], {type: 'application/pdf'});
		            var fileURL = URL.createObjectURL(file);
		           
		            var a = document.createElement("a");
		            document.body.appendChild(a);
		            a.style = "display: none";
		            
		            a.href = fileURL;
		            a.download = 'Partida ';
		            a.click();
		            window.open(fileURL);
		            window.URL.revokeObjectURL(fileURL);
		     };
	  
	
   	
   	return dataFactory;
}]);
mainApp.controller('HomeController', ['$route', '$routeParams', '$location','$scope','ServicesFactory',
        function($route, $routeParams, $location,$scope,FacturaFactory) {
	
   }]);
