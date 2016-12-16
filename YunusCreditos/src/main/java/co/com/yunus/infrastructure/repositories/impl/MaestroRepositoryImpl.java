package co.com.yunus.infrastructure.repositories.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import co.com.yunus.application.dto.Maestro;
import co.com.yunus.domain.repositories.IMaestroRepository;
import co.com.yunus.domain.repositories.operations.IRepositoryOperations;

@Named("MaestroRepositoryImpl")
public class MaestroRepositoryImpl implements IMaestroRepository {
	
	@Inject
	private IRepositoryOperations databaseOperations;

	public List<Maestro> buscarMaestrosPorParent(Long idParent) {
		
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("idparent", idParent);

		return databaseOperations.listar(Maestro.FIND_BY_PARENT, parametros, Maestro.class);
	}

}
