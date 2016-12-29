package co.com.yunus.domain.repositories;

import java.util.List;

import co.com.yunus.application.dto.Credito;

public interface ICreditosRepository {
	void guardarCreditoCliente(List<Credito> creditos);
}
