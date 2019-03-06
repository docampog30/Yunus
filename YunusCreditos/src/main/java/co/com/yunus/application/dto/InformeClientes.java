package co.com.yunus.application.dto;

import co.com.yunus.infrastructure.excel.IExcelColumnDescriptor;

public enum InformeClientes  implements IExcelColumnDescriptor{
	CLIENTE("CLIENTE", "getClienteName"),
	DOCUMENTO("DOCUMENTO", "getDocumento"),
	CORREO("CORREO","getEmail"),
	DIRECCION("DIRECCION","getDirresidencia"),
	TELEFONO("TELEFONO","getCelular");

	private String header;
	private String attribute;

	private InformeClientes(String header, String attribute) {
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

