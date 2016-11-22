package co.com.yunus.domain.repositories;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.jpa.repository.JpaRepository;

import co.com.yunus.application.dto.Persona;

@CacheEvict(value="Persona", allEntries = true)
public interface IRepositoryPersonas extends JpaRepository<Persona,Long> {
	List<Persona> findByTipoNotNullAndEstado(boolean b);
	List<Persona> findByTipoNotNull();
	Persona findByTipo(String tipo);
}
