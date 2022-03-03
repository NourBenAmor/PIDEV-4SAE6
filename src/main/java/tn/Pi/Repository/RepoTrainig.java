package tn.Pi.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.Pi.entities.BestWorstTrainig;
import tn.Pi.entities.Training;
import tn.Pi.entities.User;
import tn.Pi.entities.UserTraining;
@Repository
public interface RepoTrainig extends CrudRepository<Training, Long> {
	@Query(value="select training.titel as name ,count(training_userlike.training_id_formation) occ from training_userlike"
			+ " left join training on training.id_formation =training_userlike.training_id_formation"
			+ " group by (training_id_formation)"
			+ " Order by occ "
			+ "DESC LIMIT 1 "
              ,nativeQuery=true)
	public BestWorstTrainig BestTraining();

	@Query(value="select training.titel as name ,count(training_userdeslike.training_id_formation) occ from training_userdeslike"
			+ " left join training on training.id_formation =training_userdeslike.training_id_formation"
			+ " group by (training_id_formation)"
			+ " Order by occ"
			+ " DESC LIMIT 1",nativeQuery=true)
	public BestWorstTrainig WorstTraining();
	
	@Query(value = "SELECT training.description , training.titel , formateur.first_name"
			+ " FROM training LEFT JOIN formateur ON training.formateur_id_formateur=formateur.id_formateur"
			+ " WHERE first_name =:name"  , nativeQuery = true)
	public List<UserTraining> trainingtitle(@Param("name")String name);
}
