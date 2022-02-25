package com.ayoub.gestion.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ayoub.gestion.model.Expert;

public interface ExpertRepository extends JpaRepository<Expert, Long> {

}
