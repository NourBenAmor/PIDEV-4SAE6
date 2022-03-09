package tn.Pi.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.Pi.entities.Post;
import tn.Pi.entities.Rate;


public interface RateRepository extends JpaRepository<Rate, Long> {

	List<Rate> findByPost(Post p);

}
