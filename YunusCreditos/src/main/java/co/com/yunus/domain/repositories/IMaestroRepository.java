package co.com.yunus.domain.repositories;

import java.util.List;

import co.com.yunus.application.dto.Maestro;

public interface IMaestroRepository {

	List<Maestro> buscarMaestrosPorParent(Long idParent);

}
