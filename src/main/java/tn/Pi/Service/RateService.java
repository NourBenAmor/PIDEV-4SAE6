package tn.Pi.Service;

import java.util.List;

import tn.Pi.entities.Post;
import tn.Pi.entities.Rate;




public interface RateService {

	Rate saveRate(Rate r);
	Rate updateRate(Rate r);
	void deleteRate(Rate r);
	void deleteRateById(Long id);
	Rate getRate(Long id);
	List<Rate> getAllRates();
	float Statistiques(Post p);
}
