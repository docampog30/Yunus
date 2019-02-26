controllers
  .controller('LoginController',['$scope','$location','$cookies','ServicesFactory', function($scope,$location,$cookies,ServicesFactory) {
	  
	  $scope.login = function(){
		  ServicesFactory.login($scope.user)
		  .then(function(data) {
			  if(data.data.valid){
			  	window.localStorage.setItem("yunus", JSON.stringify({date:Date.now(),user:$scope.user.user}));
				$location.url('/home');
			  }else{
				  alert("Usuario o contraseña erradas");
			  }
		  }, function errorCallback(response) {
			  alert("Error en autenticación");
		  });
			
	  }
	  $scope.init = function(){
		  window.localStorage.removeItem("yunus");
	  }
	  $scope.init();
	  
}]);