package tn.Pi.entities;




 


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity

public class Commentaire implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;
	private String description;
	private long like;
	private String emoji;
	@Enumerated(EnumType.STRING)
	private MotInterdi motinterdi;
	@ManyToOne
	User appuser;

}
