package tn.Pi.Controlleur;

import java.util.List;

import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.Pi.JwtAndAuthConf.JwtResponse;
import tn.Pi.JwtAndAuthConf.JwtUtils;
import tn.Pi.JwtAndAuthConf.LoginRequest;
import tn.Pi.JwtAndAuthConf.UserDetailsImpl;
import tn.Pi.Repository.RoleRepository;
import tn.Pi.Service.ServiceUser;
import tn.Pi.entities.User;

@RestController
@RequestMapping("/AppUser")
public class UserControlleur {
	@Autowired
	ServiceUser serviceUser;
	
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	JwtUtils jwtUtils;
	
	
	
	
	
	

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser( @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 roles));
	}
	
	
	@PostMapping("/forgot/{username}")
	public String forgotPassworProcess(@PathVariable("username") String username,HttpServletRequest request) {
		return serviceUser.forgotPassword(username, request);
	}
	@PostMapping("/reset/{token}/{newpassword}")
	public String resetPassword(@PathVariable("token") String token,@PathVariable("newpassword") String newpassword ) {
		return serviceUser.fogetPasswordSetting(token, newpassword);
	}
	
	@GetMapping("/confirm/{token}")
	public String confirm(@PathVariable("token") String token) {
		return serviceUser.confirmToken(token);
	}

	
	@PostMapping("/addUser")
	public String addUser(@RequestBody User u)
	{
		return serviceUser.addUser(u);
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
	
	
	@GetMapping("/SortUniOff")
    @ResponseBody
    public ResponseEntity<List<User>> SortUniOff(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "idUser") String sortBy,
            @RequestParam(defaultValue = "ASC") String Type)
    {
        List<User> list = serviceUser.orderUser(pageNo, pageSize, sortBy,Type);


        return new ResponseEntity<List<User>>(list, new HttpHeaders(), HttpStatus.OK);
    }
	
	
	
}
