controllers
  .controller('AportesController',['$scope','$rootScope','$http', 'ServicesFactory','$modal', function($scope,$rootScope,$http,ServicesFactory,$modal) {

	  $scope.init = function() {
		  $scope.cliente = null;
		  $scope.valorAporte = null;
		  $scope.tipo= "O";
	  }
	  
	  $scope.buscarCliente = function(){
		  $scope.init();
			  ServicesFactory.buscarCliente($scope.documento)
			  .then(function(data) {
				  if(data.data.length > 0){
					  if(data.data[0].vinculaciones.length == 0){
						  alert("El cliente no poseé vinculaciones asociadas");
					  }else{
						  $scope.cliente = data.data[0];
					  }
				  }else{
					  alert("No se encontraron datos");
				  }
				  
			  }, function errorCallback(response) {
				    alert("Ocurrio un error recuperando el cliente");
			  });
		  }
	  
	  $scope.pagarAporte = function(){
		  if($scope.valorAporte > 0){
			  var request = {idcliente:$scope.cliente.id,valor:$scope.valorAporte,tipaporte:$scope.tipo}
			  ServicesFactory.liquidarAporte(request)
			  .then(function(data) {
				  alert("Aporte liquidado correctamente");
				  ServicesFactory.imprimirReporteAporte(data.data);
				  $scope.init();
			  }, function errorCallback(response) {
				    alert("Ocurrio un error liquidando el aporte");
			  });
		  }else{
			  alert("El aporte debe ser mayor a 0");
		  }
	  }
	  
	  $scope.condicionales = [{key:"O",value:"Ordinario"},{key:"E",value:"Extraordinario"}];
	  
	  $scope.init();
   }]);