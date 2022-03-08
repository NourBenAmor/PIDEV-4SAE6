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
public class Rendez_vous {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idRendez_vous;
	@ManyToOne
	private Expert expert;
	@ManyToOne
	private User user;
	private Date date;
	@Enumerated(EnumType.STRING)
	private Type type;
	
	
	
	public Rendez_vous() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Rendez_vous [idRendez_vous=" + idRendez_vous + ", expert=" + expert + ", date=" + date + ", type="
				+ type + "]";
	}
	public Rendez_vous(Expert expert, User user, Date date, Type type) {
		super();
		this.expert = expert;
		this.user = user;
		this.date = date;
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}

	
	
	
}
