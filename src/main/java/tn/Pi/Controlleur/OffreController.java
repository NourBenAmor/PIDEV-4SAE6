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

import tn.Pi.Service.OffreService;
import tn.Pi.entities.Offre;



@RequestMapping("/offre")
	@RestController
	public class OffreController {
		@Autowired
		OffreService offreService;
		
		@RequestMapping("/showCreate")
		public String showCreate()
		{
			return "createOffre";
		}
		
		@PostMapping("/saveOffre")
		@ResponseBody
		public void saveOffre(@RequestBody Offre offre)
		{
			//conversion de la date 
	       // SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
	        //Date dateCreation = dateformat.parse(String.valueOf(date));
	       // produit.setDateCreation(dateCreation);
	      
			offreService.saveOffre(offre);
		
		}
		
		@GetMapping("/ListeOffres")
		@ResponseBody
		public  String listeOffres(ModelMap modelMap)
		{
			List<Offre> offs = offreService.getAllOffre();
			modelMap.addAttribute("offres", offs);		
			return "listeOffres";	
		}
		
		@RequestMapping("/supprimerOffre")
		public String supprimerOffre(@RequestParam("id") Long id,ModelMap modelMap)
		{
			Offre o= new Offre();
			o.setIdOffre(id);
			offreService.deleteOffre(o);
			List<Offre> offs = offreService.getAllOffre();
			modelMap.addAttribute("offres", offs);	
			return "listeOffres";	
		}
		
		@RequestMapping("/modifierOffre")
		public String editerOffre(@RequestParam("id") Long id,ModelMap modelMap)
		{
			Offre o= offreService.getOffre(id);
			modelMap.addAttribute("offre", o);	
			return "editerOffre";	
		}

		
		@PutMapping("/updateOffre")
		@ResponseBody
		public void updateOffre(@RequestBody Offre offre) 
		{
			
			//conversion de la date 
	        //SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
	       // Date dateCreation = dateformat.parse(String.valueOf(date));
	       // produit.setDateCreation(dateCreation);
	        
			offreService.updateOffre(offre);
			 
		}

}
