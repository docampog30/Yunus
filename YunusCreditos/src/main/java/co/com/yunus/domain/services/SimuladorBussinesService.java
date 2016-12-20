package co.com.yunus.domain.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.apache.poi.ss.formula.functions.FinanceLib;

import co.com.yunus.application.dto.Credito;
import co.com.yunus.application.dto.RequestSimulador;

public class SimuladorBussinesService {
	private final BigDecimal CIEN = BigDecimal.valueOf(100);
	
	public List<Credito> generarSimulacion(RequestSimulador request){
		List<Credito> simulacion = new ArrayList<>();
		BigDecimal cuota = BigDecimal.valueOf(FinanceLib.pmt(request.getInteres().divide(CIEN).doubleValue(), request.getMeses(), request.getValor().negate().doubleValue(), 0, false));
		
		IntStream.range(1, request.getMeses()+1).forEach(periodo ->{
			Credito credito = new Credito();
			credito.setSaldoInicial(periodo == 1 ? request.getValor() : simulacion.get(simulacion.size()-1).getSaldoFinal());
			credito.setPeriodo(periodo);
			credito.setCuota(cuota);
			credito.setIntereses(periodo == 1 ? request.getValor().multiply(request.getInteres()).divide(CIEN) : simulacion.get(simulacion.size()-1).getSaldoFinal().multiply(request.getInteres()).divide(CIEN));
			
			credito.setIntereses(credito.getIntereses());
			credito.setSaldoInicial(credito.getSaldoInicial());
			
			credito.setAmortizacion(credito.getCuota().subtract(credito.getIntereses()));
			credito.setSaldoFinal(credito.getSaldoInicial().subtract(credito.getAmortizacion()));
			simulacion.add(credito);
		});
		return simulacion;
		
	}
}
