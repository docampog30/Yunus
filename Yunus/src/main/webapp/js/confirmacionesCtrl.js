controllers
  .controller('ConfirmacionesController',['$scope','$rootScope','$http', 'ServicesFactory','$modal','$routeParams'
	  , function($scope,$rootScope,$http,ServicesFactory,$modal,$routeParams) {
	  $scope.idPartida = $routeParams.id;
	  $scope.guardar = function() {
		  
		  if($scope.confirmacion.id == undefined){
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
		  }else{
			  ServicesFactory.actualizarPartida($scope.confirmacion)
			  .then(function(data) {
				  alert('Partida actualizada correctamente');
				  $scope.init();
			  }, function errorCallback(response) {
				    console.log(response.headers());
				    console.log(response.headers);
				    console.log(response.headers['message']);
				    alert("Error actualizando la partida");
			  });
		  }
	  }
	  
	  $scope.init = function() {
		  $scope.confirmacion = {};
		  $scope.confirmacion.persona1 = {};
		  if( $scope.idPartida != undefined){
			  ServicesFactory.recuperarPartida( $scope.idPartida).then(function(data) {
				  $scope.confirmacion = $scope.parseData(data.data);
			  }, function(response) {
			    alert("Error consultando la partida");
			  });;
		  }
	  }
	  
	  $scope.parseData = function(partida){
		  partida.libroNro = parseInt(partida.libroNro);
		  partida.anioPartida = parseInt(partida.anioPartida);
		  partida.folio = parseInt(partida.folio);
		  partida.numero = parseInt(partida.numero);
		  partida.persona1.cedula = parseInt(partida.persona1.cedula);
		  partida.fecha = new Date(partida.fecha);
		  return partida;
		  
	  }
	  
	  $scope.init();
   }]);