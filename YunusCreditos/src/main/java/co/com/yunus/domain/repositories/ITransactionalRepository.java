package co.com.yunus.domain.repositories;



public interface ITransactionalRepository {

	<T>	void save(T object);

	<T>	void update(T object);

	<T> void delete(T object);
}
