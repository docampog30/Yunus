package co.com.yunus.domain.repositories.database;

import java.util.List;
import java.util.Map;

public interface IDatabaseOperations {
	<T> List<T> listar(String namedQueryName, Map<String, Object> parametros,Class<T> clazz);
	<T> void save(T object);
	<T> void update(T object);
	<T> void delete(T object);
}