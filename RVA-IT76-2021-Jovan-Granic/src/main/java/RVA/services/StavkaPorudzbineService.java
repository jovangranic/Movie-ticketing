package RVA.services;

import java.util.List;
import RVA.models.*;

public interface StavkaPorudzbineService extends CrudService<StavkaPorudzbineService> {

	List<StavkaPorudzbine> getPorudzbinasByPlaceno (boolean placeno);
	
	List<StavkaPorudzbine> getByForeignKey(Artikl artikl);
	
	List<StavkaPorudzbine> getByForeignKey(Porudzbina porudzbina);

}
