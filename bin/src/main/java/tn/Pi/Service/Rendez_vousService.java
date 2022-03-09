package tn.Pi.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import tn.Pi.entities.Rendez_vous;


@Service
public interface Rendez_vousService {
	public String saveRendez_vous(Rendez_vous r,Long id);
	void updateRendez_vous(Rendez_vous r);
	void deleteRendez_vous(Rendez_vous r);
	void deleteRendez_vousById(Long id);
	Rendez_vous getRendez_vous(Long id);
	List<Rendez_vous> getAllRendez_vous();
	public String prendreRendezVous(Long userId,Long expertId);

}

