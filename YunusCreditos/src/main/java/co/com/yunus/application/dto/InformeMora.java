package co.com.yunus.application.dto;

import co.com.yunus.infrastructure.excel.IExcelColumnDescriptor;

public enum InformeMora implements IExcelColumnDescriptor {
	
	FECHA("FECHA PAGO CUOTA", "getFecha"),
	NUMERO("# CR\u00c9DITO", "getidcredito"),
	PERIODO("PERIODO", "getPeriodo"),
	VALOR_CUOTA("VALOR CUOTA", "getCuota"),
	VINCULADO("VINCULADO", "getCliente"),
	DOCUMENTO("DOCUMENTO", "getDocumentoCliente");

	private String header;
	private String attribute;

	private InformeMora(String header, String attribute) {
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
