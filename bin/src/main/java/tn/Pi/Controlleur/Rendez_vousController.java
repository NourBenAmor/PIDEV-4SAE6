package tn.Pi.Controlleur;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.Pi.entities.Rendez_vous;
import tn.Pi.Service.Rendez_vousService;

@RequestMapping("/rendez_vous")
@RestController
public class Rendez_vousController {
	@Autowired
	Rendez_vousService rendez_vousService;
	
	@RequestMapping("/showCreate")
	public String showCreate()
	{
		return "createRendez_vous";
	}
	
	@PostMapping("/saveRendez_vous/{idExpert}")
	@ResponseBody
	public String saveRendez_vous(@RequestBody Rendez_vous rendez_vous,@PathVariable("idExpert") Long id)
	{
	
	 return rendez_vousService.saveRendez_vous(rendez_vous,id);
	}
	
	@GetMapping("/ListeRndez_vous")
	@ResponseBody
	public String listeRendez_vous(ModelMap modelMap)
	{
		List<Rendez_vous> rends = rendez_vousService.getAllRendez_vous();
		modelMap.addAttribute("rendez_vous", rends);		
		return "listeRendez_vous";	
	}
	
	@RequestMapping("/supprimerRendez_vous")
	public String supprimerRendez_vous(@RequestParam("id") Long id,ModelMap modelMap)
	{
		Rendez_vous re= new Rendez_vous();
		re.setIdRendez_vous(id);
		rendez_vousService.deleteRendez_vous(re);
		List<Rendez_vous> rends = rendez_vousService.getAllRendez_vous();
		modelMap.addAttribute("rendez_vous", rends);	
		return "listeRendez_vous";	
	}
	
	@RequestMapping("/modifierRendez_vous")
	public String editerRendez_vous(@RequestParam("id") Long id,ModelMap modelMap)
	{
		Rendez_vous re= 	rendez_vousService.getRendez_vous(id);
		modelMap.addAttribute("rendez_vous", re);	
		return "editerRendez_vous";	
	}

	
	@PutMapping("/updateRendez_vous")
	@ResponseBody
	public void updateRendez_vous(@RequestBody Rendez_vous rendez_vous) 
	{
		
		//conversion de la date 
        //SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
       // Date dateCreation = dateformat.parse(String.valueOf(date));
       // produit.setDateCreation(dateCreation);
        
		rendez_vousService.updateRendez_vous(rendez_vous);
		 
	}
	@PutMapping("/prendreRendezVous/{userId}/{expertId}")
	public String prendreRendezVous(@PathVariable("userId") Long userId,@PathVariable("expertId")Long expertId) {
	return rendez_vousService.prendreRendezVous(userId, expertId);
	}
}
