package RVA.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import RVA.models.Bioskop;

public interface BioskopRepository extends JpaRepository<Bioskop, Integer>{

	List<Bioskop> findByAdresaContainingIgnoreCase(String adresa);
}