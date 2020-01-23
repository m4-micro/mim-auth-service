package mim.auth.service.models;

import lombok.Getter;
import lombok.Setter;
import mim.auth.service.entity.models.UserDetails;

@Getter
@Setter
public class JwtContent {
	
	public JwtContent(UserDetails userDetails) {
		this.userId = userDetails.getUserId();
	}

	private String userId;

}
