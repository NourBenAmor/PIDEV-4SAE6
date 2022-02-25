package com.ayoub.gestion.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;



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
	
	
	public Offre() {
		super();

	}
	public Offre(String domaine, String societe, String poste, String competencesdemandes) {
		super();
		this.domaine = domaine;
		this.societe = societe;
		this.poste = poste;
		this.competencesdemandes = competencesdemandes;
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
	@Override
	public String toString() {
		return "Offre [idOffre=" + idOffre + ", domaine=" + domaine + ", societe=" + societe + ", poste=" + poste
				+ ", competencesdemandes=" + competencesdemandes + "]";
	}
	
	
	
	
	

}
