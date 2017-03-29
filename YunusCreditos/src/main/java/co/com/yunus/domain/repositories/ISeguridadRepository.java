package co.com.yunus.domain.repositories;

import co.com.yunus.application.dto.LoginRequest;

public interface ISeguridadRepository {

	boolean checkLogin(LoginRequest request);

}
