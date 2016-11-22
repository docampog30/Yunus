controllers
.controller('ConfiguracionController',['$scope','$rootScope','$http', 'ServicesFactory','$modal', function($scope,$rootScope,$http,ServicesFactory,$modal) {
	 
	  $scope.init = function(){
		  ServicesFactory.getConfiguracion().then(function(data) {
			  $scope.parroquia = data.data;
		  }, function errorCallback(response) {
			    alert("Error recuperando la información");
		  });
		  $scope.ministro = {};
	  }
	  
	  $scope.guardar = function(){
			  ServicesFactory.actualizarConfiguracion($scope.parroquia).then(function(data) {
				  alert("Parroquia actualizada correctamente");
				  $scope.init();
			  }, function errorCallback(response) {
				    alert("Error actualizando la parroquia");
			  });
	  }
	  
	  $scope.init();
 }]);