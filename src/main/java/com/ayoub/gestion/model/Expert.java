package com.ayoub.gestion.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Expert {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idExpert; 
	private String nom;
	private String prenom;
	private String fonction;
	private String email;
	private int numTel;
	private String adresse;
	private String certif;
	@OneToMany (mappedBy = "expert")
	private List<Rendez_vous>Rendez_vous;
	
	
	public Expert() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Expert(String nom, String prenom, String fonction, String email, int numTel, String adresse, String certif) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.fonction = fonction;
		this.email = email;
		this.numTel = numTel;
		this.adresse = adresse;
		this.certif = certif;
	}

	public Long getIdExpert() {
		return idExpert;
	}

	public void setIdExpert(Long idExpert) {
		this.idExpert = idExpert;
	}

	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getFonction() {
		return fonction;
	}
	public void setFonction(String fonction) {
		this.fonction = fonction;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getNumTel() {
		return numTel;
	}
	public void setNumTel(int numTel) {
		this.numTel = numTel;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getCertif() {
		return certif;
	}
	public void setCertif(String certif) {
		this.certif = certif;
	}

	@Override
	public String toString() {
		return "Expert [idExpert=" + idExpert + ", nom=" + nom + ", prenom=" + prenom + ", fonction=" + fonction
				+ ", email=" + email + ", numTel=" + numTel + ", adresse=" + adresse + ", certif=" + certif + "]";
	}

	public Expert(Long idExpert, String nom, String prenom, String fonction, String email, int numTel, String adresse,
			String certif, List<com.ayoub.gestion.model.Rendez_vous> rendez_vous) {
		super();
		this.idExpert = idExpert;
		this.nom = nom;
		this.prenom = prenom;
		this.fonction = fonction;
		this.email = email;
		this.numTel = numTel;
		this.adresse = adresse;
		this.certif = certif;
		Rendez_vous = rendez_vous;
	}
	
	
	
}
