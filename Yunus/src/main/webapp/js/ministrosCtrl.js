controllers
  .controller('MinistrosController',['$scope','$rootScope','$http', 'ServicesFactory','$modal', function($scope,$rootScope,$http,ServicesFactory,$modal) {
	  $scope.tipos = [{id:"P",value:"SI"},{id:"M",value:"NO"}];
	  $scope.editorEnabled=false;
	  
	    $scope.reset = function () {
	        $scope.selected = {};
	    };
	  
	  $scope.init = function(){
		  $scope.reset();
		  ServicesFactory.getMinistros().then(function(data) {
			  $scope.ministros = data.data;
		  }, function errorCallback(response) {
			    alert("Error recuperando los ministros");
		  });
		  $scope.ministro = {};
	  }
	  
	  $scope.guardar = function(){
		  if($scope.validarParrocos()){
			  $scope.ministro.estado = true;
			  ServicesFactory.grabarMinistro($scope.ministro).then(function(data) {
				  alert("Ministro grabado correctamente");
				  $scope.init();
			  }, function errorCallback(response) {
				    alert("Error grabando el ministro");
			  });
		  }else{
			  alert("No pueden existir dos parrocos")
		  }
	  }
	  
	  $scope.init();
	  $scope.validarParrocos = function(){
		  var rpta = true;
		  var nroParrocos = $scope.ministros.filter((ministro) => ministro.tipo == 'P').length;
		 if($scope.ministro.tipo == 'P'){
			  if(nroParrocos >= 1){
				  rpta = false;
			  }
		  }
		  return rpta;
	  }
	  
	  $scope.edit = function(ministro){
			$scope.selected = angular.copy(ministro);
	}
	  
	  $scope.update = function(idx){
		  var nroParrocos = $scope.ministros.filter((ministro) => ministro.tipo == 'P').length;
		  if($scope.selected .tipo == 'P' && nroParrocos >=1){
			  alert("No pueden existir dos parrocos")
			  return;
		  }
		  
		  ServicesFactory.actualizarMinistro($scope.selected)
			.then(function (data){
				alert("Registro Actualizado Exitosamente");
				$scope.init();
			}, function (response) {
				alert("Error actualizando ministro");
				$scope.init();
			});
		}
		
		$scope.getTemplate = function (ministro) {
	        if (ministro.id === $scope.selected.id) return 'templates/editMinistro.html';
	        else return 'templates/displayMinistro.html';
	    };
	    
	    $scope.condicionales = [{key:"P",value:"SI"},{key:"M",value:"NO"}];

   }]);