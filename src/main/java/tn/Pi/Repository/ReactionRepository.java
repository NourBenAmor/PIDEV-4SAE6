package tn.Pi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.Pi.entities.Reaction;



public interface ReactionRepository extends JpaRepository<Reaction, Long> {

}
