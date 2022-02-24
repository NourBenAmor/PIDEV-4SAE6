package tn.Pi.entities;



import java.io.Serializable;


import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor

public class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long idUser;

	@NonNull
	private String firstName;
	@NonNull
	private String lastName;
	@NonNull
	@Column(unique=true)
	private String userName;
	@NonNull
	private String email;
	@NonNull
	private String password;
	@NonNull
	@Enumerated(EnumType.STRING)
	UserRole userRole;

	private boolean locked;

	private boolean enabled;
	
	private boolean isDeleted;

	@NonNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedAt;

	@Temporal(TemporalType.TIMESTAMP)
	private Date deletedAt;



	@Temporal(TemporalType.DATE)
	@Transient
	private Date birthDate;
	@NonNull
	private Integer age;
	
	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "appuser")
	private List<Reclamation> reclamation;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "appuser")
	private List<Rendezvous> rendezvous;
	@ManyToOne
	Cangotte cangotte;
	@ManyToMany(mappedBy = "appuser", cascade = CascadeType.ALL)
	private List<Offer> offer;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "appuser")
	private List<Publicite> publicite;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "appuser")
	private List<Chat> chat;
	@ManyToMany(mappedBy = "appuser", cascade = CascadeType.ALL)
	private List<Training> training;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "appuser")
	private List<Publication> publication;
	


}
