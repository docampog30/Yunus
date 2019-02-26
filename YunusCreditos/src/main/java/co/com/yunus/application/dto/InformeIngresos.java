package co.com.yunus.application.dto;

import co.com.yunus.infrastructure.excel.IExcelColumnDescriptor;

public enum InformeIngresos implements IExcelColumnDescriptor {
	
	FECHA("FECHA", "getFecha"),
	FPAGO("FECHA PAGO", "getFechapago"),
	NUMERO("# CR\u00c9DITO", "getidcredito"),
	PERIODO("PERIODO", "getPeriodo"),
	VALOR("VALOR ABONO", "getValorpagado");

	private String header;
	private String attribute;

	private InformeIngresos(String header, String attribute) {
		this.header = header;
		this.attribute = attribute;
	}
	
	@Override
	public String getColumnHeader() {
		return header;
	}

	@Override
	public String getColumnDataMapper() {
		return attribute;
	}

	@Override
	public int getColumnIndex() {
		return ordinal();
	}

}
