package tn.Pi.Service;

import java.util.List;

import tn.Pi.entities.User;

public interface IServiceUser {
	void addUser(User u);

	List<User> getAllUser();

	List<User> getUndeletedUser();

	void deletedUser(Long id);

	void updateUser(User u);
}
