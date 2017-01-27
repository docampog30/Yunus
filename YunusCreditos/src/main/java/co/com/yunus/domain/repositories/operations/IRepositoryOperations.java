package co.com.yunus.domain.repositories.operations;

import java.util.List;
import java.util.Map;

public interface IRepositoryOperations {
	<T> List<T> listar(String namedQueryName, Map<String, Object> parametros,Class<T> clazz);
	<T> void save(T object);
	<T> void update(T object);
	<T> void delete(T object);
	<T> void save(List<T> object);
	<T> void update(List<T> object);
	void executeSQL(String sql,Map<String, Object> parametros);
}