package RVA.services;

import java.util.List;

import org.springframework.stereotype.Service;

import RVA.models.Film;

@Service
public interface FilmService extends CrudService<Film> {

	List<Film> getFilmsByNaziv(String naziv);
}