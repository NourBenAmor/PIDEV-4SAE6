package tn.Pi.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import tn.Pi.entities.Offre;


@Service
public interface OffreService {
	void saveOffre(Offre o);
	void updateOffre(Offre o);
	void deleteOffre(Offre o);
	void deleteOffreById(Long id);
	Offre getOffre(Long id);
	List<Offre> getAllOffre();
	public List<Offre> suggestOffre(String email);

}
