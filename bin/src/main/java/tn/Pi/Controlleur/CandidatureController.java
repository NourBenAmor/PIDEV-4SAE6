package tn.Pi.Controlleur;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.Pi.Service.CandidatureService;
import tn.Pi.entities.Candidature;




@RestController
public class CandidatureController {

		@Autowired 
		CandidatureService candidatureService;
		@GetMapping("/Candidatures")
		@ResponseBody
		public List<Candidature> getAllCandidatures(){
			List<Candidature> list= candidatureService.getAllCandidature();
		return list;
		}

		
		@PostMapping("/SaveCandidature")
		@ResponseBody
		public void saveCandidature(@RequestBody Candidature c,@RequestParam String email) {
			 candidatureService.saveCandidature(c,email);
			 }


		@DeleteMapping("deleteCandidature/{id}")
		@ResponseBody
		public void deleteCandidature(@RequestParam("id") Long idCandidature) {
			candidatureService.deleteCandidatureById(idCandidature);
				}
			

@PutMapping("/updateCandidature")
@ResponseBody
public void updateCandidature(@RequestBody Candidature candidature) 
{
	
	//conversion de la date 
    //SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
   // Date dateCreation = dateformat.parse(String.valueOf(date));
   // produit.setDateCreation(dateCreation);
    
	  candidatureService.updateCandidature(candidature);}}
	 
