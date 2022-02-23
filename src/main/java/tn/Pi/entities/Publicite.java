package tn.Pi.entities;

import java.io.Serializable;
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

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class Publicite implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;
	private String titre;
	@Temporal(TemporalType.DATE)
	private Date dateDebut;
	@Temporal(TemporalType.DATE)
	private Date dateFin;
	private String link;
	private String poster;
	@Enumerated(EnumType.STRING)
	private Methode methode;
	private float budget;
	private boolean promotefb;
	private boolean prometgoogle;
	private int estimatedviews;
	private int currentviews;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	@ManyToOne
	User appuser;
	
	

}
