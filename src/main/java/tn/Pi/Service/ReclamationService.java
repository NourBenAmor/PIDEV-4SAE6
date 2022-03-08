package tn.Pi.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import tn.Pi.entities.Reclamation;



@Service
public interface ReclamationService {
	void saveReclamtion(Reclamation re);
	void updateReclamation(Reclamation re);
	void deleteReclamation(Reclamation re);
	void deleteReclamationById(Long id);
	Reclamation getReclamation(Long id);
	List<Reclamation> getAllReclamation();

}
