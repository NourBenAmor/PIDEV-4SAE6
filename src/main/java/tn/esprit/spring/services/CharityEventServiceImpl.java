package tn.esprit.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.CharityEvent;
import tn.esprit.spring.entities.Reservation;
import tn.esprit.spring.repository.ICharityEventRepository;

import tn.esprit.spring.repository.IReservationRepository;
import tn.esprit.spring.repository.UserRepository;

@Service
public class CharityEventServiceImpl  implements ICharityEventService {

	@Autowired
	ICharityEventRepository eR;
	@Autowired
	UserRepository Ur;
	@Autowired
	IReservationRepository RR;
	
	
	@Override
	public CharityEvent addEvent(CharityEvent e) {
		return eR.save(e);
	}
	
	@Override
	public int getNombrePlacesEvent(int idevent){
		return eR.NombrePlacesEvent(idevent);		
	}
	@Override
	public int getNombreParticpEvent(int idevent){
		return eR.NombreParticpEvent(idevent);
	}
	public List<Integer> getEventList(){
		return eR.CharityEventList();
	}

	@Override
	public void deleteEvent(int id) {
		eR.deleteById(id);
		
	}

	
	

	@Override
	public List<CharityEvent>retreiveAllEvent(){
		
		List<CharityEvent> e = (List <CharityEvent>)eR.findAll();
		return e;
	}

	@Override
	public CharityEvent updateEvent(CharityEvent e) {
		return eR.save(e);
	}

	@Override
	public void addReservation(Reservation r) {
		
		RR.save(r);
	}

	
	
	
}