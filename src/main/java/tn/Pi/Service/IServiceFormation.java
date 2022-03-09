package tn.Pi.Service;

import java.util.List;
import java.util.Map;

import tn.Pi.entities.BestWorstTrainig;
import tn.Pi.entities.Certifact;
import tn.Pi.entities.Training;
import tn.Pi.entities.UserTraining;

public interface IServiceFormation {
	public void ajouterFormation(Training formation);
	public Training ModifierFormation(Training formation, Long id);
	public void SupprimerFormation(Long id);
	public List<Training> ListedesFormation();
	
	
	public void ajouterCertificat(Certifact c);
	public Certifact ModifierCertificat(Certifact c,Long id);
	public void supprimerCertificat(Long id);
	public List<Certifact> ListedesCertificat();
	//public void dislikeTraining(Long idFormation, Long id);
	//public void likeAtraining(Long idFormation , Long id);
	public BestWorstTrainig besttraining();	
	public BestWorstTrainig worsttraining();
	public List<UserTraining> searchbyname(String name);
	public void pdf(Long id);
	public List<Training> searchTraining(String word);
}
