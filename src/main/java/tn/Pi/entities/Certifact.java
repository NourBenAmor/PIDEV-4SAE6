package tn.Pi.entities;

import java.io.Serializable;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
public class Certifact implements Serializable {
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String description;
	@OneToOne
	private Training training;
	public Certifact() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Certifact(long id, String description, Training training) {
		super();
		this.id = id;
		this.description = description;
		this.training = training;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Training getTraining() {
		return training;
	}
	public void setTraining(Training training) {
		this.training = training;
	}
	

}
