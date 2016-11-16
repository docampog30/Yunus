package co.com.yunus.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.yunus.application.dto.Partida;
import co.com.yunus.application.enums.TipoSacramento;

public interface IRepositoryPartidas extends JpaRepository<Partida,Long>{

	List<Partida> findByNumeroAndTipo(String numero, TipoSacramento tipo);
}
