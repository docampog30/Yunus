package co.com.yunus.application.rest;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.com.yunus.application.dto.Cliente;
import co.com.yunus.domain.repositories.IClientesRepository;
import co.com.yunus.domain.repositories.ITransactionalRepository;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

@Path("cliente")
public class ClientesServices {

	@Inject
	private IClientesRepository clientesRepository;
	
	@Inject
	private ITransactionalRepository transactionalRepository;
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void guardar(Cliente cliente) throws Exception{
		transactionalRepository.save(cliente);
		byte[] exportReportToPdf;
		Map<String,Object> parametros = new HashMap<String,Object>();
		parametros.put("cliente", cliente);
		InputStream jasperSin = this.getClass().getClassLoader().getResourceAsStream("vinculacion.jasper");
        JasperReport jasperCompilado = (JasperReport) JRLoader.loadObject(jasperSin);
		JasperPrint jasperPrint 	 = JasperFillManager.fillReport(jasperCompilado, parametros, new JREmptyDataSource());
		exportReportToPdf = JasperExportManager.exportReportToPdf(jasperPrint);
		
		OutputStream out = new FileOutputStream("C:/Users/David/Documents/reporte.pdf");
		out.write(exportReportToPdf);
		out.close();
	}
	
	@GET
	@Path("{documento}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Cliente> getCliente(@PathParam("documento") String documento){
		return clientesRepository.getClientByDocument(documento);
	}
	
}
