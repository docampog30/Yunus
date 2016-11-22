controllers
  .controller('ReportesController',['$scope','$rootScope','$http', 'ServicesFactory','$modal', function($scope,$rootScope,$http,ServicesFactory,$modal) {
	  
	  
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
	  
	  $scope.sacramentos = ['MATRIMONIO','BAUTIZO','CONFIRMACION'];
	  
	  $scope.emptyOrNull = function(item){
		  return !(item.Message === null || item.Message.trim().length === 0)
		}

	  $scope.init();

	  $scope.descargar = function(partida){
		  ServicesFactory.descargarPartidaMatrimonio(partida.id);
	  }
	  
}]);