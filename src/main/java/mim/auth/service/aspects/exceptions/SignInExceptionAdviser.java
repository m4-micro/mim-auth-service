package mim.auth.service.aspects.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SignInExceptionAdviser {
	
	
	@ExceptionHandler(InvalidCredentialException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Object hander(InvalidCredentialException invalidCredentialException) {
		
		return "InvalidCredentialException";
		
	}
	
	
	@ExceptionHandler(NoSuchUserException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Object hander(NoSuchUserException noSuchUserException) {
		
		return "NoSuchUserException";
		
	}
	
	

}
