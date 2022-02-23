package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.CharityEvent;
import tn.esprit.spring.entities.Reservation;

public interface ICharityEventService {
	
void deleteEvent(int id);
	
	CharityEvent addEvent(CharityEvent e);
	
	int getNombrePlacesEvent(int idevent);

	int getNombreParticpEvent(int idevent);
	
	List<CharityEvent>retreiveAllEvent();
	
	public List<Integer> getEventList();
	
	CharityEvent updateEvent(CharityEvent e);
	
	void addReservation(Reservation r);

}
