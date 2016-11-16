controllers
  .controller('ReservasController',['$scope','$rootScope','$http', 'ServicesFactory','$modal', function($scope,$rootScope,$http,ServicesFactory,$modal) {

	  $scope.init = function(){
		  $scope.horariosIni 	= $scope.getHorarios();
		  $scope.horariosFin 	= $scope.getHorarios();
		  $scope.tarifasMin 	= $scope.getTarifas();
		  $scope.tarifasMax 	= $scope.getTarifas();
		  $scope.estados 		= $scope.getEstados();
	  }
	  
	  $scope.buscarHora= function(){
		  ServicesFactory.listarVuelosEntreHoras($scope.horaini.value,$scope.horafin.value).success(function(data)
          {
        	  $scope.listaVuelos = data;
        	  $scope.reset();
          });
	  }
	  
	  $scope.buscarTarifas = function(){
		  ServicesFactory.listarVuelosEntreTarifas($scope.tarifaMax,$scope.tarifaMin).success(function(data)
          {
			  $scope.listaVuelos = data;
			  $scope.reset();
          });
	  }
	  
	  $scope.buscarEstado = function(){
		  ServicesFactory.listarVuelosPorEstado($scope.estado.id).success(function(data)
          {
			  $scope.listaVuelos = data;
			  $scope.reset();
          });
	  }
	  
	  $scope.getHorarios = function(){
		var horarios = [];
		for (var int = 0; int < 25; int++) {
			horarios.push ({label:('0'+ int).slice(-2)+":00" ,value:int});
		}
		return horarios;
	  };
	  
	  $scope.getHour = function(hora){
		  return hora.toString().slice(-2)+":00"
	  }
	  $scope.getEstado = function(estado){
		for (var i = 0; i < $scope.estados.length; i++) {
			var estadoObj = $scope.estados[i];
			if(estado == estadoObj.id){
				return estadoObj.value;
			}
		}
	  }
	  
	  $scope.getTarifas = function(){
		  var tarifas = [];
		  
		  var int = 0;
		  while (int <= 2000000) {
			  tarifas.push (int);
			  int = int +100000;
		}
			return tarifas;
	  }
	  
	  $scope.getEstados = function(){
		  var estados = [{id:1,value:"Sin novedad"},{id:2,value:"Sin asientos disponibles"},{id:3,value:"Asientos disponibles"}];
		  return estados;
	  }
	  
	  $scope.reset= function(){
		  $scope.estado = "";
		  $scope.tarifaMin = "";
		  $scope.tarifaMax = "";
		  $scope.horaini = "";
		  $scope.horafin = "";
	  }
	  
   }]);
