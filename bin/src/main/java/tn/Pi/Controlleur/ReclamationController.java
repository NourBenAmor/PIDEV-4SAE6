package tn.Pi.Controlleur;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.Pi.entities.Reclamation;
import tn.Pi.Service.ReclamationService;

@RequestMapping("/reclamation")
@RestController
public class ReclamationController {
	@Autowired
	ReclamationService reclamationService;
	
	@RequestMapping("/showCreate")
	public String showCreate()
	{
		return "createReclamation";
	}
	
	@PostMapping("/saveReclamation")
	@ResponseBody
	public void saveReclamation(@RequestBody Reclamation reclamation)
	{
		//conversion de la date 
       // SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        //Date dateCreation = dateformat.parse(String.valueOf(date));
       // produit.setDateCreation(dateCreation);
      
		reclamationService.saveReclamtion(reclamation);
	
	}
	
	@GetMapping("/ListeReclamations")
	@ResponseBody
	public String listeReclamations(ModelMap modelMap)
	{
		List<Reclamation> recs = reclamationService.getAllReclamation();
		modelMap.addAttribute("reclamations", recs);		
		return "listeReclamations";	
	}
	
	@RequestMapping("/supprimerReclamation")
	public String supprimerReclamation(@RequestParam("id") Long id,ModelMap modelMap)
	{
		Reclamation r= new Reclamation();
		r.setIdReclamation(id);
		reclamationService.deleteReclamation(r);
		List<Reclamation> recs = reclamationService.getAllReclamation();
		modelMap.addAttribute("reclamations", recs);	
		return "listeReclamations";	
	}
	
	@RequestMapping("/modifierReclamation")
	public String editerReclamation(@RequestParam("id") Long id,ModelMap modelMap)
	{
		Reclamation r= 	reclamationService.getReclamation(id);
		modelMap.addAttribute("reclamation", r);	
		return "editerReclamation";	
	}

	
	@PutMapping("/updateReclamation")
	@ResponseBody
	public void updateReclamation(@RequestBody Reclamation reclamation) 
	{
		
		//conversion de la date 
        //SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
       // Date dateCreation = dateformat.parse(String.valueOf(date));
       // produit.setDateCreation(dateCreation);
        
		reclamationService.updateReclamation(reclamation);
		 
	}

}
