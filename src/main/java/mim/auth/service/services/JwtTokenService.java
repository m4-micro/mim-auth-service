package mim.auth.service.services;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.RsaProvider;
import mim.auth.service.entity.models.UserDetails;
import mim.auth.service.models.JwtTokenResponce;

@Service
public class JwtTokenService implements EnvironmentAware {

	private Environment environment;

	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
	public static final long JWT_REFRESH_TOKEN_VALIDITY = 15 * 60 * 60;

	public static final String MIM_AUTH_SERVICE_PUBLIC_KEY = "MIM_AUTH_SERVICE_PUBLIC_KEY";
	public static final String MIM_AUTH_SERVICE_PRIVATE_KEY = "MIM_AUTH_SERVICE_PRIVATE_KEY";

	PrivateKey privateKey;
	PublicKey publicKey;

	@Override
	public void setEnvironment(Environment environment) {
		this.environment = environment;

		// String public_key = environment.getProperty(MIM_AUTH_SERVICE_PUBLIC_KEY);
		// String private_key = environment.getProperty(MIM_AUTH_SERVICE_PRIVATE_KEY);

		KeyPair keyPair = RsaProvider.generateKeyPair(1024);

		privateKey = keyPair.getPrivate();
		publicKey = keyPair.getPublic();

	}

	public JwtTokenResponce generateJwtToken(UserDetails userDetails) {

		return new JwtTokenResponce(generateToken(userDetails));

	}

	private String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();

		claims.put("emailId", userDetails.getEmailId());
		claims.put("userId", userDetails.getUserId());
		claims.put("dob", userDetails.getDob());
		claims.put("firstName", userDetails.getFirstName());
		claims.put("lastName", userDetails.getLastName());
		claims.put("uuid", userDetails.getUuid());
		claims.put("enabled", userDetails.isEnabled());
		claims.put("locked", userDetails.isLocked());
		claims.put("issuer", "mim-auth-service");

		return doGenerateToken(claims, userDetails.getUserId());
	}

	public boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUserIdFromToken(token);
		return (username.equals(userDetails.getUserId()) && !isTokenExpired(token));
	}

	private boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	public String getUserIdFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token).getBody();
	}

	private String doGenerateToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.RS512, privateKey).compact();
	}

}
