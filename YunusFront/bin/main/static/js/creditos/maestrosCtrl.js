controllers
  .controller('MaestrosController',['$scope','$rootScope','$http', 'ServicesFactory','$modal', function($scope,$rootScope,$http,ServicesFactory,$modal) {
	  $scope.idparent = 30;
	  $scope.guardar = function() {
		  $scope.maestro.idParent = $scope.idparent;
		  ServicesFactory.guardarDatos($scope.maestro)
		  .then(function(data) {
			  alert('Datos guardado correctamente');
			  $scope.init();
			  
		  }, function errorCallback(response) {
			    alert("Error creando los datos");
		  });
	  }
	  
	  $scope.edit = function(maestro){
			$scope.selected = angular.copy(maestro);
	  }
	  
	  $scope.init = function() {
		  $scope.maestro = {};
		  $scope.recuperarDatos($scope.idparent);
		  $scope.reset();
	  }
	  
	  $scope.recuperarDatos = function(idmaestro){
		  ServicesFactory.listarMaestro(idmaestro)
		  .then(function(data) {
			  $scope.maestros = data.data;
			  angular.forEach($scope.maestros,function(object){
				  object.descripcion = parseFloat(object.descripcion);
			  })
		  }, function errorCallback(response) {
			    alert("Ocurrio un error recuperando los datos");
		  });
	  }
	  
	  $scope.getTemplate = function (maestro) {
	        if (maestro.id === $scope.selected.id) return 'templates/editMaestro.html';
	        else return 'templates/displayMaestro.html';
	   };
	   $scope.reset = function () {
	        $scope.selected = {};
	    };
	    
	    $scope.getName = function(maestro){
	    	var rpta = "Crédito de vivienda";
	    	if(maestro.codigo == "CL"){
	    		rpta = "Crédito de libre inversión";
	    	}
	    	return rpta;
	    }
	    
	    $scope.update = function(idx){
	    	ServicesFactory.actualizarMaestro($scope.selected)
			.then(function (data){
				alert("Registro Actualizado Exitosamente");
				$scope.init();
			}, function (response) {
				alert("Error actualizando interes");
				$scope.init();
			});
	    }
	  	  
	  $scope.init();
   }]);