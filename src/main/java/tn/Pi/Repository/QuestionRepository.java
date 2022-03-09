package tn.Pi.Repository;

import org.springframework.data.repository.CrudRepository;

import tn.Pi.entities.Question;

public interface QuestionRepository extends CrudRepository<Question, Long> {

}
