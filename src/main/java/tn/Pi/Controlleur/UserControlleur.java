package tn.Pi.Controlleur;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@GetMapping("/confirm/{token}")
	public String confirm(@PathVariable("token") String token) {
		return serviceUser.confirmToken(token);
	}

	
	@PostMapping("/addUser")
	public String addUser(@RequestBody User u)
	{
		return serviceUser.addUser(u);
	}
	
	
	
	@GetMapping("/getUser")
	public List<User>getAllUser()
	
	{
		return serviceUser.getAllUser();
	}
	
	@GetMapping("/getUser2")
	public List<User>getUndeletedUser()
	{
		return serviceUser.getUndeletedUser();
	}
	
	@PutMapping("/deletUser/{idUser}")
	public void deletedUser(@PathVariable("idUser")Long id)
	{
		
		serviceUser.deletedUser(id);
		
	}
	
	@PutMapping("/updateuser")
	public void updateUser(@RequestBody User u)
	{
		serviceUser.updateUser(u);
	}
	
	
}
