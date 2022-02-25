package com.ayoub.gestion.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ayoub.gestion.model.Candidature;
@Service
public interface CandidatureService {
	void saveCandidature(Candidature c);
	void updateCandidature(Candidature c);
	void deleteCandidature(Candidature c);
	void deleteCandidatureById(Long id);
	Candidature getCandidature(Long id);
	List<Candidature> getAllCandidature();
}
