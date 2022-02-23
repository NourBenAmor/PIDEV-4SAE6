package tn.esprit.spring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Cagnotte;
import tn.esprit.spring.repository.CagnotteRepository;





@Service
public class CagnotteServiceImpl implements ICagnotteService {

	@Autowired 
	private CagnotteRepository cagnotteRepository;
	
	
	
	@Override
	public Cagnotte addCagnotte(Cagnotte c) {
		
		return cagnotteRepository.save(c);
		
	}

	@Override
	public List<Cagnotte> getAllCagnotte() {
		return (List<Cagnotte>)cagnotteRepository.findAll();
	}
	
	@Override
	public void deleteCagnotte(int idCagnotte) {
		cagnotteRepository.deleteById(idCagnotte);
		
	}
	
	@Override
	public Cagnotte updateCagnotte(Cagnotte c) {
		
		return cagnotteRepository.save(c);
	}
	
	@Override
	public Optional<Cagnotte> getCagnotte_by_ID(int idCagnotte) {
		return cagnotteRepository.findById(idCagnotte);
	}
}
