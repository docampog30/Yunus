package co.com.yunus.infrastructure.excel;

import java.util.Arrays;

public class ExcelFileDescriptor {

	private int rowOffset;
	private int columnOffset;
	private IExcelColumnDescriptor[] columnDescriptors;
	
	public ExcelFileDescriptor(IExcelColumnDescriptor[] descriptor) {
		this(descriptor, 0, 0);
	}
	
	public ExcelFileDescriptor(IExcelColumnDescriptor[] descriptor, int rowOffset) {
		this(descriptor, rowOffset, 0);
	}
	
	public ExcelFileDescriptor(IExcelColumnDescriptor[] descriptor, int rowOffset, int columnOffset) {
		this.columnDescriptors = Arrays.copyOf(descriptor,descriptor.length);
		this.rowOffset = rowOffset;
		this.columnOffset = columnOffset;
	}
	
	public IExcelColumnDescriptor[] getColumnDescriptors() {
		return columnDescriptors.clone();  
	}
	
	public int getRowOffset() {
		return rowOffset;
	}
	
	public int getColumnOffset() {
		return columnOffset;
	}
}
