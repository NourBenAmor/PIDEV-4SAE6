package com.ayoub.gestion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayoub.gestion.model.Expert;
import com.ayoub.gestion.repos.ExpertRepository;

@Service
public class ExpertServiceImpl implements ExpertService {
	
	@Autowired
	ExpertRepository expertRepository; 
	@Override
	public void saveExpert(Expert e) {
		 expertRepository.save(e);
		}

	@Override
	public void updateExpert(Expert e) {
		 expertRepository.save(e);

	}

	@Override
	public void deleteExpert(Expert e) {
		expertRepository.delete(e);
	}

	@Override
	public void deleteExpertById(Long id) {
		expertRepository.deleteById(id);		
	}

	@Override
	public Expert getExpert(Long id) {
		return expertRepository.findById(id).get();
	}

	@Override
	public List<Expert> getAllExpert() {
		return expertRepository.findAll();
	}

}
