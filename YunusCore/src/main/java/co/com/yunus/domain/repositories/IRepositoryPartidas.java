package co.com.yunus.domain.repositories;

import java.util.List;

import org.springframework.data.repository.query.Param;

import co.com.yunus.application.dto.Partida;
import co.com.yunus.application.enums.TipoSacramento;

public interface IRepositoryPartidas {

	List<Partida> findByNumeroAndTipo(String numero, TipoSacramento tipo);

	//@Query("SELECT p FROM Partida p WHERE p.persona1.cedula LIKE CONCAT('%',:documento,'%')")
	List<Partida> findPartidasByP1(@Param("documento") String documento);
	
	//@Query("SELECT p FROM Partida p WHERE p.persona2.cedula LIKE CONCAT('%',:documento,'%')")
	List<Partida> findPartidasByP2(@Param("documento") String documento);

	Partida findOne(Long id);
}
