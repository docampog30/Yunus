controllers
  .controller('AbonosController',['$scope','$rootScope','$http', 'ServicesFactory','$modal', function($scope,$rootScope,$http,ServicesFactory,$modal) {

	  $scope.init = function() {
		  $scope.cliente = null;
		  $scope.creditos = null;
		  $scope.credito = null;
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
	  $scope.buscarCreditos = function(){
		  ServicesFactory.buscarCreditosByCliente($scope.cliente.documento)
		  .then(function(data) {
			  if(data.data.length > 0){
				  $scope.creditos = data.data;
			  }else{
				  alert("No se encontraron datos");
			  }
			  
		  }, function errorCallback(response) {
			    alert("Ocurrio un error recuperando el cliente");
		  });
	  }
	  
	  $scope.creditoSeleccionado = function(cre){
		  $scope.credito = cre;
	  }
	  
	  $scope.actualizarValor = function(cuota){
		  $scope.valor = 0;
		 var seleccionados = $scope.credito.detalles.filter(isSelected);
		 angular.forEach(seleccionados, function(value) {
		 	$scope.valor += value.cuota;

		 })
	  }
	  
	  $scope.liquidar = function(){
		  var seleccionados = $scope.credito.detalles.filter(isSelected);
		  angular.forEach(seleccionados, function(value, key) {
		  	delete(value.liquidar);
		  })
		  
		  var request = {idcliente:$scope.cliente.id,detalles:seleccionados,idcredito:$scope.credito.id};
		  ServicesFactory.liquidarCuotas(request)
		  .then(function(data) {
			  alert("Cuotas liquidadas correctamente");
			  ServicesFactory.imprimirReporteAbonos(data.data);
			  $scope.init();
		  }, function errorCallback(response) {
			    alert("Ocurrio un error liquidando las cuotas");
		  });
	  }
	  
	  function isSelected(elemento) {
		  return elemento.liquidar;
		}
	  
	  $scope.init();
   }]);