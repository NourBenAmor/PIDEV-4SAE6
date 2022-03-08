package tn.Pi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.Pi.entities.Ban;
import tn.Pi.entities.User;



public interface BanRepository extends JpaRepository<Ban, Long> {

	Ban findByUser(User u);

}
