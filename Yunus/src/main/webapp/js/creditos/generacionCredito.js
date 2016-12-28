controllers
  .controller('CreditosController',['$scope','$rootScope','$http', 'ServicesFactory','$modal', function($scope,$rootScope,$http,ServicesFactory,$modal) {

	  $scope.init = function() {

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
						  $scope.credito = {};
					  }
				  }else{
					  alert("No se encontraron datos");
				  }
				  
			  }, function errorCallback(response) {
				    alert("Ocurrio un error recuperando el cliente");
			  });
		  }
	  
	  $scope.simular = function(){
		  ServicesFactory.simularCredito($scope.credito)
		  .then(function(data) {
			  $scope.simulacion = data.data;
			  
		  }, function errorCallback(response) {
			    alert("Error generando simulación");
		  });
	  }
	  
	  $scope.init();
   }]);