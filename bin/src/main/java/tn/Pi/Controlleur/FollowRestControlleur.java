package tn.Pi.Controlleur;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tn.Pi.Service.IFollowService;
import tn.Pi.entities.Follow;

@RequestMapping("/follow")
@RestController
public class FollowRestControlleur {
	@Autowired
	IFollowService ifs;
	
	@PostMapping("addFollow/{idUser1}/{idUser2}")
	public Follow addFollow(@PathVariable("idUser1")Long idUser1,@PathVariable("idUser2") Long idUser2){
		return ifs.addFollow(idUser1, idUser2);
		
	}
	
	@PutMapping("acceptFollow/{idFollow}")
	public void acceptFollow(@PathVariable("idFollow")Long idFollow){
	      ifs.acceptFollow(idFollow);
		
	}
	
	@GetMapping("myFrinds/{idUser1}")
	public List<String> myFrinds(@PathVariable("idUser1") Long idUser1){
		return ifs.myFrinds(idUser1, 1);
	}
	
	@GetMapping("myInvitations/{idUser2}")
	public List<String> myInvitations(@PathVariable("idUser2") Long idUser2){
		return ifs.myInvitations(idUser2, 0);
	}

}
