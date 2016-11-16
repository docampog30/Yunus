package co.com.yunus.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.yunus.application.dto.Persona;

public interface IRepositoryPersonas extends JpaRepository<Persona,Long> {
	List<Persona> findByTipoNotNullAndEstado(boolean b);

	List<Persona> findByTipoNotNull();
}
