package RVA.services;

import java.util.*;

public interface CrudService<T> {
	//<T> oznacava bilo koji tip obelezja (artikl, dobavljac...)
	
	List<T> getAll();
	
	boolean existsById(int id);
	
	T create (T t);
	
	Optional<T> update(T t, int id);
	
	void delete(int id);

}
