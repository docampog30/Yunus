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
         when('/clientes', {
             templateUrl: 'creditos/clientes.html',
             controller: 'ClientesController'
          }).
          when('/vinculacion/:id?', {
              templateUrl: 'creditos/vinculacion.html',
              controller: 'VinculacionController'
           }).
           when('/simulador', {
               templateUrl: 'creditos/simulador.html',
               controller: 'SimuladorController'
           }).
           when('/formato', {
               templateUrl: 'creditos/formatos.html',
               controller: 'FormatosController'
           }).
           when('/credito', {
               templateUrl: 'creditos/generacionCredito.html',
               controller: 'CreditosController'
           }).
           when('/maestros/:id?', {
               templateUrl: 'creditos/maestro.html',
               controller: 'MaestrosController'
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

   	dataFactory.guardarCliente = function(cliente){
   	 	return $http.put($rootScope.urlServices+'/cliente',cliente);
   	},
   	dataFactory.listarMaestro = function(cdmaestro){
   	 	return $http.get($rootScope.urlServices+'/maestros/'+cdmaestro);
   	},
   	dataFactory.buscarCliente = function(documento){
   	 	return $http.get($rootScope.urlServices+'/cliente/'+documento);
   	}
   	dataFactory.guardarVinculacion = function(vinculacion){
   		return $http.post($rootScope.urlServices+'/vinculacion',vinculacion,{responseType: 'arraybuffer'});
   	}
   	dataFactory.simularCredito = function(request){
   		return $http.post($rootScope.urlServices+'/simulador',request);
   	}
	dataFactory.generarCredito = function(request){
   		return $http.post($rootScope.urlServices+'/creditos',request);
   	}
   	dataFactory.actualizarMaestro = function(maestro){
   	 	return $http.put($rootScope.urlServices+'/maestros',maestro);
   	}
   	dataFactory.buscarVinculaciones = function(filter){
   		return $http.get($rootScope.urlServices+'/vinculacion/buscar/'+filter.documento);
   	}
   	dataFactory.recuperarVinculacionById = function(id){
   		return $http.get($rootScope.urlServices+'/vinculacion/id/'+id);
   	}
   	dataFactory.actualizarVinculacion = function(vinculacion){
   	 	return $http.post($rootScope.urlServices+'/vinculacion/actualizar',vinculacion,{responseType: 'arraybuffer'});
   	}
   	dataFactory.descargarVinculacion = function(id){
   		return $http.get($rootScope.urlServices+'/vinculacion/'+id, {responseType: 'arraybuffer'}).then(function(data) {
   			dataFactory.imprimirReporteAfiliacion(data.data);
		  }, function errorCallback(response) {
			    alert("Error generando informe");
		  });
   	}
   	dataFactory.imprimirReporteAfiliacion = function(data){
   		var file = new Blob([data], {type: 'application/pdf'});
        var fileURL = URL.createObjectURL(file);
       
        var a = document.createElement("a");
        document.body.appendChild(a);
        a.style = "display: none";
        
        a.href = fileURL;
        a.download = 'Vinculacion';
        a.click();
        window.open(fileURL);
        window.URL.revokeObjectURL(fileURL);
   	}
   	return dataFactory;
}]);
mainApp.controller('HomeController', ['$route', '$routeParams', '$location','$scope','ServicesFactory',
        function($route, $routeParams, $location,$scope,FacturaFactory) {
	
   }]);