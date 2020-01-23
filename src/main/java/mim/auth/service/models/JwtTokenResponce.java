package mim.auth.service.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtTokenResponce {
	
	public JwtTokenResponce() {
		
		this.authenticated = false;
		
	}
	
	public JwtTokenResponce(String token) {
		this.token = token;
		this.authenticated = true;
	}

	String token;
	
	Boolean authenticated;
	
	

}
