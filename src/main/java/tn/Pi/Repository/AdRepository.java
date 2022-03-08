package tn.Pi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.Pi.entities.Ad;



public interface AdRepository extends JpaRepository<Ad, Long> {

}
