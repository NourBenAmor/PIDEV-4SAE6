package tn.Pi.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.Pi.entities.Certifact;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Training implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idFormation;
	@NotNull
	private String titel;
//	@Enumerated(EnumType.STRING)
	//private Domaine type;
	@ManyToOne(cascade=CascadeType.ALL)
	@JsonIgnore
	private Formateur formateur;
	@NotNull
	private String description;
	@Temporal(TemporalType.DATE)
	private Date dateDebut;
	@Temporal(TemporalType.DATE)
	private Date dateFin;
	private int nbrApp;
	@ManyToMany(cascade = CascadeType.ALL)
	@JsonIgnore
	private List<apprenant> apprenant;
	@OneToOne(mappedBy="training")
	@JsonIgnore
	private Certifact certifcat	;
	//@ManyToMany(mappedBy="trainings",cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
	//private Set<User> users;
	@OneToMany(mappedBy="training",cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)	
	@JsonManagedReference
	private Set<Quiz> quizs;
	
	//@ManyToMany
	//@JsonIgnore
	//Set<User> Userlike;
	//@ManyToMany
	//@JsonIgnore
	//Set<User> Userdeslike;
	
}
