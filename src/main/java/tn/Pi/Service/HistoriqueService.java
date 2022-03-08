package tn.Pi.Service;

import java.util.List;

import tn.Pi.entities.Historique;
import tn.Pi.entities.User;



public interface HistoriqueService {
	Historique saveHistorique(Historique b);
	Historique updateHistorique(Historique b);
	void deleteHistorique(Historique b);
	void deleteHistoriqueById(Long id);
	Historique getHistorique(Long id);
	List<Historique> getHistoriqueByUser(User u);
}
