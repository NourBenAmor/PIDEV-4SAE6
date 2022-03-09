package tn.Pi.Repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.Pi.entities.Historique;
import tn.Pi.entities.User;




public interface HistoriqueRepository extends JpaRepository<Historique, Long> {

	List<Historique> getByUser(User u);
	List<Historique> findByUser(User u);

}
