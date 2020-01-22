package mim.auth.service.entity.models;


import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


import lombok.Getter;
import lombok.Setter;
import mim.auth.service.models.SignUpModel;


@Getter
@Setter
@Entity
@Table(name = "MIM_USER_DETAILS", uniqueConstraints={@UniqueConstraint(columnNames = "userId"),@UniqueConstraint(columnNames = "emailId")})

public class UserDetails {
	
	public UserDetails() {
		
	}
	

	@Id
	@GeneratedValue
	private UUID uuid;
	
	private String firstName;
	private String lastName;
	

	private String userId;
	private String emailId;

	private String dob;
	private String password;
	
	private boolean isLocked;
	private boolean isEnabled;
	
	
	public UserDetails(SignUpModel signUpModel, String encriptedPassword) {
		
		this.emailId = signUpModel.getEmailId();
		this.firstName = signUpModel.getFirstName();
		this.lastName = signUpModel.getLastName();
		this.userId = signUpModel.getUserId();
		this.password = encriptedPassword;

	}


	
}
