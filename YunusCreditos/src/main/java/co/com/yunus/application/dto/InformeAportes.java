package co.com.yunus.application.dto;

import co.com.yunus.infrastructure.excel.IExcelColumnDescriptor;

public enum InformeAportes implements IExcelColumnDescriptor{
		FECHA("FECHA", "getFecha"),
		VALOR("VALOR APORTE", "getValor"),
		TIPO("TIPO", "getTipo"),
		CLIENTE("CLIENTE", "getClienteName"),
		DOCUMENTO("DOCUMENTO", "getClienteDocumento");

		private String header;
		private String attribute;

		private InformeAportes(String header, String attribute) {
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
