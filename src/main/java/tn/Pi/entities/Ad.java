package tn.Pi.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Ad {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idAd;
	@Enumerated(EnumType.STRING)
	private canal canal;
	private Date dateDebut;
	private Date dateFin;
	private int nbrInitVues;
	private int nbrCibleVues;
	private float cout;
	private String type;
	private String file;
	private String link;
	private String cible;
	@ManyToOne
	private User user;
	
	public Ad() {
		super();
	}
	
	public Ad(canal canal, Date dateDebut, Date dateFin, int nbrInitVues, int nbrCibleVues,
			float cout, String type, String file, String link, String cible, User user) {
		super();
		this.canal = canal;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.nbrInitVues = nbrInitVues;
		this.nbrCibleVues = nbrCibleVues;
		this.cout = cout;
		this.type = type;
		this.file = file;
		this.link = link;
		this.cible = cible;
		this.user = user;
	}





	public Long getIdAd() {
		return idAd;
	}
	public void setIdAd(Long idAd) {
		this.idAd = idAd;
	}
	public canal getCanal() {
		return canal;
	}
	public void setCanal(canal canal) {
		this.canal = canal;
	}
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
	public int getNbrInitVues() {
		return nbrInitVues;
	}
	public void setNbrInitVues(int nbrInitVues) {
		this.nbrInitVues = nbrInitVues;
	}
	public int getNbrCibleVues() {
		return nbrCibleVues;
	}
	public void setNbrCibleVues(int nbrCibleVues) {
		this.nbrCibleVues = nbrCibleVues;
	}
	public float getCout() {
		return cout;
	}
	public void setCout(float cout) {
		this.cout = cout;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getCible() {
		return cible;
	}
	public void setCible(String cible) {
		this.cible = cible;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Ad [idAd=" + idAd + ", canal=" + canal + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin
				+ ", nbrInitVues=" + nbrInitVues + ", nbrCibleVues=" + nbrCibleVues + ", cout=" + cout + ", type="
				+ type + ", file=" + file + ", link=" + link + ", cible=" + cible + ", user=" + user + "]";
	}
	
	
	
	
	
	
}
