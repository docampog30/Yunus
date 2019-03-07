controllers
  .controller('ClientesController',['$scope','$rootScope','$http', 'ServicesFactory','$modal','$window', function($scope,$rootScope,$http,ServicesFactory,$modal, $window) {
	  $scope.guardar = function() {
		  ServicesFactory.guardarCliente($scope.cliente)
		  .then(function(data) {
			  alert('Cliente guardado correctamente');
			  $scope.init();
			  
		  }, function errorCallback(response) {
			    alert("Este cliente ya existe");
		  });
	  }
	  
	  $scope.init = function() {
		  $scope.cliente = {};
		  $scope.recuperarEstadosCiviles(1);
		  $scope.recuperarNivelesEducativos(2);
		  $scope.recuperarTiposVivienda(3);
		  $scope.recuperarZonasVivienda(4);
		  $scope.recuperarSexos();
		  $scope.recuperarEstratos();
		  $scope.recuperarOcupaciones(5);
		  $scope.recuperarTiposDocumento();
		  $scope.ismenoredad = false;
		  
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
		  $scope.sexos = [{id:'M',descripcion:'MASCULINO'},{id:'F',descripcion:'FEMENINO'}];
	  }
	  $scope.recuperarEstratos = function(){
		  $scope.estratos = [{id:1,descripcion:'Estrato 1'},{id:2,descripcion:'Estrato 2'},{id:3,descripcion:'Estrato 3'},{id:4,descripcion:'Estrato 4'},{id:5,descripcion:'Estrato 5'},{id:6,descripcion:'Estrato 6'},];
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
		  $scope.tiposDocumento = [{id:'CC',descripcion:'Cédula de ciudadania'},{id:'TI',descripcion:'Tarjeta de identidad'}]
	  }
	  
	  $scope.$watch('cliente.fenacimiento', function(newVal, oldVal){
		    if(newVal != undefined){
			    var age = calculateAge($scope.cliente.fenacimiento);
			    if(age <=18){
			    	$scope.ismenoredad = true;
			    }else{
			    	$scope.ismenoredad = false;
			    }
		    }
		  });
	  
	  $scope.buscarCliente = function(){
		  ServicesFactory.buscarCliente($scope.cliente.documento)
		  .then(function(data) {
			  if(data.data.length > 0){
				  $scope.cliente = $scope.parseData(data.data[0]);
			  }
			  
		  }, function errorCallback(response) {
			    alert("Ocurrio un error recuperando el cliente");
		  });
	  }
	  
	  $scope.descargarClientes = function(){
		  ServicesFactory.descargarClientes();
	  }
	  
	  $scope.parseData = function(cliente){
		  cliente.feexpedicion 	= new Date(cliente.feexpedicion);
		  cliente.fenacimiento 		= new Date(cliente.fenacimiento);
		  cliente.celular			= parseInt( cliente.celular);
		  cliente.telefono			= parseInt(cliente.telefono);
		  return cliente;
	  }
	  
	  function calculateAge(birthday) {
		    var ageDifMs = Date.now() - birthday.getTime();
		    var ageDate = new Date(ageDifMs);
		    return Math.abs(ageDate.getUTCFullYear() - 1970);
		}
	  
	  $scope.init();
   }]);