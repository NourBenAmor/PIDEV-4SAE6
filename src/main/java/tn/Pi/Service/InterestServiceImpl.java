package tn.Pi.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.Pi.Repository.InterestRepository;
import tn.Pi.Repository.OffreRepository;
import tn.Pi.Repository.UserRepository;
import tn.Pi.entities.Interest;
import tn.Pi.entities.Offre;
import tn.Pi.entities.User;


@Service
public class InterestServiceImpl implements InterestService{
	@Autowired
	private UserRepository u;
	@Autowired
	private InterestRepository ir;
	@Autowired
	private OffreRepository o;
	
	@Override
	public void addUserInterest(Long interestId, String email) {
		User user = u.findByEmail(email);
		Interest interest = ir.findById(interestId).orElse(null);
		user.getInterests().add(interest);
		u.save(user);		
	}

	@Override
	public void removeUserInterest(Long interestId, String Name) {
		User user = (User) u.findByfirstNameContains(Name);
		Interest interest = ir.findById(interestId).orElse(null);
		user.getInterests().remove(interest);
		u.save(user);		
	}

	@Override
	public void addOffreInterest(Long interestId, Long offreId) {
		Offre offre = o.findById(interestId).orElse(null);
		Interest interest = ir.findById(interestId).orElse(null);
		offre.setInterest(interest);
		o.save(offre);				
	}

	@Override
	public void removeOffreInterest(Long offreId) {
		Offre offre = o.findById(offreId).orElse(null);
		offre.setInterest(null);
		o.save(offre);				
	}

	@Override
	public Interest addInterest(Interest interest) {
		 ir.save(interest);
		return interest;
	}

}
