package co.com.yunus.domain.repositories;

import java.util.List;

import co.com.yunus.application.dto.Vinculacion;

public interface IVinculacionRepository {

	List<Vinculacion> findByDocument(String document);
	List<Vinculacion> findById(Long idVinculacion);
}
