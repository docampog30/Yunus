controllers
  .controller('ReporteMorososController',['$scope','$rootScope','$http', 'ServicesFactory','$modal', function($scope,$rootScope,$http,ServicesFactory,$modal) {

	  $scope.init = function() {
		  $scope.cliente = {};
	  }
	  
	  $scope.buscarCliente = function(){
		  ServicesFactory.buscarCliente($scope.cliente.documento)
		  .then(function(data) {
			  if(data.data.length > 0){
				  $scope.cliente = data.data[0];
				  $scope.generarReporteMorososCliente();
			  }else{
				  alert("No se encontraron datos");
			  }
			  
		  }, function errorCallback(response) {
			    alert("Ocurrio un error recuperando el cliente");
		  });
	  }
	  
	  $scope.generarReporte = function(){
		  ServicesFactory.generarReporteMorosos($scope.fechaini.getTime(),$scope.fechafin.getTime());
	  }
	  
	  $scope.generarReporteMorososCliente = function(){
		  ServicesFactory.generarReporteMorososCliente($scope.cliente.documento,$scope.cliente.nombres);
	  }
	  
	  
	  $scope.init();
   }]);