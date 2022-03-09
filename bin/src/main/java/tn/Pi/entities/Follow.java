package tn.Pi.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Getter
@Setter 
@NoArgsConstructor 
@AllArgsConstructor
public class Follow implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idFollow;
	@Temporal(TemporalType.DATE)
	private Date dateFollow;
	private int etat;
	@ManyToOne
	@JoinColumn(name="idUser1")
	private User user1;
	@ManyToOne
	@JoinColumn(name="idUser2")
	private User user2;

}
