controllers
  .controller('BautizosController',['$scope','$rootScope','$http', 'ServicesFactory','$modal','$routeParams',
	  function($scope,$rootScope,$http,ServicesFactory,$modal,$routeParams) {
	  
	  $scope.idPartida = $routeParams.id;
	  
	  $scope.guardar = function() {
		  if($scope.bautizo.id == undefined){
			  ServicesFactory.guardarBautizo($scope.bautizo)
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
			  ServicesFactory.actualizarPartida($scope.bautizo)
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
		  $scope.bautizo = {};
		  $scope.bautizo.persona1 = {};
		  if( $scope.idPartida != undefined){
			  ServicesFactory.recuperarPartida( $scope.idPartida).then(function(data) {
				  $scope.bautizo = $scope.parseData(data.data);
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
		    alert("Error consultando los ministros");
		  });
	  }
	  
	  $scope.parseData = function(partida){
		  partida.libroNro = parseInt(partida.libroNro);
		  partida.anioPartida = parseInt(partida.anioPartida);
		  partida.folio = parseInt(partida.folio);
		  partida.numero = parseInt(partida.numero);
		  partida.persona1.cedula = parseInt(partida.persona1.cedula);
		  partida.persona1.fNacimiento =  new Date(partida.persona1.fNacimiento);
		  partida.fecha = new Date(partida.fecha);
		  return partida;
		  
	  }
	  
	  $scope.init();
	  $scope.listarMinistrosActivos();
	  $scope.sexos = [{id:'M',desc:'MASCULINO'},{id:'F',desc:'FEMENINO'}];
	  
   }]);