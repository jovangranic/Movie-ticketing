package RVA.services;

import java.util.List;

import org.springframework.stereotype.Service;

import RVA.models.Bioskop;

@Service
public interface BioskopService extends CrudService<Bioskop>{
	List<Bioskop> getBioskopsByAdresa(String adresa);

}