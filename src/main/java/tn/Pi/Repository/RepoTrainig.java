package tn.Pi.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.Pi.entities.Training;
import tn.Pi.entities.User;
@Repository
public interface RepoTrainig extends CrudRepository<Training, Long> {

}
