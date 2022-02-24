package tn.Pi.entities;

import java.io.Serializable;

import javax.persistence.Entity;
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
public class Rating implements Serializable {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;
	private float rate;
	@ManyToOne
	Publication publication;

}
