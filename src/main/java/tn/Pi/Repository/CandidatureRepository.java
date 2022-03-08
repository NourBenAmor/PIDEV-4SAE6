package tn.Pi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.Pi.entities.Candidature;



public interface CandidatureRepository extends JpaRepository<Candidature, Long> {

}
