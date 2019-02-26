controllers
  .controller('AportesController',['$scope','$rootScope','$http', 'ServicesFactory','$modal', function($scope,$rootScope,$http,ServicesFactory,$modal) {

	  $scope.init = function() {
		  $scope.cliente = null;
		  $scope.valorAporte = null;
		  $scope.tipo= "O";
		  $scope.isDocumentoEscaneado = false;
		  angular.element(document.querySelector('#mainDiv'))[0].focus();
	  }
	  
	  $scope.buscarCliente = function(){
		  $scope.init();
			  ServicesFactory.buscarCliente($scope.documento)
			  .then(function(data) {
				  if(data.data.length > 0){
						  $scope.cliente = data.data[0];
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
			  .success(function (data, status, headers, config) {
				  var cons = parseInt(headers('cons'));
				  alert("Aporte liquidado correctamente");
				  var filename = "Aporte "+$scope.cliente.nombres+$scope.cliente.apellidos+"_"+cons;
				  ServicesFactory.imprimirReporteAporte(data,filename);
				  $scope.init();
			  }, function errorCallback(response) {
				    alert("Ocurrio un error liquidando el aporte");
			  });
		  }else{
			  alert("El aporte debe ser mayor a 0");
		  }
	  }
	  
	  	$scope.scan = function(infoCedula) {
		  $scope.cliente = {};
		  $scope.documento = parseInt(infoCedula.numeroIdentificacion);
		  $scope.$digest();
		  $scope.buscarCliente();
		}
	  
	  $scope.condicionales = [{key:"O",value:"Ordinario"},{key:"E",value:"Extraordinario"},{key:"A",value:"Afiliación"},{key:"H",value:"Honorarios"}];
	  
	  $scope.init();
   }]);