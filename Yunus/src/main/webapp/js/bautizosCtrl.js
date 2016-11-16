controllers
  .controller('BautizosController',['$scope','$rootScope','$http', 'ServicesFactory','$modal', function($scope,$rootScope,$http,ServicesFactory,$modal) {
	  $scope.guardar = function() {
		  ServicesFactory.guardarBautizo($scope.bautizo)
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
		  $scope.bautizo = {};
		  $scope.bautizo.persona1 = {};
	  }
	  
	  $scope.listarMinistrosActivos = function(){
		  ServicesFactory.listarMinistrosActivos()
		  .then(function(data) {
			  $scope.ministros = data.data;
		  }, function(response) {
		    alert("Error consultando los ministros");
		  });
	  }
	  
	  $scope.imprimir = function(){
		  ServicesFactory.descargarPartidaBautizo(3);
	  }
	  
	  $scope.init();
	  $scope.listarMinistrosActivos();
	  $scope.sexos = [{id:'M',desc:'MASCULINO'},{id:'F',desc:'FEMENINO'}];
	  
   }]);