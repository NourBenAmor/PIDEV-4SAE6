package tn.Pi.Service;

import java.util.List;

import tn.Pi.entities.Follow;

public interface IFollowService {
	Follow addFollow(Long idUser1,Long idUser2);
	void acceptFollow(Long idFollow);
	List<String> myFrinds(Long idUser1,int etat);
	List<String> myInvitations(Long idUser2, int etat);

}
