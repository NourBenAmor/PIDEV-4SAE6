package tn.Pi.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
@Entity
public class Interest implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	String interest;
	
	@ManyToMany(mappedBy="interests")
	private List<User> users;
	

	


	public Interest(long id, String interest, List<User> users) {
		super();
		this.id = id;
		this.interest = interest;
		this.users = users;
	}
	public Interest() {
		super();
	
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	
	
	
}
