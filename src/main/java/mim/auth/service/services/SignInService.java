package mim.auth.service.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import mim.auth.service.entity.models.UserDetails;
import mim.auth.service.entity.repository.UserDetailsRepository;
import mim.auth.service.models.JwtContent;
import mim.auth.service.models.JwtToken;
import mim.auth.service.models.SignInModel;

@Service
public class SignInService {

	@Autowired
	private UserDetailsRepository userDetailsRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtTokenService jwtTokenService;

	public Object signIn(SignInModel signInModel) {

		List<UserDetails> listUserDetails = userDetailsRepository.findByUserIdOrEmailId(signInModel.getUserId(),
				signInModel.getEmailId());
		if (!listUserDetails.isEmpty()) {

			UserDetails userDetails = listUserDetails.get(0);

			if (signInModel.validateCredentials(passwordEncoder, userDetails.getPassword())) {
				return jwtTokenService.generateJwtToken(userDetails);
			} else {
				return "invalid creden";
			}

		} 
			return "no such";

	}

	public boolean validateTocken(JwtToken jwtToken) {
		
		String userId = jwtTokenService.getUserIdFromToken(jwtToken.getToken());
		
		List<UserDetails> listUserDetails = userDetailsRepository.findByUserId(userId);
		
		if (!listUserDetails.isEmpty()) {
			UserDetails userDetails = listUserDetails.get(0);
			return jwtTokenService.validateToken(jwtToken.getToken(), userDetails);
		}
		return false;
	}

}
