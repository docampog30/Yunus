controllers
  .controller('ClientesController',['$scope','$rootScope','$http', 'ServicesFactory','$modal', function($scope,$rootScope,$http,ServicesFactory,$modal) {
	  $scope.guardar = function() {
		  ServicesFactory.guardarCliente($scope.cliente)
		  .then(function(data) {
			  alert('Cliente guardado correctamente');
			  $scope.init();
			  
		  }, function errorCallback(response) {
			    alert("Ocurrio un error guardando los datos");
		  });
	  }
	  
	  $scope.init = function() {
		  $scope.cliente = {};
	  }
	  
	  $scope.init();
	  $scope.sexos = [{id:'M',desc:'MASCULINO'},{id:'F',desc:'FEMENINO'}];
	  
   }]);