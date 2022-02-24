package tn.Pi.Service;

import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.Pi.Repository.UserRepository;
import tn.Pi.entities.User;

@Service
public class ServiceUser implements IServiceUser {
	@Autowired
	UserRepository userRepository;

	@Override
	public void addUser(User u) {

		Date date = new Date(System.currentTimeMillis());
		u.setCreatedAt(date);
		Period period = Period.between(u.getBirthDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
				date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		u.setAge(period.getYears());
		userRepository.save(u);
	}

	@Override
	public List<User> getAllUser() {

		return userRepository.findAll();
	}

	@Override
	public List<User> getUndeletedUser() {
		return userRepository.getUndeletedUser();
	}

	@Override
	public void deletedUser(Long id) {

		Date date = new Date(System.currentTimeMillis());
		User deletedUser = userRepository.findById(id).orElse(null);
		deletedUser.setDeletedAt(date);
		deletedUser.setDeleted(true);
		userRepository.save(deletedUser);

	}
	
	
	public void updateUser(User u)
	{
		
		Date date = new Date(System.currentTimeMillis());
		u.setCreatedAt(date);
		Period period = Period.between(u.getBirthDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
				date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		u.setAge(period.getYears());
		u.setModifiedAt(date);
		userRepository.save(u);
		
	}
}
