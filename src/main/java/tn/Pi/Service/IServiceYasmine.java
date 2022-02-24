package tn.Pi.Service;

import java.util.List;

import tn.Pi.entities.Certifact;
import tn.Pi.entities.Training;

public interface IServiceYasmine {
	public void ajouterFormation(Training formation);
	public Training ModifierFormation(Training formation, Long id);
	public void SupprimerFormation(Long id);
	public List<Training> ListedesFormation();
	
	
	public void ajouterCertificat(Certifact c);
	public Certifact ModifierCertificat(Certifact c,Long id);
	public void supprimerCertificat(Long id);
	public List<Certifact> ListedesCertificat();
	
}
