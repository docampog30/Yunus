package co.com.yunus.infrastructure.repositories.impl;

import java.util.ArrayList;
import java.util.List;

import co.com.yunus.application.dto.Vinculacion;
import co.com.yunus.domain.repositories.IVinculacionRepository;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

public class VinculacionMockImpl implements IVinculacionRepository{

	@Override
	public List<Vinculacion> findByDocument(String document) {
		PodamFactory factory = new PodamFactoryImpl();
		List<Vinculacion> list = new ArrayList<Vinculacion>();
		Vinculacion myPojo = factory.manufacturePojo(Vinculacion.class);
		Vinculacion myPojo2 = factory.manufacturePojo(Vinculacion.class);
		Vinculacion myPojo3 = factory.manufacturePojo(Vinculacion.class);
		list.add(myPojo);
		list.add(myPojo2);
		list.add(myPojo3);
		return list;
	}

	@Override
	public List<Vinculacion> findById(Long idVinculacion) {
		PodamFactory factory = new PodamFactoryImpl();
		List<Vinculacion> list = new ArrayList<Vinculacion>();
		Vinculacion myPojo = factory.manufacturePojo(Vinculacion.class);
		list.add(myPojo);
		return list;
	}

}
