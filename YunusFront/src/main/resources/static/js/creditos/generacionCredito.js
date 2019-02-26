controllers
  .controller('CreditosController',['$scope','$rootScope','$http', 'ServicesFactory','$modal', function($scope,$rootScope,$http,ServicesFactory,$modal) {

	  $scope.init = function() {
		  $scope.cliente =	null;
		  $scope.credito = null;
		  $scope.simulacion = null;
	  }
	  
	  $scope.recuperarDatos = function(idmaestro){
		  ServicesFactory.listarMaestro(idmaestro)
		  .then(function(data) {
			  $scope.maestros = data.data;
			  angular.forEach($scope.maestros,function(object){
				  object.descripcion = parseFloat(object.descripcion);
				  object.codigo = $scope.getName(object);
			  })
		  }, function errorCallback(response) {
			    alert("Ocurrio un error recuperando los datos");
		  });
	  }
	  
	  $scope.actualizaInteres = function(){
		  $scope.credito.interes = $scope.tipo.descripcion;
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
						  $scope.recuperarDatos(30);
					  }
				  }else{
					  alert("No se encontraron datos");
				  }
				  
			  }, function errorCallback(response) {
				    alert("Ocurrio un error recuperando el cliente");
			  });
		  }
	  
	  $scope.generar = function(){
		  var request = {detalles:$scope.simulacion,valor:$scope.credito.valor,plazo:$scope.credito.meses,interes:$scope.credito.interes,idcliente:$scope.cliente.id,idmaestro:$scope.tipo.id};
		  
		  ServicesFactory.generarCredito(request)
		  .then(function(data) {
			  alert("Crédito generado exitosamente");
			  ServicesFactory.imprimirReporteCredito(data.data);
			  $scope.init();
		  }, function errorCallback(response) {
			    alert("Ocurrio un error guardando el crédito");
		  });
	  }
	  
	  $scope.getName = function(maestro){
	    	var rpta = "Crédito de vivienda";
	    	if(maestro.codigo == "CL"){
	    		rpta = "Crédito de libre inversión";
	    	}
	    	return rpta;
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