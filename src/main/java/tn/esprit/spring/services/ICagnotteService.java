package tn.esprit.spring.services;

import java.util.List;
import java.util.Optional;

import tn.esprit.spring.entities.Cagnotte;



public interface ICagnotteService {
	
	Cagnotte addCagnotte(Cagnotte c);

	List<Cagnotte> getAllCagnotte();

	void deleteCagnotte(int idCagnotte);

	Cagnotte updateCagnotte(Cagnotte c);

	Optional<Cagnotte> getCagnotte_by_ID(int idCagnotte);

}
