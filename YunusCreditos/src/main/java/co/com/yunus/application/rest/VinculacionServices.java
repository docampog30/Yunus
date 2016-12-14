package co.com.yunus.application.rest;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.com.yunus.application.dto.Cliente;
import co.com.yunus.application.dto.Vinculacion;
import co.com.yunus.domain.repositories.ITransactionalRepository;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

@Path("vinculacion")
public class VinculacionServices {
	
	@Inject
	@Named("TransactionalRepositoryImpl")
	private ITransactionalRepository transactionalRepository;
	
	@Inject
	private ClientesServices clientes;
	
	@POST	
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public byte[] guardar(Vinculacion vinculacion){
		vinculacion.getBeneficiarios().stream().forEach(b->b.setVinculacion(vinculacion));
		transactionalRepository.save(vinculacion);
		return getReportBytes();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public byte[] getReportBytes() {
		Vinculacion vinculacion = new Vinculacion();
		List<Cliente> cliente = clientes.getCliente("1047969179");
		vinculacion.setCliente(cliente.get(0));
		byte[] exportReportToPdf = null;
		try {
			Map<String,Object> parametros = new HashMap<String,Object>();
			parametros.put("vinculacion", vinculacion);
			InputStream jasperSin = this.getClass().getClassLoader().getResourceAsStream("vinculacion.jasper");
		    JasperReport jasperCompilado = (JasperReport) JRLoader.loadObject(jasperSin);
			JasperPrint jasperPrint 	 = JasperFillManager.fillReport(jasperCompilado, parametros, new JREmptyDataSource());
			exportReportToPdf = JasperExportManager.exportReportToPdf(jasperPrint);
			
			OutputStream out = new FileOutputStream("C:/Users/David/Documents/reporte.pdf");
			out.write(exportReportToPdf);
			out.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return exportReportToPdf;
	}
}
