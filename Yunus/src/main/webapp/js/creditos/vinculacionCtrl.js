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
			  $scope.cliente = data.data[0];
			  
		  }, function errorCallback(response) {
			    alert("Ocurrio un error guardando los datos");
		  });
	  }
	  
	  $scope.init = function() {
		  $scope.cliente = null;
		  $scope.vinculacion = null;
	  }
	  
	  $scope.init();
   }]);