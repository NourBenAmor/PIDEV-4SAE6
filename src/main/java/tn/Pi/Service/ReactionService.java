package tn.Pi.Service;

import java.util.List;

import tn.Pi.entities.Reaction;



public interface ReactionService {

	Reaction saveReaction(Reaction r);
	Reaction updateReaction(Reaction r);
	void deleteReaction(Reaction r);
	void deleteReactionById(Long id);
	Reaction getReaction(Long id);
	List<Reaction> getAllReactions();
}
