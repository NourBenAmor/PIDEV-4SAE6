package tn.Pi.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import tn.Pi.entities.Expert;


@Service
public interface ExpertService {
	void saveExpert(Expert e);
	void updateExpert(Expert e);
	void deleteExpert(Expert e);
	void deleteExpertById(Long id);
	Expert getExpert(Long id);
	List<Expert> getAllExpert();

}
