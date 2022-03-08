package tn.Pi.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Candidature {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCandidature;
	@ManyToOne
	private User Nomducandidat;
	@ManyToOne
	private Offre offre;
	@Enumerated(EnumType.STRING)
	private Etat etat;
	private String cv;
	private String lettredemotivation;
	
	
	
	
	public Candidature() {
		super();
		// TODO Auto-generated constructor stub
		
	}
	
	
	
	public Candidature(User nomducandidat, Offre offre, Etat etat, String cv, String lettredemotivation) {
		super();
		Nomducandidat = nomducandidat;
		this.offre = offre;
		this.etat = etat;
		this.cv = cv;
		this.lettredemotivation = lettredemotivation;
	}



	public Long getIdCandidature() {
		return idCandidature;
	}
	public void setIdCandidature(Long idCandidature) {
		this.idCandidature = idCandidature;
	}
	public User getNomducandidat() {
		return Nomducandidat;
	}
	public void setNomducandidat(User nomducandidat) {
		Nomducandidat = nomducandidat;
	}
	public Offre getOffre() {
		return offre;
	}
	public void setOffre(Offre offre) {
		this.offre = offre;
	}
	public Etat getEtat() {
		return etat;
	}
	public void setEtat(Etat etat) {
		this.etat = etat;
	}
	public String getCv() {
		return cv;
	}
	public void setCv(String cv) {
		this.cv = cv;
	}
	public String getLettredemotivation() {
		return lettredemotivation;
	}
	public void setLettredemotivation(String lettredemotivation) {
		this.lettredemotivation = lettredemotivation;
	}



	@Override
	public String toString() {
		return "Candidature [idCandidature=" + idCandidature + ", Nomducandidat=" + Nomducandidat + ", offre=" + offre
				+ ", etat=" + etat + ", cv=" + cv + ", lettredemotivation=" + lettredemotivation + "]";
	}
	
	
	
	
	
		
		
}
	
	
	
	
	
