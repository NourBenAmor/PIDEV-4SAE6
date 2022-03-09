package tn.Pi.Controlleur;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.sun.xml.messaging.saaj.packaging.mime.internet.ParseException;

import tn.Pi.Service.PostService;
import tn.Pi.Service.RateService;
import tn.Pi.entities.Post;
import tn.Pi.entities.Rate;


@RestController
public class RateController {

	@Autowired
	RateService rateService;
	@Autowired
	PostService postservice;
	

	@RequestMapping("/showCreateRate")
	public String showCreate()
	{
		return "createRate";
	}
	
	@PostMapping("/saveRate")
	public String saveRate(@RequestBody Rate rate,@RequestParam Long idPost)
	{
		Post p = postservice.findById(idPost);
		p.setIdPost(idPost);
		rate.setPost(p);
		rateService.saveRate(rate);
		return "rate added!";
	}
	
	@GetMapping("/ListeRates")
	public List<Rate> listeRates()
	{
		List<Rate> Rates = rateService.getAllRates();
		return Rates;	
	}
	
	@DeleteMapping("/supprimerRate")
	public String supprimerRate(@RequestParam Long id)
	{
		
		rateService.deleteRateById(id);
		return "rate deleted";	
	}
	
	@GetMapping("/GetRateById")
	public Rate editerRate(@RequestParam Long id)
	{
		Rate r= 	rateService.getRate(id);	
		return r;	
	}

	@PutMapping("/updateRate")
	public String updateRate(@RequestBody Rate rate,@RequestParam Long idPost) 
	{
		Post p =postservice.findById(idPost);
		rate.setPost(p);
		rateService.updateRate(rate);
		return "rate updated";
	}
	
	@PostMapping("/statistiques")
	public String statistique(@RequestParam("idPost") Long idPost)
	{
		String msg ;
		String tag;
		Post p = postservice.getPost(idPost);
		float stars  =rateService.Statistiques(p);
		if (stars>3)
		{
			msg = "Congratulations! ";
			tag="reached";
		}
		else
		{
			msg="Sadly..";
			tag="only";
		}
		return msg+"\nyour post has "+tag+" "+Math.round(stars)+" stars ";
	}
}
