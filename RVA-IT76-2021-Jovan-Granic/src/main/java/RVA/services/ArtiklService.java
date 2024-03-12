package RVA.services;

import java.util.List;
import RVA.models.Artikl;

public interface ArtiklService extends CrudService<Artikl> {

	List<Artikl> getArtiklsByNaziv(String naziv);
	
	

}
