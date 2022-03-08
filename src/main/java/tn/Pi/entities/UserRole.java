package tn.Pi.entities;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole  implements GrantedAuthority{
	
	ADMIN,USER;

	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return"ROLE_" + name();
	}

}
