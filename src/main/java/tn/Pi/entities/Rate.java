package tn.Pi.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
public class Rate {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idRate;
	private float stars;
	@JsonBackReference
	@ManyToOne(fetch=FetchType.LAZY)
	private Post post;
	
	public Rate() {
		super();
	}
	
	


	public Rate(float stars, Post post) {
		super();
		this.stars = stars;
		this.post = post;

	}




	public Long getIdRate() {
		return idRate;
	}
	public void setIdRate(Long idRate) {
		this.idRate = idRate;
	}
	public float getStars() {
		return stars;
	}
	public void setStars(float stars) {
		this.stars = stars;
	}
	
	public Post getPost() {
		return post;
	}



	public void setPost(Post post) {
		this.post = post;
	}

	@Override
	public String toString() {
		return "Rate [idRate=" + idRate + ", stars=" + stars + ", post=" + post + "]";
	}

	
}