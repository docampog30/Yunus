package co.com.yunus.infrastructure.repositories.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import co.com.yunus.application.dto.Vinculacion;
import co.com.yunus.domain.repositories.IVinculacionRepository;
import co.com.yunus.domain.repositories.operations.IRepositoryOperations;

@Named("VinculacionRepositoryImpl")
public class VinculacionRepositoryImpl implements IVinculacionRepository{
	
	@Inject
	private IRepositoryOperations reposotoryImpl;

	@Override
	public List<Vinculacion> findByDocument(String document) {
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("documento", document);
		return reposotoryImpl.listar(Vinculacion.FIND_BY_DOCUMENT, parametros, Vinculacion.class);
	}

	@Override
	public List<Vinculacion> findById(Long idVinculacion) {
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("id", idVinculacion);
		return reposotoryImpl.listar(Vinculacion.FIND_BY_ID, parametros, Vinculacion.class);
	}

}
