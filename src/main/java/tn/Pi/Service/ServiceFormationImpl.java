package tn.Pi.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.Pi.entities.BestWorstTrainig;
import tn.Pi.entities.Certifact;
import tn.Pi.entities.Training;
import tn.Pi.entities.User;
import tn.Pi.entities.UserTraining;
import tn.Pi.Repository.RepoCertificat;
import tn.Pi.Repository.RepoTrainig;
import tn.Pi.Repository.UserRepository;
@Service
public class ServiceFormationImpl implements IServiceFormation {

	@Autowired
	RepoTrainig tr;
	@Autowired
	RepoCertificat cr;
	@Autowired
	UserRepository ur;
	@Autowired
	EmailSenderService service;
	
	@Autowired
	ServiceUser su;
	
	@Override
	public void ajouterFormation(Training formation) {
		tr.save(formation);
		List<User> users = new ArrayList<>();
		users = ur.findAll();
		for(User u: users)
		{
			service.sendSimpleEmail(u.getEmail(), "bonjour", "teseiufgalfyulaft ");
		}
			}

	@Override
	public Training ModifierFormation(Training formation, Long id) {
		long idd= formation.getIdFormation();
		if(id==idd)
		{
			Optional<Training> formations = tr.findById(id);

			if(formations!=null)
				tr.save(formation);
		}
		return formation;
	}

	@Override
	public void SupprimerFormation(Long id) {
	    
		tr.deleteById(id);
		
	}

	@Override
	public List<Training> ListedesFormation() {
		
		return (List<Training>)tr.findAll();
	}

	@Override
	public void ajouterCertificat(Certifact c) {
		cr.save(c);
		
	}
	

	@Override
	public Certifact ModifierCertificat(Certifact c, Long id) {
		long idd= c.getId();
		if(id==idd)
		{
			Optional<Certifact> certificats = cr.findById(id);

			if(certificats!=null)
			cr.save(c);
		}
		return c;
	}

	@Override
	public void supprimerCertificat(Long id) {
		cr.deleteById(id);
		
	}

	@Override
	public List<Certifact> ListedesCertificat() {
		return (List<Certifact>)cr.findAll();
	}

	@Override
	public void dislikeTraining(Long idFormation, Long id) {
		Training t =tr.findById(idFormation).orElseGet(null);
		User u = ur.findById(id).orElseGet(null);
		Set<User> l = t.getUserdeslike();
		if(t.getUserlike().contains(u))
		{
			t.getUserlike().remove(u);
			l.add(u);
			t.setUserdeslike(l);
			}
		else
		{	l.add(u);}
		tr.save(t);
		
	}

	@Override
	public void likeAtraining(Long idFormation, Long id) {
		Training t = tr.findById(idFormation).orElseGet(null);
		User u = ur.findById(id).orElseGet(null);
		Set<User> l = t.getUserlike();
		if(t.getUserdeslike().contains(u))
		{
			t.getUserdeslike().remove(u);
			l.add(u);
			t.setUserlike(l); 
			}
		else
		{	if(t.getUserlike().contains(u)) {
			t.getUserlike().remove(u);	
		}
		else {l.add(u);}
		}
		tr.save(t);
	 
		}
		
	

	@Override
	public BestWorstTrainig besttraining() {
		// TODO Auto-generated method stub
		return tr.BestTraining();
	}

	@Override
	public BestWorstTrainig worsttraining() {
		// TODO Auto-generated method stub
		return tr.WorstTraining();
	}

	@Override
	public List<UserTraining> searchbyname(String name) {
		
		return tr.trainingtitle(name);
		//return null;
	}
	
}
