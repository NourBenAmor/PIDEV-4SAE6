package tn.Pi.Controlleur;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.Pi.Service.IServiceYasmine;
import tn.Pi.entities.Certifact;
import tn.Pi.entities.Training;
import tn.Pi.entities.User;


@RestController
@RequestMapping("/Formations")
public class EspaceFormationController {
	@Autowired
	IServiceYasmine se;
	
	@PostMapping("/add")
	public void ajouterFormateur(@RequestBody Training formation) {
		se.ajouterFormation(formation);
	}
	 
	@GetMapping("/getFormation")
	public List<Training>getform()
	{
		return se.ListedesFormation();
	}
	@DeleteMapping("/deletFormation/{idFormation}")
    @ResponseBody
	public void deleteformation(@PathVariable("idFormation")Long idFormation)
	{		
		se.SupprimerFormation(idFormation);	
	}
	@PutMapping("/modify-formation-by-id")
    @ResponseBody
    public Training modifyformation(@RequestBody Training formation,@RequestParam("idFormationn") Long idFormationn) {
        return se.ModifierFormation(formation, idFormationn);
    }

	@PostMapping("/addCertificat")
	public void ajouterCertificat(@RequestBody Certifact certificat) {
		se.ajouterCertificat(certificat);
	}
	
	@GetMapping("/getCertificat")
	public List<Certifact>getcertificats()
	{
		return se.ListedesCertificat();
	}
	@DeleteMapping("/deleteCertificat/{id}")
    @ResponseBody
	public void deleteCertificat(@PathVariable Long id)
	{		
		se.SupprimerFormation(id);	
	}
	@PutMapping("/modify-certificat-by-id")
    @ResponseBody
    public Certifact modifyCertificat(@RequestBody Certifact cer,@RequestParam("idCertificat") Long idCertificat) {
        return se.ModifierCertificat(cer, idCertificat);
    }
	
} 