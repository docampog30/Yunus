controllers
  .controller('VinculacionController',['$scope','$rootScope','$http', 'ServicesFactory','$modal','$routeParams', function($scope,$rootScope,$http,ServicesFactory,$modal,$routeParams) {
	  $scope.guardar = function() {
		  if($scope.vinculacion.id == undefined){
			  $scope.grabar();
		  }else{
			  $scope.actualizar();
		  }
	  }
	  $scope.actualizar = function(){
		  $scope.vinculacion.confirmaciones = [];
		  $scope.vinculacion.confirmaciones.push($scope.confirmacion);
		  ServicesFactory.actualizarVinculacion($scope.vinculacion)
		  .then(function(data) {
			  alert('Vinculación guardada correctamente');
			  print = window.confirm('Desea imprimir el reporte de afiliación ?');
			  if(print){
				  ServicesFactory.imprimirReporteAfiliacion(data.data);
			  }
			  $scope.init();
			  
		  }, function errorCallback(response) {
			    alert("Ocurrio un error guardando los datos");
		  });  
	  }
	  
	  $scope.grabar = function(){
		  $scope.vinculacion.confirmaciones = [];
		  $scope.vinculacion.confirmaciones.push($scope.confirmacion);
		  ServicesFactory.guardarVinculacion($scope.vinculacion)
		  .then(function(data) {
			  alert('Vinculación guardada correctamente');
			  print = window.confirm('Desea imprimir el reporte de afiliación ?');
			  if(print){
				  ServicesFactory.imprimirReporteAfiliacion(data.data);
			  }
			  $scope.init();
			  
		  }, function errorCallback(response) {
			    alert("Ocurrio un error guardando los datos");
		  });  
	  }
	  $scope.buscarCliente = function(){
		  ServicesFactory.buscarCliente($scope.documento)
		  .then(function(data) {
			  if(data.data.length > 0){
				  $scope.cliente = data.data[0];
				  $scope.vinculacion = {};
				  $scope.vinculacion.beneficiarios = [];
				  $scope.vinculacion.idCliente = $scope.cliente.id;
			  }else{
				  alert("No se encontraron datos");
			  }
			  
		  }, function errorCallback(response) {
			    alert("Ocurrio un error recuperando el cliente");
		  });
	  }
	  
	  $scope.recuperarTiposContrato = function(idmaestro){
		  ServicesFactory.listarMaestro(idmaestro)
		  .then(function(data) {
			  $scope.tiposContrato = data.data;
		  }, function errorCallback(response) {
			    alert("Ocurrio un error recuperando los datos");
		  });
	  }
	  
	  $scope.recuperarTiposEmpresa = function(idmaestro){
		  ServicesFactory.listarMaestro(idmaestro)
		  .then(function(data) {
			  $scope.tiposEmpresa = data.data;
		  }, function errorCallback(response) {
			    alert("Ocurrio un error recuperando los datos");
		  });
	  }
	  
	  $scope.recuperarAfinidad = function(idmaestro){
		  ServicesFactory.listarMaestro(idmaestro)
		  .then(function(data) {
			  $scope.afinidades = data.data;
		  }, function errorCallback(response) {
			    alert("Ocurrio un error recuperando los datos");
		  });
	  }
	  
	  $scope.recuperarResultados= function(){
		  $scope.resultadosEntrevista = [{key:undefined,value:undefined},{key:"A",value:"Aceptado"},{key:"R",value:"Rechazado"}];
	  }
	  
	  
	  $scope.nuevoBeneficiario = function(){
		  $scope.vinculacion.beneficiarios.push({documento:"",nombre:""});
	  }
	  
	  $scope.eliminarBeneficiario = function(index){
		  $scope.vinculacion.beneficiarios.splice(index,1); 
	  }
	  
	  $scope.init = function() {
		  $scope.cliente = null;
		  $scope.vinculacion = null;
		  $scope.documento = null;
		  $scope.confirmacion = null;
		  
		  if( $scope.id != undefined){
			  ServicesFactory.recuperarVinculacionById( $scope.id).then(function(data) {
				  $scope.vinculacion = $scope.parseData(data.data[0]);
				  $scope.cliente = $scope.vinculacion.cliente;
				  $scope.documento = $scope.cliente.documento;
				  $scope.confirmacion = $scope.vinculacion.confirmaciones[0];
			  }, function(response) {
			    alert("Error consultando la partida");
			  });
		  }
	  }
	  
	  $scope.condicionales = [{key:undefined,value:undefined},{key:"S",value:"SI"},{key:"N",value:"NO"}];
	  $scope.recuperarAfinidad(20);
	  $scope.recuperarTiposEmpresa(18);
	  $scope.recuperarTiposContrato(19);
	  $scope.recuperarResultados();
	  
	  $scope.parseData = function(vinculacion){
		  vinculacion.feingreso 	= new Date(vinculacion.feingreso);
		  vinculacion.fecorte 		= new Date(vinculacion.fecorte);
		  vinculacion.feentrevista 	= new Date(vinculacion.feentrevista);
		  angular.forEach(vinculacion.beneficiarios, function(value, key) {
			  value.fechanacimiento = new Date(value.fechanacimiento);
		  })
		  return vinculacion;
	  }
	  
	  $scope.id = $routeParams.id;
	  $scope.init();
   }]);