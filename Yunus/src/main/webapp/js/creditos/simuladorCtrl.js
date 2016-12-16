controllers
  .controller('SimuladorController',['$scope','$rootScope','$http', 'ServicesFactory','$modal', function($scope,$rootScope,$http,ServicesFactory,$modal) {

	  $scope.init = function() {

	  }
	  
	  $scope.simular = function(){
		  ServicesFactory.simularCredito($scope.credito)
		  .then(function(data) {
			  $scope.simulacion = data.data;
			  
		  }, function errorCallback(response) {
			    alert("Error generando simulación");
		  });
	  }
	  
	  $scope.init();
   }]);