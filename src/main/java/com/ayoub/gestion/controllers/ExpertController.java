package com.ayoub.gestion.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ayoub.gestion.model.Expert;
import com.ayoub.gestion.service.ExpertService;

@RequestMapping("/expert")
	@Controller
	public class ExpertController {
		@Autowired
		ExpertService expertService;
		
		@RequestMapping("/showCreate")
		public String showCreate()
		{
			return "createExpert";
		}
		
		@PostMapping("/saveExpert")
		@ResponseBody
		public void saveExpert(@RequestBody Expert expert)
		{
			//conversion de la date 
	       // SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
	        //Date dateCreation = dateformat.parse(String.valueOf(date));
	       // produit.setDateCreation(dateCreation);
	      
			    expertService.saveExpert(expert);
		
		}
		
		@GetMapping("/ListeExperts")
		@ResponseBody
		public String listeExperts(ModelMap modelMap)
		{
			List<Expert> exps = expertService.getAllExpert();
			modelMap.addAttribute("experts", exps);		
			return "listeExperts";	
		}
		
		@RequestMapping("/supprimerExpert")
		public String supprimerExpert(@RequestParam("id") Long id,ModelMap modelMap)
		{
			Expert e= new Expert();
			e.setIdExpert(id);
			expertService.deleteExpert(e);
			List<Expert> exps = expertService.getAllExpert();
			modelMap.addAttribute("experts", exps);	
			return "listeExperts";	
		}
		
		@RequestMapping("/modifierExpert")
		public String editerExpert(@RequestParam("id") Long id,ModelMap modelMap)
		{
			Expert e= 	expertService.getExpert(id);
			modelMap.addAttribute("expert", e);	
			return "editerExpert";	
		}

		
		@PutMapping("/updateExpert")
		@ResponseBody
		public void updateExpert(@RequestBody Expert expert) 
		{
			
			//conversion de la date 
	        //SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
	       // Date dateCreation = dateformat.parse(String.valueOf(date));
	       // produit.setDateCreation(dateCreation);
	        
			  expertService.updateExpert(expert);
			 
		}

}
