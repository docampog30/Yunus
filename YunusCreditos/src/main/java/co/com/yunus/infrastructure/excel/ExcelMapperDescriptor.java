package co.com.yunus.infrastructure.excel;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import co.com.yunus.exception.AppException;

public abstract class ExcelMapperDescriptor implements IExcelColumnDescriptor {

	private String header;
	private String attribute;
	private int order;

	protected ExcelMapperDescriptor(String header, String attribute, int order) {
		this.header = header;
		this.attribute = attribute;
		this.order = order;
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
		return order;
	}
	
	public static List<IExcelColumnDescriptor> values(Class<?> clazz) {
		Field[] declaredFields = clazz.getDeclaredFields();
		List<IExcelColumnDescriptor> descriptor = new ArrayList<IExcelColumnDescriptor>();
		final int constantModifiers = Modifier.PUBLIC + Modifier.STATIC + Modifier.FINAL;
		for (Field f : declaredFields) {
			addDescriptor(descriptor, constantModifiers, f);
		}
		return descriptor;
	}

	private static void addDescriptor(List<IExcelColumnDescriptor> descriptor, int constantModifiers, Field f) {
		try {
			if (f.getModifiers() == constantModifiers && f.get(null) instanceof IExcelColumnDescriptor) {
				descriptor.add((IExcelColumnDescriptor) f.get(null));
			}
		} catch (Exception e) {
			throw new AppException("Error de configuracion del reporte", e);
		}
	}
}
