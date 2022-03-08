package tn.Pi.Repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tn.Pi.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
	

	
	@Query("Select u from User u where u.isDeleted= 0")
	public List<User>getUndeletedUser();
	
	@Query("Select u from User u where u.userName= :urname")
	public User findOneByUserName(@Param("urname") String urname);

	@Transactional
	@Modifying
	@Query("UPDATE User u " + "SET u.enabled = TRUE WHERE u.userName = ?1")
	int enableAppUser(String userName);
	
	

	
}
