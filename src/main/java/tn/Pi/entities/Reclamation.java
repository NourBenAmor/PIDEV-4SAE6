package tn.Pi.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.util.Date;
@Entity
public class Reclamation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idReclamation;
	private String sujet;
	private String description ;
	private Date date;
	@Enumerated(EnumType.STRING)
	private Statut statut;
	
	
	public Reclamation() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Reclamation [idReclamation=" + idReclamation + ", sujet=" + sujet + ", description=" + description
				+ ", date=" + date + ", statut=" + statut + "]";
	}
	public Reclamation(String sujet, String description, Date date, Statut statut) {
		super();
		this.sujet = sujet;
		this.description = description;
		this.date = date;
		this.statut = statut;
	}
	
	public Long getIdReclamation() {
		return idReclamation;
	}
	public void setIdReclamation(Long idReclamation) {
		this.idReclamation = idReclamation;
	}
	public String getSujet() {
		return sujet;
	}
	public void setSujet(String sujet) {
		this.sujet = sujet;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Statut getStatut() {
		return statut;
	}
	public void setStatut(Statut statut) {
		this.statut = statut;
	} 

}
