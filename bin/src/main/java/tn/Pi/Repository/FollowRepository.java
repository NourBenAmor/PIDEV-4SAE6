package tn.Pi.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.Pi.entities.Follow;
import tn.Pi.entities.User;
@Repository
public interface FollowRepository extends JpaRepository<Follow,Long>{
	@Query("select f from Follow f where f.user1= :user1 and f.user2= :user2")
	Optional<Follow> findByIdUser1User2(@Param("user1") User user1,@Param("user2") User user2);
	
	@Query("select f from Follow f where  (f.user1=:user1 or f.user2= :user1)  and f.etat=:etat")
	List<Follow> myFrinds(@Param("user1") User user1,@Param("etat") int etat);
	
	@Query("select f from Follow f where f.user2=:user2 and f.etat=:etat")
	List<Follow> myInvitations(@Param("user2") User user2,@Param("etat") int etat);

}
