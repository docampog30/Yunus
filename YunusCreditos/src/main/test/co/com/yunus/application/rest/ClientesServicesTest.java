package co.com.yunus.application.rest;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import co.com.yunus.application.dto.Cliente;
import co.com.yunus.domain.repositories.IClientesRepository;
import co.com.yunus.exception.AppException;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@RunWith(MockitoJUnitRunner.class)
public class ClientesServicesTest {

	@Mock
	private IClientesRepository clientesRepository;
	
	@InjectMocks
	private ClientesServices service;
	
	@Test(expected=AppException.class)
	public void debeValidarDocumentoRepetido() throws Exception{
		
		PodamFactory factory = new PodamFactoryImpl();
		Cliente cliente = factory.manufacturePojoWithFullData(Cliente.class);
		List<Cliente> clientes = new ArrayList<Cliente>();
		clientes.add(cliente);
		Mockito.when(clientesRepository.getClientByDocument(Mockito.anyString())).thenReturn(clientes);
		service.guardar(cliente);
	}
}
