package tn.Pi.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.Pi.entities.Reaction;



@Service
public class ReactionServiceImp implements ReactionService {

	@Autowired
	tn.Pi.Repository.ReactionRepository ReactionRepository;
	
	@Override
	public Reaction saveReaction(Reaction r) {
		return ReactionRepository.save(r);
	}

	@Override
	public Reaction updateReaction(Reaction r) {
		return ReactionRepository.save(r);
	}

	@Override
	public void deleteReaction(Reaction r) {
		ReactionRepository.delete(r);
		
	}

	@Override
	public void deleteReactionById(Long id) {
		ReactionRepository.deleteById(id);
		
	}

	@Override
	public Reaction getReaction(Long id) {
		return ReactionRepository.findById(id).get();
	}

	@Override
	public List<Reaction> getAllReactions() {
		
		return ReactionRepository.findAll();
	}
}
