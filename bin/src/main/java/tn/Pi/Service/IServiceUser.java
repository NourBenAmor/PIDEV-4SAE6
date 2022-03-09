package tn.Pi.Service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import tn.Pi.entities.User;

public interface IServiceUser {
	String addUser(User u);



	List<User> getUndeletedUser();

	void deletedUser(Long id);

	void updateUser(User u);
	
	void send(String to, String email);
	
	 public String confirmToken(String token);
		public String forgotPassword(String userName,HttpServletRequest request);
		public String fogetPasswordSetting(String token,String newPass);
		public List<User> orderUser(Integer pageNo, Integer pageSize, String sortBy,String Type);
}
