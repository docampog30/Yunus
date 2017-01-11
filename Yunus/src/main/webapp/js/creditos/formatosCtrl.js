controllers
  .controller('FormatosController',
		  ['$scope','$rootScope','$http', 'ServicesFactory','$modal','$location',
			  function($scope,$rootScope,$http,ServicesFactory,$modal,$location) {
	  
	  
	  $scope.buscar = function() {
		  ServicesFactory.buscarVinculaciones($scope.filter)
		  .then(function(data) {
			  $scope.vinculaciones = data.data;
		  }, function errorCallback(response) {
			    alert("Error consultando las vinculaciones, por favor informe al administrador del sistema");
		  });
	  }
	  
	  $scope.editar = function(vinculacion){
		  $location.url('/vinculacion/'+vinculacion.id);
	  }

	  $scope.descargar = function(partida){
		  ServicesFactory.descargarVinculacion(partida.id);
	  }
	  
}]);