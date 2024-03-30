package RVA.services;

import java.util.List;

import org.springframework.stereotype.Service;

import RVA.models.Bioskop;
import RVA.models.Sala;

@Service
public interface SalaService extends CrudService<Sala>{
	
	List<Sala> getSalasByKapacitet(int kapacitet);
	
	List<Sala> getByForeignKey(Bioskop bioskop);

}