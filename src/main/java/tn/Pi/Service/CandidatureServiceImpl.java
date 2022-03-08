package tn.Pi.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.Pi.Repository.CandidatureRepository;
import tn.Pi.entities.Candidature;




@Service
public class CandidatureServiceImpl implements CandidatureService{

	@Autowired
	CandidatureRepository candidatureRepository;
	@Override
	public void saveCandidature(Candidature c) {
		candidatureRepository.save(c);
	}

	@Override
	public void updateCandidature(Candidature c) {
		candidatureRepository.save(c);
		
	}

	@Override
	public void deleteCandidature(Candidature c) {
		candidatureRepository.save(c);
		
	}

	@Override
	public void deleteCandidatureById(Long id) {
		candidatureRepository.deleteById(id);		
	}

	@Override
	public Candidature getCandidature(Long id) {
		return candidatureRepository.findById(id).get();
	}

	@Override
	public List<Candidature> getAllCandidature() {
	return	candidatureRepository.findAll();
	}

}
