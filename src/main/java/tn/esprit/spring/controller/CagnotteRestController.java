package tn.esprit.spring.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import tn.esprit.spring.entities.Cagnotte;
import tn.esprit.spring.services.ICagnotteService;

@RestController
@Api(tags = "Gestion des cagnottes")
@RequestMapping("/cagnotte")
public class CagnotteRestController {
	@Autowired
	ICagnotteService cagnotteService;
	
	// http://localhost:8089/SpringMVC/cagnotte/retrieve-all-cagnottes
	@ApiOperation(value = "Récupérer la liste de tous les cagnottes")
	@GetMapping("/retrieve-all-cagnottes")
	public List<Cagnotte> getCagnottes() {
		List<Cagnotte> listCagnottes = cagnotteService.getAllCagnotte();
		return listCagnottes;
	}
	
	// http://localhost:8089/SpringMVC/cagnotte/get-cagnotte/8
	@ApiOperation(value = "Récupérer une cagnotte avec son Id")
	@GetMapping(value = "getAllCagnotte")

    public List<Cagnotte> getAllCagnotte(){
	return cagnotteService.getAllCagnotte();
	
	}
	
	@ApiOperation(value = "Ajouter une cagnotte à la base de données")
	@PostMapping("/add-cagnotte")
	public Cagnotte addCagnotte(@RequestBody Cagnotte c) {
		Cagnotte cagnotte = cagnotteService.addCagnotte(c);
		return cagnotte;	
		}
	
	// http://localhost:8089/SpringMVC/cagnotte/delete-cagnotte/{id-cagnotte}
	@ApiOperation(value = "Supprimer le cagnotte d'Id en question de la base de données")
	@DeleteMapping("/delete-cagnotte/{id-cagnotte}")
	@ResponseBody
	public void deleteCagnotte(@PathVariable("idCagnotte")int idCagnotte){
		   cagnotteService.deleteCagnotte(idCagnotte);
	}
	// http://localhost:8089/SpringMVC/cagnotte/modify-cagnotte
	@ApiOperation(value = "Modifier une cagnotte")
	@PutMapping("/modify-cagnotte")
	@ResponseBody
	public Cagnotte modifyCagnotte(@RequestBody Cagnotte cagnotte) {
		return cagnotteService.updateCagnotte(cagnotte);
		}

}
