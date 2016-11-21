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
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import co.com.yunus.application.dto.Parroquia;
import co.com.yunus.application.dto.Partida;
import co.com.yunus.application.dto.PartidasFilter;
import co.com.yunus.application.dto.Persona;
import co.com.yunus.domain.repositories.IRepositoryParroquias;
import co.com.yunus.domain.repositories.IRepositoryPartidas;
import co.com.yunus.domain.repositories.IRepositoryPersonas;

@Path("reportes")
public class ReportesServices {
		
	@Inject
	private IRepositoryPartidas partidasRepository;

	@Inject
	private IRepositoryParroquias parroquiasRepository;
	
	@Inject
	private IRepositoryPersonas personasRepository;

	
	@GET
	@Path("bautizo/{id}")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public byte[] generarPartidaBautizo(@PathParam("id") Long id) throws Exception{
		Partida partida = partidasRepository.findOne(id);
		Map<String, Object> parametros = getParametros(partida);
		return getReportBytes(parametros,"bautizo.jrxml");
	}
	
	@GET
	@Path("matrimonio/{id}")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public byte[] generarPartidaMatrimonio(@PathParam("id") Long id) throws Exception{
		Partida partida = partidasRepository.findOne(id);
		Map<String, Object> parametros = getParametros(partida);
		return getReportBytes(parametros, "matrimonios.jrxml");
	}
	
	@GET
	@Path("confirmacion/{id}")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public byte[] generarPartidaConfirmacion(@PathParam("id") Long id) throws Exception{
		Partida partida = partidasRepository.findOne(id);
		Map<String, Object> parametros = getParametros(partida);
		return getReportBytes(parametros, "confirmacion.jrxml");
	}
	@POST
	@Path("buscar")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Partida> buscar(PartidasFilter filter){
		List<Partida> byDocumento 		= partidasRepository.findPartidas(filter.getDocumento());
		
//		List<Partida> byFechaBetween 	= partidasRepository.findByFechaBetween(filter.getFechaIni(), filter.getFechaFin());
//		Stream<Partida> partidas = Stream.concat(byDocumento.stream(), byFechaBetween.stream()).distinct();
//		
//		List<Object> collect = partidas.collect(Collectors.toList());
		
		return byDocumento;
	}
	
	private Map<String, Object> getParametros(Partida partida) {
		
		Parroquia parroquia = parroquiasRepository.findAll().get(0);
		Persona parroco = personasRepository.findByTipo("P");
		
		Map<String,Object> parametros = new HashMap<String,Object>();
		parametros.put("partida", parroquia.getDiocesis());
		parametros.put("parroquia", parroquia.getNombre());
		parametros.put("direccionParroquia", parroquia.getDireccion());
		parametros.put("tel", parroquia.getTelefono());
		parametros.put("presbitero", "presbitero");
		parametros.put("parroco", parroco.getNombres().concat(" ").concat(parroco.getApellidos()));
		parametros.put("parrocoPie", parroquia.getPie());
		parametros.put("ubicacion", parroquia.getDireccion());
		return parametros;
	}
	
	private byte[] getReportBytes(Map<String, Object> parametros,String reportName) throws JRException, Exception {
		byte[] exportReportToPdf;
		InputStream jasperSin = this.getClass().getClassLoader().getResourceAsStream(reportName);
        JasperReport jasperCompilado = JasperCompileManager.compileReport(jasperSin);
		JasperPrint jasperPrint 	 = JasperFillManager.fillReport(jasperCompilado, parametros, new JREmptyDataSource());
		exportReportToPdf = JasperExportManager.exportReportToPdf(jasperPrint);
		
		OutputStream out = new FileOutputStream("C:/Users/David/Documents/reporte.pdf");
		out.write(exportReportToPdf);
		out.close();
		
		return exportReportToPdf;
	}
	
	
	
}
