controllers
  .controller('ReporteCreditosController',['$scope','$rootScope','$http', 'ServicesFactory','$modal', function($scope,$rootScope,$http,ServicesFactory,$modal) {

	  $scope.init = function() {
		  $scope.detalles = null;
		  $scope.documento = null;
		  $scope.creditos = null;
		  $scope.totalPagos = 0;
		  $scope.valor = null;
	  }
	  
	  $scope.buscarTotales = function(){
		  $scope.detalles = null;
		  $scope.cliente = null;
		  ServicesFactory.buscarTotales($scope.fechaini.getTime(),$scope.fechafin.getTime())
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
	  
	  $scope.generarReporte = function () {
		  ServicesFactory.generarReporteCliente($scope.documento);
	  }
	  
	  $scope.buscarCreditos = function(){
		  $scope.detalles = [];
		  $scope.totalPagos =0;
		  ServicesFactory.buscarCreditosByCliente($scope.cliente.documento)
		  .then(function(data) {
			  if(data.data.length > 0){
				  $scope.creditos = data.data;
				  angular.forEach($scope.creditos, function(value, key) {
					  var detalles = value.detalles.filter(esPagada);
					  angular.forEach(detalles, function(detalle, key) {
						  detalle.idcredito = value.id;
					  	$scope.detalles.push(detalle);
					  	$scope.totalPagos+= detalle.valorpagado;
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