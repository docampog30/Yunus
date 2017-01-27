package co.com.yunus.infrastructure.reports;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

public class ReportGenerator {

	@SuppressWarnings("rawtypes")
	public byte[] getBytes(Map<String,Object> parametros, String reportName,List lista) {
		byte[] exportReportToPdf = null;
		try {
		    JasperReport jasperCompilado =getJasperReport(reportName);
		    JRDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(lista);
			JasperPrint jasperPrint 	 = JasperFillManager.fillReport(jasperCompilado, parametros, beanCollectionDataSource);
			exportReportToPdf = getReportBytes(jasperPrint);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return exportReportToPdf;
	}
	
	public byte[] getBytes(Map<String,Object> parametros, String reportName) {
		byte[] exportReportToPdf = null;
		try {
			JasperReport jasperCompilado = getJasperReport(reportName);
			JasperPrint jasperPrint 	 = JasperFillManager.fillReport(jasperCompilado, parametros, new JREmptyDataSource());
			exportReportToPdf = getReportBytes(jasperPrint);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return exportReportToPdf;
	}

	private byte[] getReportBytes(JasperPrint jasperPrint) throws JRException {
		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

	private JasperReport getJasperReport(String reportName) throws JRException {
		InputStream jasperSin = this.getClass().getClassLoader().getResourceAsStream(reportName);
		JasperReport jasperCompilado = (JasperReport) JRLoader.loadObject(jasperSin);
		return jasperCompilado;
	}
}
