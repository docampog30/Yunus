package co.com.yunus.infrastructure.reports;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import co.com.yunus.application.dto.Vinculacion;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

public class ReportGenerator {

	public byte[] getBytes(Map<String,Object> parametros, String reportName) {
		byte[] exportReportToPdf = null;
		try {

			InputStream jasperSin = this.getClass().getClassLoader().getResourceAsStream(reportName);
		    JasperReport jasperCompilado = (JasperReport) JRLoader.loadObject(jasperSin);
			JasperPrint jasperPrint 	 = JasperFillManager.fillReport(jasperCompilado, parametros, new JREmptyDataSource());
			exportReportToPdf = JasperExportManager.exportReportToPdf(jasperPrint);
			
			OutputStream out = new FileOutputStream("C:/Users/david.ocampo/Documents/reporte.pdf");
			out.write(exportReportToPdf);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return exportReportToPdf;
	}
}
