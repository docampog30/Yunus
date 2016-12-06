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
		  $scope.recuperarEstadosCiviles(1);
		  $scope.recuperarNivelesEducativos(2);
		  $scope.recuperarTiposVivienda(3);
		  $scope.recuperarZonasVivienda(4);
		  $scope.recuperarSexos();
		  $scope.recuperarOcupaciones(5);
		  $scope.recuperarTiposDocumento();
		  
	  }
	  
	  
	  $scope.recuperarEstadosCiviles = function(idmaestro){
		  ServicesFactory.listarMaestro(idmaestro)
		  .then(function(data) {
			  $scope.estadosCiviles = data.data;
		  }, function errorCallback(response) {
			    alert("Ocurrio un error recuperando los datos");
		  });
	  }
	  $scope.recuperarNivelesEducativos = function(idmaestro){
		  ServicesFactory.listarMaestro(idmaestro)
		  .then(function(data) {
			  $scope.nivelesEducativos = data.data;
		  }, function errorCallback(response) {
			    alert("Ocurrio un error recuperando los datos");
		  });
	  }
	  $scope.recuperarEstadosCiviles = function(idmaestro){
		  ServicesFactory.listarMaestro(idmaestro)
		  .then(function(data) {
			  $scope.estadosCiviles = data.data;
		  }, function errorCallback(response) {
			    alert("Ocurrio un error recuperando los datos");
		  });
	  }
	  $scope.recuperarTiposVivienda = function(idmaestro){
		  ServicesFactory.listarMaestro(idmaestro)
		  .then(function(data) {
			  $scope.tiposVivienda = data.data;
		  }, function errorCallback(response) {
			    alert("Ocurrio un error recuperando los datos");
		  });
	  }
	  $scope.recuperarZonasVivienda = function(idmaestro){
		  ServicesFactory.listarMaestro(idmaestro)
		  .then(function(data) {
			  $scope.zonasVivienda = data.data;
		  }, function errorCallback(response) {
			    alert("Ocurrio un error recuperando los datos");
		  });
	  }
	  $scope.recuperarSexos = function(){
		  $scope.sexos = [{id:'M',desc:'MASCULINO'},{id:'F',desc:'FEMENINO'}];
	  }
	  $scope.recuperarOcupaciones = function(idmaestro){
		  ServicesFactory.listarMaestro(idmaestro)
		  .then(function(data) {
			  $scope.ocupaciones = data.data;
		  }, function errorCallback(response) {
			    alert("Ocurrio un error recuperando los datos");
		  });
	  }
	  
	  $scope.recuperarTiposDocumento = function(){
		  $scope.tiposDocumento = [{id:'CC',desc:'Cédula de ciudadania'},{id:'TI',desc:'Tarjeta de identidad'}]
	  }
	  
	  $scope.init();
   }]);