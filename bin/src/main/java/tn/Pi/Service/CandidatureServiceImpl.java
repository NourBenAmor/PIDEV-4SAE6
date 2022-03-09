package tn.Pi.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import tn.Pi.Repository.CandidatureRepository;
import tn.Pi.Repository.UserRepository;
import tn.Pi.entities.Candidature;



@Service
public class CandidatureServiceImpl implements CandidatureService{

	@Autowired
	private JavaMailSender mailsender;
	@Autowired
	CandidatureRepository candidatureRepository;
	@Autowired 
	UserRepository userRepository;
	
	
	@Override
	public void saveCandidature(Candidature c,String email ) {
		
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setFrom("ayoub.selmani.1@esprit.tn");
		message.setTo("iheb.saadaoui@esprit.tn");
		message.setSubject("Votre Candidature a étè prise en compte et après le traitement on vous informe que votre entretien sera planifié le 10/03/2022 à 14:15");
		message.setText("bonsoir monsieur"+userRepository.findByEmail(email).getFirstName()+"je vous informe que la condidature"+c.getEtat());
		System.out.println("success");
		
		mailsender.send(message);
		candidatureRepository.save(c);
	}
	

	@Override
	public void updateCandidature(Candidature c) {
		candidatureRepository.save(c);
		
	}

	@Override
	public void deleteCandidature(Candidature c) {
		candidatureRepository.save(c);
		
	}

	@Override
	public void deleteCandidatureById(Long id) {
		candidatureRepository.deleteById(id);		
	}

	@Override
	public Candidature getCandidature(Long id) {
		return candidatureRepository.findById(id).get();
	}

	@Override
	public List<Candidature> getAllCandidature() {
	return	candidatureRepository.findAll();
	}

}
