package mim.auth.service.models;

import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.Getter;
import lombok.Setter;

@Setter
public class SignInModel {
	

	@Getter
	private String emailId;
	@Getter
	private String userId;

	private String password;

	public String getEncriptedPassword(PasswordEncoder passwordEncoder) {
		return passwordEncoder.encode(password);
	}

	public boolean validateCredentials(PasswordEncoder passwordEncoder, String encriptedPassword) {
		
		return passwordEncoder.matches(password, encriptedPassword);
		
	}

}
