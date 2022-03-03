package tn.Pi.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.engine.internal.Cascade;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
	private List<User> appuser;
	@OneToOne(mappedBy="training")
	private Certifact certifcat	;
	
	@ManyToMany
	@JsonIgnore
	Set<User> Userlike;
	@ManyToMany
	@JsonIgnore
	Set<User> Userdeslike;
	
}
