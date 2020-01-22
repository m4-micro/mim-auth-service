package mim.auth.service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import mim.auth.service.entity.models.UserDetails;
import mim.auth.service.entity.repository.UserDetailsRepository;
import mim.auth.service.models.SignUpModel;

@Service
public class SignUpService {
	
	
	@Autowired
	private UserDetailsRepository userDetailsRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public SignUpStatus signUp(SignUpModel signUpModel) {
		
		
		if(signUpModel.validatePassword()) {
			
			String encriptedPassword =  signUpModel.getEncriptedPassword(passwordEncoder);
			UserDetails userDetails = new UserDetails(signUpModel, encriptedPassword);
			userDetailsRepository.save(userDetails);
			
		}
		
		
		return SignUpStatus.USER_CREATED;
		
	}

}
