package co.com.yunus.infrastructure.repositories.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import co.com.yunus.application.dto.Maestro;
import co.com.yunus.domain.repositories.IMaestroRepository;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@Named("maestrosMock")
public class MaestrosMockImpl implements IMaestroRepository{

	@Override
	public List<Maestro> buscarMaestrosPorParent(Long idParent) {
		PodamFactory factory = new PodamFactoryImpl();
		List<Maestro> list = new ArrayList<Maestro>();
		Maestro myPojo = factory.manufacturePojo(Maestro.class);
		Maestro myPojo2 = factory.manufacturePojo(Maestro.class);
		Maestro myPojo3 = factory.manufacturePojo(Maestro.class);
		list.add(myPojo);
		list.add(myPojo2);
		list.add(myPojo3);
		return list;
	}

}
