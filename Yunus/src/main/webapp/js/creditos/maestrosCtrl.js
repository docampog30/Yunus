controllers
  .controller('MaestrosController',['$scope','$rootScope','$http', 'ServicesFactory','$modal', function($scope,$rootScope,$http,ServicesFactory,$modal) {
	  $scope.guardar = function() {
		  ServicesFactory.guardarDatos($scope.maestro)
		  .then(function(data) {
			  alert('Datos guardado correctamente');
			  $scope.init();
			  
		  }, function errorCallback(response) {
			    alert("Error creando los datos");
		  });
	  }
	  
	  $scope.init = function() {
		  $scope.maestro = {};
		  $scope.recuperarDatos(20);
		  $scope.reset();
	  }
	  
	  $scope.recuperarDatos = function(idmaestro){
		  ServicesFactory.listarMaestro(idmaestro)
		  .then(function(data) {
			  $scope.maestros = data.data;
		  }, function errorCallback(response) {
			    alert("Ocurrio un error recuperando los datos");
		  });
	  }
	  
	  $scope.recuperarTiposDocumento = function(){
		  $scope.tiposDocumento = [{id:'CC',descripcion:'Cédula de ciudadania'},{id:'TI',descripcion:'Tarjeta de identidad'}]
	  }
	  $scope.getTemplate = function (ministro) {
	        if (maestro.id === $scope.selected.id) return 'templates/editMaestro.html';
	        else return 'templates/displayMaestro.html';
	   };
	   $scope.reset = function () {
	        $scope.selected = {};
	    };
	  	  
	  $scope.init();
   }]);