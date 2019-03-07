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
		  if(vinculacion.tipo === 'vinculacion'){
				  $location.url('/vinculacion/'+vinculacion.id+'/vinculacion');
		  }else{
			  $location.url('/preinscripcion/'+vinculacion.id+'/preinscripcion');
		  }
	  }

	  $scope.descargar = function(partida){
		  var user = JSON.parse(window.localStorage.getItem("yunus")).user;
		  var filename = partida.tipo+"_"+partida.cliente.nombres+partida.cliente.apellidos;
		  ServicesFactory.descargarVinculacion(partida.id,user,filename);
	  }
	  
}]);