package tn.Pi.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tn.Pi.entities.Interest;
import tn.Pi.entities.Offre;



public interface OffreRepository extends JpaRepository<Offre, Long> {
	@Query("select o from Offre o where o.interest IN ?1")
	List<Offre> suggestedOffres(List<Interest>userInterests);
	
}
