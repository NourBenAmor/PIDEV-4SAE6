package tn.Pi.entities;

import java.io.Serializable;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
public class Cangotte implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Temporal(TemporalType.DATE)
	private Date dateDebut;

	@Temporal(TemporalType.DATE)
	private Date dateFin;
	private float valCangotte;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cangotte")
	private List<User> appuser;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cangotte")
	private List<Event> event;
	@OneToOne
	private Donate donate;

}
