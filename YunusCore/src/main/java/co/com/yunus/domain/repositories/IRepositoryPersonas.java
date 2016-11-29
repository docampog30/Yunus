package co.com.yunus.domain.repositories;

import java.util.List;

import co.com.yunus.application.dto.Persona;


public interface IRepositoryPersonas{
	List<Persona> findByTipoNotNullAndEstado(boolean b);
	List<Persona> findByTipoNotNull();
	Persona findByTipo(String tipo);
	Persona findOne(Long id);
}
