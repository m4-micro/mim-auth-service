package mim.auth.service.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtToken {
	
	public JwtToken() {
		
	}
	
	public JwtToken(String token) {
		this.token = token;
	}

	String token;

}
