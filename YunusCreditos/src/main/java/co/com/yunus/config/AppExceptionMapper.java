package co.com.yunus.config;

import javax.inject.Singleton;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import co.com.yunus.exception.AppException;

@Provider
@Singleton

public class AppExceptionMapper implements ExceptionMapper<AppException> {

	public Response toResponse(AppException ex) {
		return Response.status(500)
				.header("message", ex.getMessage())
				.build();
	}

}
