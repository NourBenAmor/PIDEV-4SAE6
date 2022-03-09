package tn.Pi.Service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.Pi.Repository.ExpertRepository;
import tn.Pi.Repository.Rendez_vousRepository;
import tn.Pi.Repository.UserRepository;
import tn.Pi.entities.Expert;
import tn.Pi.entities.Rendez_vous;
import tn.Pi.entities.User;


@Service
public class Rendez_vousServiceImpl  implements Rendez_vousService {
	@Autowired
	Rendez_vousRepository rendez_vousRepository;
	@Autowired
	ExpertRepository exRepo;
	@Autowired
	UserRepository uRepo;
	@Override
	public String saveRendez_vous(Rendez_vous r,Long id) {
		Expert ex = exRepo.findById(id).orElse(null);
		r.setExpert(ex);
		rendez_vousRepository.save(r);
		return "Successfuly aded"; 	
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

	@Override
	public String prendreRendezVous(Long userId,Long expertId) {
		User u = uRepo.findById(userId).orElse(null);

		Expert ex = exRepo.findById(expertId).orElse(null);
		List<Rendez_vous>r = ex.getRendez_vous();
		r.get(0).setUser(u);
		rendez_vousRepository.save(r.get(0));
		return "RendezVous Prise";
	}

}
