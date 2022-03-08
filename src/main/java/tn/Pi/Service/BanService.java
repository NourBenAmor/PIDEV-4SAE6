package tn.Pi.Service;

import java.util.List;

import tn.Pi.entities.Ban;
import tn.Pi.entities.User;



public interface BanService {
	Ban saveBan(Ban b);
	Ban updateBan(Ban b);
	void deleteBan(Ban b);
	void deleteBanById(Long id);
	Ban getBan(Long id);
	Ban getBanByUser(User u);
	List<Ban> getAllBans();
}
