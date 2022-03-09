package tn.Pi.Service;

import tn.Pi.entities.Interest;

public interface InterestService {
	public void addUserInterest(Long interestId,String email);
	public void removeUserInterest(Long interestId,String uname);
	public void addOffreInterest(Long interestId,Long offreId);
	public void removeOffreInterest(Long offreId);
	public Interest addInterest( Interest  interest);}
