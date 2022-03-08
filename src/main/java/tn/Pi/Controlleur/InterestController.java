package tn.Pi.Controlleur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.Pi.Service.InterestService;
import tn.Pi.entities.Interest;




@RestController
@RequestMapping("/Interest")
public class InterestController {
	
	@Autowired
	InterestService interestService;
	
	
	@PostMapping("/add-interest")
	public Interest addInterest(@RequestBody Interest  interest) {
		return interestService.addInterest(interest);
	}
	
	
	@PostMapping("/addUserInterest")
	public void addUserInterst(@RequestParam("interestId") long interestId,@RequestParam String email ) {
		
		interestService.addUserInterest(interestId,email);
	}
	
	
	@PostMapping("/removeUserInterest")
	public void removeUserInterst(@RequestParam("interestId") long interestId, @RequestParam("uname")String uname ) {
		
		interestService.removeUserInterest(interestId, uname);
	}
	
	
	
	@PostMapping("/addEventInterest")
	public void addTrainingInterest(@RequestParam("interestId") long interestId, @RequestParam("trainingId") long trainingId) {
		interestService.addOffreInterest(interestId, trainingId);
	}
	@PostMapping("/removeEventInterest")
	public void removeTrainingInterest(@RequestParam("eventId") long trainingId) {
		interestService.removeOffreInterest(trainingId);
	}
	
	
	
	
	
}