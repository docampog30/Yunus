controllers
  .controller('ReportesController',
		  ['$scope','$rootScope','$http', 'ServicesFactory','$modal','$location',
			  function($scope,$rootScope,$http,ServicesFactory,$modal,$location) {
	  
	  
	  $scope.buscar = function() {
		  ServicesFactory.buscarPartidas($scope.filter)
		  .then(function(data) {
			  $scope.partidas = data.data;
		  }, function errorCallback(response) {
			    alert("Error consultando las partidas, por favor informe al administrador del sistema");
		  });
	  }
	  
	  $scope.init = function() {
		  $scope.partidas = [];
	  }
	  $scope.editar = function(partida){
		  if(partida.tipo == 'MATRIMONIO'){
			  $location.url('/matrimonios/'+partida.id);
		  }else if(partida.tipo == 'BAUTIZO'){
			  $location.url('/bautizos/'+partida.id);
		  }else if(partida.tipo == 'CONFIRMACION'){
			  $location.url('/confirmaciones/'+partida.id);
		  }
	  }
	  
	  $scope.sacramentos = ['MATRIMONIO','BAUTIZO','CONFIRMACION'];
	  
	  $scope.emptyOrNull = function(item){
		  return !(item.Message === null || item.Message.trim().length === 0)
		}

	  $scope.init();

	  $scope.descargar = function(partida){
		  ServicesFactory.descargarPartida(partida.tipo.toLowerCase(), partida.id);
	  }
	  
}]);