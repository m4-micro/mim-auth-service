package mim.auth.service.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mim.auth.service.models.JwtToken;
import mim.auth.service.models.SignInModel;
import mim.auth.service.services.SignInService;

@RestController
@RequestMapping("mim-auth-service/signin")
public class SignInController {
	
	
	@Autowired
	private SignInService signInService;
	
	
	@PostMapping(value="/")
	public Object postSignIn(@RequestBody  SignInModel signInModel) {
		
		
		return signInService.signIn(signInModel);
	}
	
	@PostMapping(value="/validate")
	public Object postSignIn(@RequestBody  JwtToken jwtToken) {
		
		
		return signInService.validateTocken(jwtToken);
	}

}
