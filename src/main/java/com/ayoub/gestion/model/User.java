package com.ayoub.gestion.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUser;
	@OneToMany (mappedBy = "Nomducandidat")
	private List<Candidature> candidatures;
	@OneToMany (mappedBy = "user")
	private List<Rendez_vous>Rendez_vous;

}
