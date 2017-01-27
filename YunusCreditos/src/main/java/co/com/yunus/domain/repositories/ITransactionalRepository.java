package co.com.yunus.domain.repositories;

import java.util.List;
import java.util.Map;

public interface ITransactionalRepository {

	<T>	void save(T object);

	<T>	void update(T object);

	<T> void delete(T object);
	
	<T> void save(List<T> object);
	<T> void update(List<T> object);

	void executeSQL(String sql, Map<String, Object> parametros);
}
