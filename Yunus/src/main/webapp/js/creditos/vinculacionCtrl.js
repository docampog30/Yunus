controllers
  .controller('VinculacionController',['$scope','$rootScope','$http', 'ServicesFactory','$modal', function($scope,$rootScope,$http,ServicesFactory,$modal) {
	  $scope.guardar = function() {
		  ServicesFactory.guardarVinculacion($scope.vinculacion)
		  .then(function(data) {
			  alert('Cliente guardado correctamente');
			  $scope.init();
			  
		  }, function errorCallback(response) {
			    alert("Ocurrio un error guardando los datos");
		  });
	  }
	  $scope.buscarCliente = function(){
		  ServicesFactory.buscarCliente($scope.documento)
		  .then(function(data) {
			  if(data.data.length > 0){
				  $scope.cliente = data.data[0];
				  $scope.vinculacion = {};
				  $scope.vinculacion.beneficiarios = [];
			  }else{
				  alert("No se encontraron datos");
			  }
			  
		  }, function errorCallback(response) {
			    alert("Ocurrio un error guardando los datos");
		  });
	  }
	  
	  $scope.nuevoBeneficiario = function(){
		  $scope.vinculacion.beneficiarios.push({documento:"",nombre:""});
	  }
	  
	  $scope.eliminarBeneficiario = function(index){
		  $scope.vinculacion.beneficiarios.splice(index,1); 
	  }
	  
	  $scope.init = function() {
		  $scope.cliente = null;
		  $scope.vinculacion = null;
	  }
	  
	  $scope.condicionales = [{key:undefined,value:undefined},{key:"S",value:"SI"},{key:"N",value:"NO"}];
	  
	  $scope.init();
   }]);