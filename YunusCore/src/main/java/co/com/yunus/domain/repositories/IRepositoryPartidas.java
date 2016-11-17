package co.com.yunus.domain.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.com.yunus.application.dto.Partida;
import co.com.yunus.application.enums.TipoSacramento;

public interface IRepositoryPartidas extends JpaRepository<Partida,Long>{

	List<Partida> findByNumeroAndTipo(String numero, TipoSacramento tipo);

	@Query(" 	 SELECT p FROM Partida p left join p.persona2 pe "
			+  " WHERE p.persona1.cedula LIKE CONCAT('%',:documento,'%') AND (p.persona2 IS NULL OR (p.persona2.cedula) LIKE CONCAT('%',:documento,'%')) ")
	List<Partida> findPartidas(@Param("documento") String documento);

	List<Partida> findByFechaBetween(Date start, Date end);
}
