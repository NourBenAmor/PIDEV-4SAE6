package com.ayoub.gestion.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ayoub.gestion.model.Expert;
@Service
public interface ExpertService {
	void saveExpert(Expert e);
	void updateExpert(Expert e);
	void deleteExpert(Expert e);
	void deleteExpertById(Long id);
	Expert getExpert(Long id);
	List<Expert> getAllExpert();

}
