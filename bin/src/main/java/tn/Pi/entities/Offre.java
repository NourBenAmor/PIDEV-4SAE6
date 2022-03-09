package tn.Pi.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Offre {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idOffre;
	private String domaine;
	private String societe;
	private String poste;
	private String competencesdemandes;
	
	@OneToMany (mappedBy = "offre")
	private List<Candidature> candidatures;
	
	@JsonIgnore
	@ManyToOne
	Interest interest;
	
	
	public Offre() {
		super();

	}
	public Offre(Long idOffre, String domaine, String societe, String poste, String competencesdemandes,
			List<Candidature> candidatures, Interest interest) {
		super();
		this.idOffre = idOffre;
		this.domaine = domaine;
		this.societe = societe;
		this.poste = poste;
		this.competencesdemandes = competencesdemandes;
		this.candidatures = candidatures;
		this.interest = interest;
	}
	
	public Long getIdOffre() {
		return idOffre;
	}
	public void setIdOffre(Long idOffre) {
		this.idOffre = idOffre;
	}
	public String getDomaine() {
		return domaine;
	}
	public void setDomaine(String domaine) {
		this.domaine = domaine;
	}
	public String getSociete() {
		return societe;
	}
	public void setSociete(String societe) {
		this.societe = societe;
	}
	public String getPoste() {
		return poste;
	}
	public void setPoste(String poste) {
		this.poste = poste;
	}
	public String getCompetencesdemandes() {
		return competencesdemandes;
	}
	public void setCompetencesdemandes(String competencesdemandes) {
		this.competencesdemandes = competencesdemandes;
	}
	
	public List<Candidature> getCandidatures() {
		return candidatures;
	}
	public void setCandidatures(List<Candidature> candidatures) {
		this.candidatures = candidatures;
	}
	public Interest getInterest() {
		return interest;
	}
	public void setInterest(Interest interest) {
		this.interest = interest;
	}
	@Override
	public String toString() {
		return "Offre [idOffre=" + idOffre + ", domaine=" + domaine + ", societe=" + societe + ", poste=" + poste
				+ ", competencesdemandes=" + competencesdemandes + "]";
	}
	
	
	
	
	

}
