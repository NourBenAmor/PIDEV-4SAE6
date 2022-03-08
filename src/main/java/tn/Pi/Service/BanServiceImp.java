package tn.Pi.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.Pi.entities.Ban;
import tn.Pi.entities.User;



@Service
public class BanServiceImp implements BanService {
	@Autowired
	tn.Pi.Repository.BanRepository BanRepository;
	
	@Override
	public Ban saveBan(Ban b) {
		return BanRepository.save(b);
	}

	@Override
	public Ban updateBan(Ban b) {
		return BanRepository.save(b);
	}

	@Override
	public void deleteBan(Ban b) {
		BanRepository.delete(b);
		
	}

	@Override
	public void deleteBanById(Long id) {
		BanRepository.deleteById(id);
		
	}

	@Override
	public Ban getBan(Long id) {
		return BanRepository.findById(id).get();
	}

	@Override
	public List<Ban> getAllBans() {
		
		return BanRepository.findAll();
	}
	
	@Override
	public Ban getBanByUser(User u){
		return BanRepository.findByUser(u);
	}
	
}
