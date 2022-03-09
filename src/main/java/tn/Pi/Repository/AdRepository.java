package tn.Pi.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.Pi.entities.Ad;
import tn.Pi.entities.User;



public interface AdRepository extends JpaRepository<Ad, Long> {

	List<Ad> findByUser(User u);

}
