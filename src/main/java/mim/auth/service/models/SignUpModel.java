package mim.auth.service.models;

import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.Getter;
import lombok.Setter;


@Setter
public class SignUpModel {
	
	@Getter
	private String firstName;
	@Getter
	private String lastName;
	@Getter
	private String emailId;
	@Getter
	private String userId;

	private String password;
	private String confirmPassword;

	public boolean validatePassword() {
		
		return password.equals(confirmPassword);
		
	}
	public String getEncriptedPassword(PasswordEncoder passwordEncoder) {
		return passwordEncoder.encode(password);
	}
}
