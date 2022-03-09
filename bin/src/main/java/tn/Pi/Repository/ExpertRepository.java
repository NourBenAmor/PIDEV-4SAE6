package tn.Pi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.Pi.entities.Expert;



public interface ExpertRepository extends JpaRepository<Expert, Long> {

}
