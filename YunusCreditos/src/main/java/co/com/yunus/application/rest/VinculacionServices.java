package co.com.yunus.application.rest;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.com.yunus.application.dto.Vinculacion;
import co.com.yunus.domain.repositories.ITransactionalRepository;
import co.com.yunus.domain.repositories.IVinculacionRepository;
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
	@Named("VinculacionRepositoryImpl")
	private IVinculacionRepository vinculacionRepository;
	
	@POST	
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public byte[] guardar(Vinculacion vinculacion){
		vinculacion.getBeneficiarios().stream().forEach(b->b.setVinculacion(vinculacion));
		vinculacion.getConfirmaciones().stream().forEach(c-> c.setVinculacion(vinculacion));
		transactionalRepository.save(vinculacion);
		return reportById(vinculacion.getId());
	}
	
	@GET
	@Path("buscar/{documento}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Vinculacion> getReport(@PathParam("documento") String documento){
		List<Vinculacion> vinculaciones = vinculacionRepository.findByDocument(documento);
		vinculaciones.forEach(v-> {
			v.getBeneficiarios().stream().forEach(b->b.setVinculacion(null));
			v.getConfirmaciones().stream().forEach(c->c.setVinculacion(null));
			v.getCliente().setVinculaciones(null);
		});
		return vinculaciones;
	}
	
	@GET
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public byte[] reportById(@PathParam("id") Long id){
		Vinculacion vinculacion = vinculacionRepository.findById(id).stream().findAny().get();
		return getBytes(vinculacion);
	}
	
	@GET
	@Path("test")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public byte[] getReportBytes() {
		Vinculacion vinculacion = vinculacionRepository.findByDocument("1047969179").stream().findAny().get();;
		return getBytes(vinculacion);
	}

	private byte[] getBytes(Vinculacion vinculacion) {
		byte[] exportReportToPdf = null;
		try {
			Map<String,Object> parametros = new HashMap<String,Object>();
			parametros.put("vinculacion", vinculacion);
			InputStream jasperSin = this.getClass().getClassLoader().getResourceAsStream("vinculacion.jasper");
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
