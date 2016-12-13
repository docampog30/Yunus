controllers
  .controller('ConfirmacionesController',['$scope','$rootScope','$http', 'ServicesFactory','$modal', function($scope,$rootScope,$http,ServicesFactory,$modal) {
	  $scope.idPartida = $routeParams.id;
	  $scope.guardar = function() {
		  ServicesFactory.guardarConfirmacion($scope.confirmacion)
		  .then(function(data) {
			  alert('Partida guardada correctamente');
			  ServicesFactory.descargarPartidaPDF(data.data);
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
		  if( $scope.idPartida != undefined){
			  $scope.confirmacion = ServicesFactory.recuperarPartida( $scope.idPartida);
		  }
	  }
	  
	  $scope.init();
   }]);