var controllers = angular.module('mainApp.controllers', ['ui.bootstrap']);
var services = angular.module('mainApp.services', ['ngResource']);
var mainApp = angular.module("mainApp", ['ngRoute','mainApp.controllers','angular-loading-bar','ui.bootstrap','ngCookies']);

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
           when('/abonos', {
               templateUrl: 'creditos/abonos.html',
               controller: 'AbonosController'
           }).
           when('/reportecreditos', {
               templateUrl: 'creditos/reporteCreditos.html',
               controller: 'ReporteCreditosController'
           }).
           when('/login', {
               templateUrl: 'login.html',
               controller: 'LoginController'
           }).
           when('/aporte', {
               templateUrl: 'creditos/aportes.html',
               controller: 'AportesController'
           }).
           when('/reporteaportes', {
               templateUrl: 'creditos/reporteAportes.html',
               controller: 'ReporteAportesController'
           }).
           when('/morosos', {
               templateUrl: 'creditos/reporteMorosos.html',
               controller: 'ReporteMorososController'
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
		    "$rootScope",'$cookies','$location',
		    function($rootScope,$cookies,$location) {
		    	$rootScope.urlServices = 'http://localhost:8181/app';		    	
		    	 $rootScope.$on('$routeChangeStart', function(event, currRoute, prevRoute){
		    		 var logged = false;
		    		
		    	        if(window.localStorage.getItem("yunus") == undefined) {
		    	        	$location.url('/login');
		    	        }else{
		    	        	resultado=(((Date.now()-JSON.parse(window.localStorage.getItem("yunus")).date)));
		    	        	resultado = (resultado/(1000*60))%60
		    	        	if(resultado > 30){
		    	        		$location.url('/login');
		    	        	}
		    	        }
		    	    });
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
   	dataFactory.descargarClientes = function(){
   	 	$http.get($rootScope.urlServices+'/cliente',{responseType: 'arraybuffer'}).then(function(data) {
	   	 	var blob = new Blob([data.data], {type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"});
			var objectUrl = URL.createObjectURL(blob);
			var a = document.createElement("a");
	        document.body.appendChild(a);
	        a.style = "display: none";
	        a.href = objectUrl;
	        a.download = 'Reporte Clientes';
	        a.click();		    
	        a.remove();
		  }, function errorCallback(response) {
			    alert("Error generando informe");
		  });;
   	}
   	
   	dataFactory.guardarVinculacion = function(vinculacion,user){
   		return $http.post($rootScope.urlServices+'/vinculacion/'+user,vinculacion,{responseType: 'arraybuffer'});
   	}
   	dataFactory.simularCredito = function(request){
   		return $http.post($rootScope.urlServices+'/simulador',request);
   	}
   	dataFactory.imprimirSimulacion = function(request){
   		return $http.post($rootScope.urlServices+'/simulador/imprimir',request,{responseType: 'arraybuffer'}).then(function(data) {
   			dataFactory.imprimirReporteAfiliacion(data.data);
		  }, function errorCallback(response) {
			    alert("Error generando informe");
		  });
   	}
	dataFactory.generarCredito = function(request){
   		return $http.post($rootScope.urlServices+'/creditos',request,{responseType: 'arraybuffer'});
   	}
   	dataFactory.actualizarMaestro = function(maestro){
   	 	return $http.put($rootScope.urlServices+'/maestros',maestro);
   	}
   	dataFactory.liquidarCuotas = function(cuotas){
   		return $http.post($rootScope.urlServices+'/creditos/liquidar',cuotas,{responseType: 'arraybuffer'});
   	}
   	dataFactory.buscarVinculaciones = function(filter){
   		return $http.get($rootScope.urlServices+'/vinculacion/buscar/'+filter.documento);
   	}
   	dataFactory.recuperarVinculacionById = function(id){
   		return $http.get($rootScope.urlServices+'/vinculacion/id/'+id);
   	}
   	dataFactory.actualizarVinculacion = function(vinculacion,user){
   	 	return $http.post($rootScope.urlServices+'/vinculacion/actualizar/'+user,vinculacion,{responseType: 'arraybuffer'});
   	}
   	dataFactory.buscarCreditosByCliente = function(cedula){
   		return $http.get($rootScope.urlServices+'/creditos/findByCliente/'+cedula);
   	}
   	dataFactory.buscarAportesByCliente = function(cedula){
   		return $http.get($rootScope.urlServices+'/creditos/aportes/findByCliente/'+cedula);
   	}
   	
	dataFactory.liquidarAporte = function(request){
   		return $http.post($rootScope.urlServices+'/creditos/aporte',request,{responseType: 'arraybuffer'});
   	}
	
	dataFactory.buscarTotales = function(feini,fefin){
   		return $http.get($rootScope.urlServices+'/creditos/sum/'+feini+"/"+fefin);
   	}
	dataFactory.buscarTotalesAportes = function(feini,fefin){
   		return $http.get($rootScope.urlServices+'/creditos/aporte/sum/'+feini+"/"+fefin);
   	}
	dataFactory.login = function(obj){
   		return $http.post($rootScope.urlServices+'/seguridad/login',obj);
   	}
	dataFactory.deshacerAporte= function(id){
		return $http.delete($rootScope.urlServices+'/creditos/aporte/'+id);
	}
	dataFactory.generarReporteCliente = function (documento) {
		return $http.get($rootScope.urlServices+'/creditos/reporte/'+documento,{responseType: 'arraybuffer'}).then(function(data) {
			var blob = new Blob([data.data], {type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"});
			var objectUrl = URL.createObjectURL(blob);
			var a = document.createElement("a");
	        document.body.appendChild(a);
	        a.style = "display: none";
	        a.href = objectUrl;
	        a.download = 'Reporte Creditos '+documento;
	        a.click();		    
	        a.remove();
		  }, function errorCallback(response) {
			    alert("Error generando informe");
		  });
    }
	dataFactory.generarReporteAportesFechas = function (feini,fefin) {
		return $http.get($rootScope.urlServices+'/creditos/aportes/reporte/'+feini+"/"+fefin,{responseType: 'arraybuffer'}).then(function(data) {
			var blob = new Blob([data.data], {type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"});
			var objectUrl = URL.createObjectURL(blob);
			var a = document.createElement("a");
	        document.body.appendChild(a);
	        a.style = "display: none";
	        a.href = objectUrl;
	        a.download = 'Reporte total aportes';
	        a.click();		    
	        a.remove();
		  }, function errorCallback(response) {
			    alert("Error generando informe");
		  });
    }
	dataFactory.generarReporteAportesCliente = function (documento) {
		return $http.get($rootScope.urlServices+'/creditos/aportes/reporte/'+documento,{responseType: 'arraybuffer'}).then(function(data) {	        
			var blob = new Blob([data.data], {type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"});
			var objectUrl = URL.createObjectURL(blob);
			var a = document.createElement("a");
	        document.body.appendChild(a);
	        a.style = "display: none";
	        a.href = objectUrl;
	        a.download = 'Reporte Aportes '+documento;
	        a.click();		    
	        a.remove();
		    
		  }, function errorCallback(response) {
			    alert("Error generando informe");
		  });
    }
   	dataFactory.descargarVinculacion = function(id,user){
   		return $http.get($rootScope.urlServices+'/vinculacion/'+id+'/'+user, {responseType: 'arraybuffer'}).then(function(data) {
   			dataFactory.imprimirReporteAfiliacion(data.data);
		  }, function errorCallback(response) {
			    alert("Error generando informe");
		  });
   	}
   	
   	dataFactory.generarReporteMorosos = function(feini,fefin){
   		return $http.get($rootScope.urlServices+'/creditos/morosos/'+feini+"/"+fefin,{responseType: 'arraybuffer'}).then(function(data) {
			var blob = new Blob([data.data], {type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"});
			var objectUrl = URL.createObjectURL(blob);
			var a = document.createElement("a");
	        document.body.appendChild(a);
	        a.style = "display: none";
	        a.href = objectUrl;
	        a.download = 'Reporte Morosos';
	        a.click();		    
	        a.remove();
		  }, function errorCallback(response) {
			    alert("Error generando informe");
		  });
   	}
   	dataFactory.generarReporteMorososCliente = function(documento,cliente){
   		return $http.get($rootScope.urlServices+'/creditos/morosos/'+documento,{responseType: 'arraybuffer'}).then(function(data) {
			var blob = new Blob([data.data], {type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"});
			var objectUrl = URL.createObjectURL(blob);
			var a = document.createElement("a");
	        document.body.appendChild(a);
	        a.style = "display: none";
	        a.href = objectUrl;
	        a.download = 'Reporte Mora '+cliente;
	        a.click();		    
	        a.remove();
		  }, function errorCallback(response) {
			    alert("Error generando informe");
		  });
   	}

   	dataFactory.imprimirReporteAporte = function(data){
   		var file = new Blob([data], {type: 'application/pdf'});
        var fileURL = URL.createObjectURL(file);
       
        var a = document.createElement("a");
        document.body.appendChild(a);
        a.style = "display: none";
        
        a.href = fileURL;
        a.download = 'Aporte';
        a.click();
        window.open(fileURL);
        window.URL.revokeObjectURL(fileURL);
   	}
   	dataFactory.imprimirReporteAbonos = function(data){
   		var file = new Blob([data], {type: 'application/pdf'});
        var fileURL = URL.createObjectURL(file);
       
        var a = document.createElement("a");
        document.body.appendChild(a);
        a.style = "display: none";
        
        a.href = fileURL;
        a.download = 'Abonos';
        a.click();
        window.open(fileURL);
        window.URL.revokeObjectURL(fileURL);
   	}
   	
   	dataFactory.imprimirReporteCredito = function (data) {
   		var file = new Blob([data], {type: 'application/pdf'});
        var fileURL = URL.createObjectURL(file);
       
        var a = document.createElement("a");
        document.body.appendChild(a);
        a.style = "display: none";
        
        a.href = fileURL;
        a.download = 'Credito';
        a.click();
        window.open(fileURL);
        window.URL.revokeObjectURL(fileURL);
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