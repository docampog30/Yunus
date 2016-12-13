controllers
  .controller('MatrimoniosController',['$scope','$rootScope','$http', 'ServicesFactory','$modal', function($scope,$rootScope,$http,ServicesFactory,$modal) {
	  $scope.idPartida = $routeParams.id;
	  $scope.guardar = function() {
		  ServicesFactory.guardarMatrimonio($scope.matrimonio)
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
		  $scope.matrimonio = {};
		  $scope.matrimonio.persona1 = {};
		  $scope.matrimonio.persona2 = {};
		  if( $scope.idPartida != undefined){
			  $scope.matrimonio = ServicesFactory.recuperarPartida( $scope.idPartida);
		  }
	  }
	  
	  $scope.listarMinistrosActivos = function(){
		  ServicesFactory.listarMinistrosActivos()
		  .then(function(data) {
			  $scope.ministros = data.data;
		  }, function(response) {
		    alert(response.headers);
		  });
	  }
	  
	  $scope.init();
	  $scope.listarMinistrosActivos();
	  
	  $scope.descargar = function(){
		  ServicesFactory.descargarPartidaMatrimonio(2);
	  }
	  
}]);