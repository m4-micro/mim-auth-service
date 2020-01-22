package mim.auth.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mim.auth.service.models.SignUpModel;
import mim.auth.service.services.SignUpService;

@RestController
@RequestMapping("mim-auth-service/signup")
public class SignUpController {
	
	@Autowired
	SignUpService signUpService;

	@PostMapping(value="/")
	public Object postSignIn(@RequestBody SignUpModel signUpModel) {
		return signUpService.signUp(signUpModel);
		
	}
}
