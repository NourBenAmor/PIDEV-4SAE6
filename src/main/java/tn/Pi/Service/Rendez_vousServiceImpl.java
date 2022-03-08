package tn.Pi.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.Pi.Repository.Rendez_vousRepository;
import tn.Pi.entities.Rendez_vous;


@Service
public class Rendez_vousServiceImpl  implements Rendez_vousService {
	@Autowired
	Rendez_vousRepository rendez_vousRepository;
	@Override
	public void saveRendez_vous(Rendez_vous r) {
		rendez_vousRepository.save(r);	
	}

	@Override
	public void updateRendez_vous(Rendez_vous r) {
		rendez_vousRepository.save(r);	
		
	}

	@Override
	public void deleteRendez_vous(Rendez_vous r) {
		rendez_vousRepository.delete(r);		
	}

	@Override
	public void deleteRendez_vousById(Long id) {
		rendez_vousRepository.deleteById(id);		
	}

	@Override
	public Rendez_vous getRendez_vous(Long id) {
		return rendez_vousRepository.findById(id).get();
	}

	@Override
	public List<Rendez_vous> getAllRendez_vous() {
		return rendez_vousRepository.findAll();
	}

}
