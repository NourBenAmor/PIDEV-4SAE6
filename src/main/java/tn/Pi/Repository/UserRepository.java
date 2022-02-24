package tn.Pi.Repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tn.Pi.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
	
	@Query("Select u from User u where u.isDeleted= 0")
	public List<User>getUndeletedUser();

	
}
