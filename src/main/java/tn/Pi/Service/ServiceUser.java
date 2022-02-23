package tn.Pi.Service;

import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.Pi.Repository.UserRepository;
import tn.Pi.entities.User;

@Service
public class ServiceUser implements IServiceUser {
	@Autowired
	UserRepository userRepository;
	
	@Override
	public void addUser(User u)
	{
		
		
		Date date = new Date(System.currentTimeMillis());
		u.setCreatedAt(date);
		Period period = Period.between( u.getBirthDate().toInstant()
		     .atZone(ZoneId.systemDefault())
		     .toLocalDate(),date.toInstant()
		     .atZone(ZoneId.systemDefault())
		     .toLocalDate());
		u.setAge(period.getYears());
		userRepository.save(u);
	}

}
