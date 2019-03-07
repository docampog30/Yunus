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
		  if($scope.verificarPorcentajes()){
			  $scope.vinculacion.confirmaciones = [];
			  $scope.vinculacion.confirmaciones.push($scope.confirmacion);
			  $scope.vinculacion.tipo = $scope.tipo;
			  var user = JSON.parse(window.localStorage.getItem("yunus")).user;
			  ServicesFactory.actualizarVinculacion($scope.vinculacion,user)
			  .then(function(data) {
				  alert('Vinculación guardada correctamente');
				  print = window.confirm('Desea imprimir el reporte de afiliación ?');
				  if(print){
					  var filename = $scope.vinculacion.tipo+"_"+$scope.vinculacion.cliente.nombres+$scope.vinculacion.cliente.apellidos; 	
					  ServicesFactory.imprimirReporteAfiliacion(data.data,filename);
				  }
				  $scope.init();
				  
			  }, function errorCallback(response) {
				    alert("Ocurrio un error guardando los datos");
			  });
		  }
	  }
	  
	  $scope.$watchGroup(['vinculacion.vlrahorro', 
		  				  'vinculacion.vlrcesantias',
		  				  'vinculacion.vlrotros',
		  				  'vinculacion.subsidiocaja',
		  				  'vinculacion.vlrsubsidiom',
		  				  'vinculacion.vlrsubsidiod'], 
		  function(newValues, oldValues, scope) {
		  	$scope.totalIngresos = 0;
		  	angular.forEach(newValues, function(value, key) {
		  		$scope.totalIngresos += value;
		  	});
		});
	  
	  $scope.$watchGroup(['vinculacion.issubsidiomunicipal', 
			  'vinculacion.issubsidiodepartamental',
			  'vinculacion.iscajacompensacion'], 
	function(newValues, oldValues, scope) {
		  if(newValues[0] == 'N'){
			  $scope.vinculacion.vlrsubsidiom = 0;
		  }
		  if(newValues[1] == 'N'){
			  $scope.vinculacion.vlrsubsidiod = 0;
		  }
		  if(newValues[2] == 'N'){
			  $scope.vinculacion.subsidiocaja = 0;
		  }
	});
	  
	  $scope.grabar = function(){
		  if($scope.verificarPorcentajes()){
			  $scope.vinculacion.confirmaciones = [];
			  $scope.vinculacion.confirmaciones.push($scope.confirmacion);
			  $scope.vinculacion.tipo = $scope.tipo;
			  var user = JSON.parse(window.localStorage.getItem("yunus")).user;
			  ServicesFactory.guardarVinculacion($scope.vinculacion,user)
			  .then(function(data) {
				  alert('Vinculación guardada correctamente');
				  print = window.confirm('Desea imprimir el reporte de afiliación ?');
				  if(print){
					  var filename = $scope.vinculacion.tipo+"_"+$scope.vinculacion.cliente.nombres+$scope.vinculacion.cliente.apellidos;

					  ServicesFactory.imprimirReporteAfiliacion(data.data,filename);
				  }
				  $scope.init();
				  
			  }, function errorCallback(response) {
				    alert("Ocurrio un error guardando los datos");
			  });
		  }
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
		  $scope.totalIngresos = 0;
		  
		  if( $scope.id != undefined){
			  ServicesFactory.recuperarVinculacionById( $scope.id).then(function(data) {
				  $scope.vinculacion = $scope.parseData(data.data[0]);
				  $scope.cliente = $scope.vinculacion.cliente;
				  $scope.documento = $scope.cliente.documento;
				  $scope.confirmacion = $scope.vinculacion.confirmaciones[0];
			  }, function(response) {
			    alert("Error consultando la la vinculación");
			  });
		  }
	  }
	  
	  $scope.condicionales = [{key:undefined,value:undefined},{key:"S",value:"SI"},{key:"N",value:"NO"}];
	  $scope.origenes = [{key:undefined,value:undefined},{value:"Mis recursos provienen de las actividades laborales y comerciales que realizo"}
	  									,{value:"Declaro que estos recursos no provienen de ninguna actividad ilícita"}];
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
			  value.designacion		= parseInt(value.designacion);
		  })
		  return vinculacion;
	  }
	  
	  $scope.verificarPorcentajes = function(){
		  
		  if(!$scope.tipo){ 
		  var porcentajeTotal = 0;
		  angular.forEach($scope.vinculacion.beneficiarios, function(value, key) {
			 porcentajeTotal += value.designacion;
		  });
		  
		  if(porcentajeTotal != 100){
			  alert("Los porcentajes de beneficiarios deben sumar 100 %");
			  return false;
		  }else{
			  return true;
		  }
		 }else{
			 return true;
		 }
	  }
	  
	  $scope.id = $routeParams.id;
	  $scope.tipo = $routeParams.tipo == 'preinscripcion' ?  $routeParams.tipo : 'vinculacion';
	  $scope.init();
   }]);