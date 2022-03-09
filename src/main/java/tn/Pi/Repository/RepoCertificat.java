package tn.Pi.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.Pi.entities.Certifact;

@Repository
public interface RepoCertificat extends CrudRepository<Certifact, Long> {

}
