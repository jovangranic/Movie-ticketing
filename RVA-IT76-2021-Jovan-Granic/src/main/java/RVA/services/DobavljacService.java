package RVA.services;

import java.util.List;

import RVA.models.Dobavljac;

public interface DobavljacService extends CrudService<Dobavljac> {

	List<Dobavljac> getDobavljacsByAdresa(String adresa);
	
}
