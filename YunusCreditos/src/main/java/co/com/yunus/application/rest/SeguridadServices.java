package co.com.yunus.application.rest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.com.yunus.application.dto.LoginRequest;
import co.com.yunus.application.dto.LoginResponse;
import co.com.yunus.domain.repositories.ISeguridadRepository;

@Path("seguridad")
public class SeguridadServices {

	@Inject
	private ISeguridadRepository seguridadRepository;
	
	@POST
	@Path("login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public LoginResponse login(LoginRequest request){
		LoginResponse response = new LoginResponse();
		response.setUser(request.getUser());
		boolean rpta = seguridadRepository.checkLogin(request);
		response.setValid(rpta);
		return response;
		
	}
}
