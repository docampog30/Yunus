package co.com.yunus.infrastructure.repositories.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import co.com.yunus.application.dto.Cliente;
import co.com.yunus.domain.repositories.IClientesRepository;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@Named("clienteMock")
public class ClienteMockImpl implements IClientesRepository {

	@Override
	public List<Cliente> getClientByDocument(String document) {
		PodamFactory factory = new PodamFactoryImpl();
		List<Cliente> list = new ArrayList<>();
		Cliente myPojo = factory.manufacturePojo(Cliente.class);
		list.add(myPojo);
		return list;
	}

}
