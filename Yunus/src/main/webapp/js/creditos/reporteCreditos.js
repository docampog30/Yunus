controllers
  .controller('ReporteCreditosController',['$scope','$rootScope','$http', 'ServicesFactory','$modal', function($scope,$rootScope,$http,ServicesFactory,$modal) {

	  $scope.init = function() {
		  $scope.detalles = null;
		  $scope.documento = null;
		  $scope.creditos = null;
		  $scope.totalPagos = 0;

	  }
	  
	  $scope.buscarTotales = function(){
		  ServicesFactory.buscarTotales($scope.feini,$scope.feiin)
		  .then(function(data) {
			  $scope.totales = data.data;
			  
		  }, function errorCallback(response) {
			    alert("Ocurrio un error recuperando totales");
		  });
	  }
	  
	  $scope.buscarCliente = function(){
		  $scope.cliente = null;
			  ServicesFactory.buscarCliente($scope.documento)
			  .then(function(data) {
				  if(data.data.length > 0){
					  if(data.data[0].vinculaciones.length == 0){
						  alert("El cliente no poseé vinculaciones asociadas");
					  }else{
						  $scope.cliente = data.data[0];
						  $scope.buscarCreditos();
					  }
				  }else{
					  alert("No se encontraron datos");
				  }
				  
			  }, function errorCallback(response) {
				    alert("Ocurrio un error recuperando el cliente");
			  });
		  }
	  
	  $scope.buscarConsolidado = function(){
		  ServicesFactory.buscarSaldoEntreFechas($scope.cliente.documento)
		  .then(function(data) {
			  
		  }, function errorCallback(response) {
			    alert("Ocurrio un error recuperando los saldos");
		  });
	  }
	  
	  $scope.buscarCreditos = function(){
		  $scope.init();
		  $scope.detalles = [];
		  ServicesFactory.buscarCreditosByCliente($scope.cliente.documento)
		  .then(function(data) {
			  if(data.data.length > 0){
				  $scope.creditos = data.data;
				  angular.forEach($scope.creditos, function(value, key) {
					  var detalles = value.detalles.filter(esPagada);
					  angular.forEach(detalles, function(detalle, key) {
						  detalle.idcredito = value.id;
					  	$scope.detalles.push(detalle);
					  	$scope.totalPagos+= detalle.cuota;
					  })
				  })
			  }else{
				  alert("No se encontraron datos");
			  }
			  
		  }, function errorCallback(response) {
			    alert("Ocurrio un error recuperando el cliente");
		  });
	  }
	  
	  function esPagada(elemento) {
		  return elemento.estado == "PAGADA";
		}
	  
	  $scope.init();
   }]);