controllers
  .controller('ConfirmacionesController',['$scope','$rootScope','$http', 'ServicesFactory','$modal', function($scope,$rootScope,$http,ServicesFactory,$modal) {
	  $scope.guardar = function() {
		  ServicesFactory.guardarConfirmacion($scope.confirmacion)
		  .then(function(data) {
			  alert('Partida guardada correctamente');
			  $scope.init();
		  }, function errorCallback(response) {
			    console.log(response.headers());
			    console.log(response.headers);
			    console.log(response.headers['message']);
			    alert("Código repetido");
		  });
	  }
	  
	  $scope.init = function() {
		  $scope.confirmacion = {};
		  $scope.confirmacion.persona1 = {};
	  }
	  
	  $scope.init();
   }]);