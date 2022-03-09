package tn.Pi.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class Rendez_vous {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idRendez_vous;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateDebut;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateFin;
	@Enumerated(EnumType.STRING)
	private Type type;
	
	@ManyToOne
	@JsonIgnore
	private Expert expert;
	@ManyToOne
	@JsonIgnore
	private User user;
	
	
	
	public Date getDateDebut() {
		return dateDebut;
	}
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}
	public Date getDateFin() {
		return dateFin;
	}
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}
	public Rendez_vous() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Rendez_vous [idRendez_vous=" + idRendez_vous + ", expert=" + expert + ", dateDebut=" + dateDebut + ",dateFin=" + dateFin+ ", type="
				+ type + "]";
	}
	public Rendez_vous(Expert expert, User user, Date dateDebut,Date dateFin, Type type) {
		super();
		this.expert = expert;
		this.user = user;
		this.dateDebut = dateDebut;
		this.dateFin=dateFin;
		this.type = type;
	}
	public Long getIdRendez_vous() {
		return idRendez_vous;
	}
	public void setIdRendez_vous(Long idRendez_vous) {
		this.idRendez_vous = idRendez_vous;
	}
	public Expert getExpert() {
		return expert;
	}
	public void setExpert(Expert expert) {
		this.expert = expert;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}

	
	
	
}
