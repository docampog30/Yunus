package co.com.yunus.domain.repositories;

import java.util.List;

import co.com.yunus.application.dto.Cliente;

public interface IClientesRepository {
	List<Cliente> getClientByDocument(String document);
}
