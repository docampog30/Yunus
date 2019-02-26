package co.com.yunus.infrastructure.excel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.inject.Named;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.jboss.logging.Logger;

import co.com.yunus.exception.AppException;

@Named("excelGeneric")
public class ExcelGenerator<T> {

	private CellStyle dateCellStyle;
	private CreationHelper createHelper;

	private static final int MAX_ROWS_IN_MEMORY = 1000;
	private static final Logger LOG = Logger.getLogger(ExcelGenerator.class);

	private List<IExcelColumnDescriptor> getDescriptor(IExcelColumnDescriptor[] columnDescriptor) {
		return Arrays.asList(columnDescriptor);
	}

	public byte[] generate(List<T> data, ExcelFileDescriptor fileDescriptor, String nameSheet) {
		try (SXSSFWorkbook workbook = new SXSSFWorkbook(MAX_ROWS_IN_MEMORY)){
			Sheet sheet = workbook.createSheet();
			dateCellStyle = workbook.createCellStyle();
			createHelper = workbook.getCreationHelper();
			dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("d/m/yy h:mm"));
			workbook.setSheetName(workbook.getSheetIndex(sheet), nameSheet);
			createHeaders(sheet, fileDescriptor, workbook);
			fillData(sheet, data, fileDescriptor);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			workbook.write(bos);
			return bos.toByteArray();
		} catch (IOException e) {
			LOG.error(e);
			throw new AppException("Error generando el excel", e);
		}
	}

	private void fillData(Sheet sheet, List<T> data, ExcelFileDescriptor fileDescriptor) {
		List<IExcelColumnDescriptor> columnsDescriptor = getDescriptor(fileDescriptor.getColumnDescriptors());
		for (int i = 0; i < data.size(); i++) {
			Row row = sheet.createRow(i + fileDescriptor.getRowOffset() + 1);
			for (IExcelColumnDescriptor column : columnsDescriptor) {
				Cell cell = row.createCell(column.getColumnIndex() + fileDescriptor.getColumnOffset());
				createCell(getData(data.get(i), column), cell);			}
		}
	}

	private void createCell(Object data, Cell cell) {
		if (data == null) {
			cell.setCellValue("");
			return;
		}
		if (Date.class.isAssignableFrom(data.getClass())) {
			Date date = (Date) data;
			cell.setCellStyle(dateCellStyle);
			cell.setCellValue(date);
		} else if (Number.class.isAssignableFrom(data.getClass())) {
			Number number = (Number) data;
			cell.setCellValue(number.doubleValue());
		} else {
			cell.setCellValue(data.toString());
		}
	}

	private Object getData(T data, IExcelColumnDescriptor columnDescriptor) {
		try {
			Method method = data.getClass().getDeclaredMethod(columnDescriptor.getColumnDataMapper(), new Class[0]);
			return method.invoke(data);
		} catch (Exception e) {
			LOG.error(e);
			throw new AppException("Error de configuracion del reporte", e);
		}
	}

	private void createHeaders(Sheet sheet, ExcelFileDescriptor fileDescriptor, SXSSFWorkbook woorkbook) {

		List<IExcelColumnDescriptor> columnsDescriptor = getDescriptor(fileDescriptor.getColumnDescriptors());
		Row fila = sheet.createRow(fileDescriptor.getRowOffset());
		CellStyle cellStyleDescriptor = woorkbook.createCellStyle();
		Font font = woorkbook.createFont();
		for (IExcelColumnDescriptor column : columnsDescriptor) {
			Cell cell = fila.createCell(column.getColumnIndex() + fileDescriptor.getColumnOffset());
			cell.setCellValue(column.getColumnHeader());
			stylesDescriptors(sheet, cellStyleDescriptor, font, column);
			cell.setCellStyle(cellStyleDescriptor);
		}
	}

	private void stylesDescriptors(Sheet sheet, CellStyle cellStyleDescriptor, Font font,
			IExcelColumnDescriptor column) {
		cellStyleDescriptor.setFillPattern(HSSFCellStyle.FINE_DOTS );
		cellStyleDescriptor.setFillForegroundColor(new HSSFColor.BLUE_GREY().getIndex());
		cellStyleDescriptor.setFillBackgroundColor(new HSSFColor.BLUE_GREY().getIndex());
		cellStyleDescriptor.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		font.setColor(HSSFColor.WHITE.index);
		font.setBold(true);
		cellStyleDescriptor.setFont(font);
	}

}