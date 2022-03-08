package tn.Pi.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.Pi.Repository.OffreRepository;
import tn.Pi.entities.Offre;


@Service
public class OffreServiceImpl implements OffreService {

	@Autowired
	OffreRepository offreRepository; 
	@Override
	public void saveOffre(Offre o) {
		offreRepository.save(o);
	}

	@Override
	public void updateOffre(Offre o) {
		offreRepository.save(o);
	}

	@Override
	public void deleteOffre(Offre o) {
		offreRepository.delete(o);
	}

	@Override
	public void deleteOffreById(Long id) {
		offreRepository.deleteById(id);
	}

	@Override
	public Offre getOffre(Long id) {
		return offreRepository.findById(id).get();
	}

	@Override
	public List<Offre> getAllOffre() {
		return offreRepository.findAll();
	}

}
