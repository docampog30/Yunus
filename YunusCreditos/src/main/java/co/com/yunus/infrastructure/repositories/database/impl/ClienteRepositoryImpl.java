package co.com.yunus.infrastructure.repositories.database.impl;

import java.util.ArrayList;
import java.util.List;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import co.com.yunus.application.dto.Cliente;
import co.com.yunus.domain.repositories.IClientesRepository;

public class ClienteRepositoryImpl implements IClientesRepository {

	public List<Cliente> getClientByDocument(String document) {
		List<Cliente> clientes = new ArrayList<Cliente>();
		PodamFactory factory = new PodamFactoryImpl();
		Cliente cliente = factory.manufacturePojo(Cliente.class);
		clientes.add(cliente);
		return clientes;
	}

}
