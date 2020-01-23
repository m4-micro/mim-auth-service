package mim.auth.service.aspects.exceptions;

public class InvalidCredentialException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2225994552713342459L;
	
	public InvalidCredentialException() {
		super();
	}
	
	public InvalidCredentialException(String message) {
		super(message);
	}

}
