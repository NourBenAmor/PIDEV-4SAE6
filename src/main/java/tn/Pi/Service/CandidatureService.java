package tn.Pi.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import tn.Pi.entities.Candidature;


@Service
public interface CandidatureService {
	void saveCandidature(Candidature c);
	void updateCandidature(Candidature c);
	void deleteCandidature(Candidature c);
	void deleteCandidatureById(Long id);
	Candidature getCandidature(Long id);
	List<Candidature> getAllCandidature();
}
