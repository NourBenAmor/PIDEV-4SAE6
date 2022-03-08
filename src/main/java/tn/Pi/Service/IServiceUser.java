package tn.Pi.Service;

import java.util.List;

import tn.Pi.entities.User;

public interface IServiceUser {
	String addUser(User u);

	List<User> getAllUser();

	List<User> getUndeletedUser();

	void deletedUser(Long id);

	void updateUser(User u);
	
	void send(String to, String email);
	 String confirmToken(String token);

}
