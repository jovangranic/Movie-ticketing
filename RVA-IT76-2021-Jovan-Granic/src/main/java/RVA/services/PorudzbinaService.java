package RVA.services;

import java.util.List;

import RVA.models.Dobavljac;
import RVA.models.Porudzbina;

public interface PorudzbinaService extends CrudService<Porudzbina> {

	List<Porudzbina> getPorudzbinasByPlaceno(boolean placeno);
	
	List<Porudzbina> getForeignKey(Dobavljac dobavljac);
	
}
