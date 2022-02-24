package tn.Pi.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.Pi.entities.Certifact;
import tn.Pi.entities.Training;
import tn.Pi.Repository.RepoCertificat;
import tn.Pi.Repository.RepoTrainig;
@Service
public class ServiceYasmineImpl implements IServiceYasmine {

	@Autowired
	RepoTrainig tr;
	@Autowired
	RepoCertificat cr;
	
	@Override
	public void ajouterFormation(Training formation) {
		tr.save(formation);
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
	

}
