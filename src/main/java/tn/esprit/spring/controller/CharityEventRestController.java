package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import tn.esprit.spring.entities.Cagnotte;
import tn.esprit.spring.entities.CharityEvent;
import tn.esprit.spring.entities.Reservation;
import tn.esprit.spring.services.ICagnotteService;
import tn.esprit.spring.services.ICharityEventService;




@RestController
@Api(tags = "Gestion des évnements des charités")
@RequestMapping("/event")
public class CharityEventRestController {
	@Autowired
	ICharityEventService eS;
	
	// http://localhost:8089/SpringMVC/event/retrieve-all-events
	@ApiOperation(value = "Récupérer la liste de tous les evenements")
	@GetMapping("/retrieveallEvent")
	@ResponseBody
	public List<CharityEvent> getEvent() {
	List<CharityEvent> list = eS.retreiveAllEvent();
	return list;
	}
	
	
	// http://localhost:8089/SpringMVC/event/addEvent
	@ApiOperation(value = "Ajouter un évènement")
	@PostMapping("/addEvent")
	@ResponseBody
	public CharityEvent addEvent(@RequestBody CharityEvent e) {
		eS.addEvent(e);
	return e;
	}
	
	// http://localhost:8089/SpringMVC/charity/delete-event/{id-event}
	@ApiOperation(value = "Supprimer l'evenement d'Id en question de la base de données")
	@DeleteMapping("/remove-event/{idEvent}")
	@ResponseBody
	public void removeEvent(@PathVariable("idEvent") int id) {
	eS.deleteEvent(id); 
	}
	//http://localhost:9091/SpringMVC/servlet/addReservation
	@PostMapping("/addReservation")
	@ResponseBody
	public Reservation addReservation(@RequestBody Reservation r) {
		eS.addReservation(r);
	return r;
	}
	

}
