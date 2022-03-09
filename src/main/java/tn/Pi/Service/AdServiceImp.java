package tn.Pi.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.Pi.Repository.AdRepository;
import tn.Pi.entities.Ad;
import tn.Pi.entities.User;




@Service
public class AdServiceImp implements AdService {

	@Autowired
	AdRepository adRepos;
	
	@Override
	public Ad save(Ad b) {
		return adRepos.save(b);
	}

	@Override
	public Ad update(Ad b) {
		return adRepos.save(b);
	}

	@Override
	public void delete(Ad b) {
		adRepos.delete(b);
	}

	@Override
	public void deleteById(Long id) {
		adRepos.deleteById(id);
	}

	@Override
	public Ad get(Long id) {
		return adRepos.findById(id).orElse(null);
	}

	@Override
	public List<Ad> getByUser(User u) {
		return adRepos.findByUser(u);
	}

	@Override
	public List<Ad> getAll() {
		return adRepos.findAll();
	}
	
	@Override
	public int Estimated(Ad ad)
	{
		float cout=ad.getCout();
		int estimation = (int) (cout/0.01);
		return estimation;
	}

}
