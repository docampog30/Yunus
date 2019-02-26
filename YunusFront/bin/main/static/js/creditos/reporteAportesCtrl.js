controllers
  .controller('ReporteAportesController',['$scope','$rootScope','$http', 'ServicesFactory','$modal', function($scope,$rootScope,$http,ServicesFactory,$modal) {

	  $scope.init = function() {
		  $scope.aportes = null;
		  $scope.documento = null;
		  $scope.creditos = null;
		  $scope.totalPagos = 0;
		  $scope.valor = null;
	  }
	  
	  $scope.buscarTotales = function(){
		  $scope.detalles = null;
		  $scope.cliente = null;
		  $scope.aportes = null;
		  ServicesFactory.buscarTotalesAportes($scope.fechaini.getTime(),$scope.fechafin.getTime())
		  .then(function(data) {
			  $scope.valor = data.data;
			  
		  }, function errorCallback(response) {
			    alert("Ocurrio un error recuperando totales");
		  });
	  }
	  
	  $scope.buscarCliente = function(){
		  $scope.cliente = null;
		  $scope.valor = null;
			  ServicesFactory.buscarCliente($scope.documento)
			  .then(function(data) {
				  if(data.data.length > 0){
						  $scope.cliente = data.data[0];
						  $scope.buscarAportes();
				  }else{
					  alert("No se encontraron datos");
				  }
				  
			  }, function errorCallback(response) {
				    alert("Ocurrio un error recuperando el cliente");
			  });
		  }
	  
	  $scope.generarReporte = function () {
		  ServicesFactory.generarReporteAportesCliente($scope.documento);
	  }
	  
	  $scope.generarReporteAportesFechas = function () {
		  ServicesFactory.generarReporteAportesFechas($scope.fechaini.getTime(),$scope.fechafin.getTime());
	  }
	  $scope.deshacerAporte = function (aporte){
		  var diff = (new Date().getTime() - aporte.fecha)/(1000*60*60*24);
		  if(diff<120){
			  ServicesFactory.deshacerAporte(aporte.id)
			  .then(function(data) {
				  $scope.buscarAportes();
				  alert("Aporte eliminado Correctamente");
			  }, function errorCallback(response) {
				    alert("Ocurrio un error borrando el aporte");
			  });
		  }else{
			  alert("No se puede deshacer el aporte por que es mayor a 120 dias");
		  }
	  }
	  
	  $scope.buscarAportes = function(){
		  $scope.totalPagos = 0;
		  $scope.aportes = [];
		  ServicesFactory.buscarAportesByCliente($scope.cliente.documento)
		  .then(function(data) {
			  if(data.data.length > 0){
				  $scope.aportes = data.data;
				  angular.forEach($scope.aportes, function(value, key) {
				  	$scope.totalPagos = $scope.totalPagos+value.valor;
				  });
			  }else{
				  alert("No se encontraron datos");
			  }
			  
		  }, function errorCallback(response) {
			    alert("Ocurrio un error recuperando el cliente");
		  });
	  }
	  $scope.init();
   }]);