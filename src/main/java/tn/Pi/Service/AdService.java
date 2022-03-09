package tn.Pi.Service;

import java.util.List;

import tn.Pi.entities.Ad;
import tn.Pi.entities.User;


public interface AdService {
	Ad save(Ad b);
	Ad update(Ad b);
	void delete(Ad b);
	void deleteById(Long id);
	Ad get(Long id);
	List<Ad> getByUser(User u);
	List<Ad> getAll();
	int Estimated(Ad ad);
}
