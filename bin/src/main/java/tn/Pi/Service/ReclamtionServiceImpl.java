package tn.Pi.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.Pi.Repository.ReclamationRepository;
import tn.Pi.entities.Reclamation;


@Service
public class ReclamtionServiceImpl implements ReclamationService {
@Autowired
ReclamationRepository reclamationRepository;
	@Override
	public void saveReclamtion(Reclamation re) {
		reclamationRepository.save(re);
	}

	@Override
	public void updateReclamation(Reclamation re) {
		reclamationRepository.save(re);

	}

	@Override
	public void deleteReclamation(Reclamation re) {
		reclamationRepository.delete(re);
	}

	@Override
	public void deleteReclamationById(Long id) {
		reclamationRepository.deleteById(id);
	}

	@Override
	public Reclamation getReclamation(Long id) {
		return reclamationRepository.findById(id).get();
	}

	@Override
	public List<Reclamation> getAllReclamation() {
		return reclamationRepository.findAll() ;
	}

}
