controllers
  .controller('MatrimoniosController',['$scope','$rootScope','$http', 'ServicesFactory','$modal','$routeParams',
	  function($scope,$rootScope,$http,ServicesFactory,$modal,$routeParams) {
	  $scope.idPartida = $routeParams.id;
	  $scope.guardar = function() {
		  if($scope.matrimonio.id == undefined){
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
		  }else{
			  ServicesFactory.actualizarPartida($scope.matrimonio)
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
		  $scope.matrimonio = {};
		  $scope.matrimonio.persona1 = {};
		  $scope.matrimonio.persona2 = {};
		  if( $scope.idPartida != undefined){
			  ServicesFactory.recuperarPartida( $scope.idPartida).then(function(data) {
				  $scope.matrimonio = $scope.parseData(data.data);
			  }, function(response) {
			    alert("Error consultando la partida");
			  });;
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
	  
	  $scope.parseData = function(partida){
		  partida.libroNro = parseInt(partida.libroNro);
		  partida.anioPartida = parseInt(partida.anioPartida);
		  partida.folio = parseInt(partida.folio);
		  partida.numero = parseInt(partida.numero);
		  partida.persona1.cedula = parseInt(partida.persona1.cedula);
		  partida.persona1.fNacimiento = new Date(partida.persona1.fNacimiento);
		  partida.persona2.fNacimiento = new Date(partida.persona2.fNacimiento)
		  partida.persona2.cedula = parseInt(partida.persona2.cedula);
		  partida.fecha = new Date(partida.fecha);
		  return partida;
		  
	  }
	  
}]);