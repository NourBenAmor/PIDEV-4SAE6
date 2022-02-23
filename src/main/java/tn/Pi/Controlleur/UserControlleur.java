package tn.Pi.Controlleur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.Pi.Service.ServiceUser;
import tn.Pi.entities.User;

@RestController
@RequestMapping("/AppUser")
public class UserControlleur {
	@Autowired
	ServiceUser serviceUser;

	
	@PostMapping("/addUser")
	public void addUser(@RequestBody User u)
	{
		serviceUser.addUser(u);
	}
}
