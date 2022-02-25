package com.ayoub.gestion.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ayoub.gestion.model.Candidature;

public interface CandidatureRepository extends JpaRepository<Candidature, Long> {

}
