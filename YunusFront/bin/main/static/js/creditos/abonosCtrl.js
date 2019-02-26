controllers
  .controller('AbonosController',['$scope','$rootScope','$http', 'ServicesFactory','$modal', function($scope,$rootScope,$http,ServicesFactory,$modal) {

	  $scope.init = function() {
		  $scope.cliente = null;
		  $scope.creditos = null;
		  $scope.credito = null;
		  $scope.valor = null;
		  $scope.isDocumentoEscaneado = false;
		  angular.element(document.querySelector('#mainDiv'))[0].focus();
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
	  
	  $scope.actualizarValor = function(){
		  $scope.valor = 0;
		 var seleccionados = $scope.credito.detalles.filter(isSelected);
		 angular.forEach(seleccionados, function(value) {
			 if(moment(value.fecha).isAfter(new Date())){
				 $scope.valor += value.amortizacion;
			 }else{
				 $scope.valor += value.cuota;
			 }
		 });
	  }
	  
	  $scope.liquidar = function(){
		  var seleccionados = $scope.credito.detalles.filter(isSelected);
		  angular.forEach(seleccionados, function(value, key) {
		  	delete(value.liquidar);
		  })
		  
		  var request = {idcliente:$scope.cliente.id,detalles:seleccionados,idcredito:$scope.credito.id};
		  ServicesFactory.liquidarCuotas(request)
		  .success(function (data, status, headers, config) {
			  var cons = parseInt(headers('cons'));
			  alert("Cuotas liquidadas correctamente");
			  var filename = "Abono "+$scope.cliente.nombres+$scope.cliente.apellidos+"_"+cons;
			  ServicesFactory.imprimirReporteAbonos(data,filename);
			  $scope.init();
		  }, function errorCallback(response) {
			    alert("Ocurrio un error liquidando las cuotas");
		  });
	  }
	  
	  $scope.getName = function(maestro){
	    	var rpta = "";
	    	if(maestro != undefined){
		    	if(maestro.codigo == "CL"){
		    		rpta = "Crédito de libre inversión";
		    	}else if(maestro.codigo == "CV"){
		    		rpta = "Crédito de vivienda"
		    	}
	    	}
	    	return rpta;
	    }
	  
	  function isSelected(elemento) {
		  return elemento.liquidar;
		}
		
	$scope.scan = function(infoCedula) {
		  $scope.cliente = {};
		  $scope.documento = parseInt(infoCedula.numeroIdentificacion);
		  $scope.$digest();
		  $scope.buscarCliente();
		}
	  
	  $scope.init();
   }]);