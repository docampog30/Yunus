package co.com.yunus.domain.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import co.com.yunus.application.dto.Credito;
import co.com.yunus.application.dto.RequestSimulador;

public class SimuladorBussinesService {
	private final BigDecimal CIEN = BigDecimal.valueOf(100);
	
	public List<Credito> generarSimulacion(RequestSimulador request){
		List<Credito> simulacion = new ArrayList<>();
		BigDecimal cuota = BigDecimal.valueOf(72304.79); 
		IntStream.range(1, request.getMeses()+1).forEach(periodo ->{
			Credito credito = new Credito();
			credito.setSaldoInicial(periodo == 1 ? request.getValor() : simulacion.get(simulacion.size()-1).getSaldoFinal());
			credito.setPeriodo(periodo);
			credito.setCuota(cuota);
			credito.setIntereses(periodo == 1 ? request.getValor().multiply(request.getInteres()).divide(CIEN) : simulacion.get(simulacion.size()-1).getSaldoFinal().multiply(request.getInteres()).divide(CIEN));
			
			credito.setIntereses(credito.getIntereses().setScale(2, BigDecimal.ROUND_CEILING));
			credito.setSaldoInicial(credito.getSaldoInicial().setScale(2, BigDecimal.ROUND_CEILING));
			
			credito.setAmortizacion(credito.getCuota().subtract(credito.getIntereses()));
			credito.setSaldoFinal(credito.getSaldoInicial().subtract(credito.getAmortizacion()));
			simulacion.add(credito);
		});
		return simulacion;
		
	}
}
