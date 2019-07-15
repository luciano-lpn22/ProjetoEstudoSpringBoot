package br.com.compra.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {

	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private long expiration;
	
	
	

	public String generateToken(String userName) {
		
			return Jwts.builder()
				.setSubject(userName)
				.setExpiration(new Date(System.currentTimeMillis()+expiration))
				.signWith(SignatureAlgorithm.HS512, secret.getBytes())
				.compact();
	}


	public boolean tokenValido(String token) {
		Claims claims=getClaims(token);
		if (claims!=null) {
			//recupera usuario solicitante
			String userName=claims.getSubject();
			//recupera data do token do solicitante
			Date ExpirationDate=claims.getExpiration();
			Date now= new Date(System.currentTimeMillis());
			if (userName!=null && ExpirationDate !=null && now.before(ExpirationDate)) {
				return true;
			}
		}
		
		return false;
	}


	private Claims getClaims(String token) {
		try {
			//Recupera o Clams a partir de um token, para utilizar na validação 
			return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			return null;
		}
		 
		
		
	}


	public String getUserName(String token) {
		Claims claims=getClaims(token);
		if (claims!=null) {
			//recupera usuario solicitante
			String userName=claims.getSubject();
			if (userName!=null) {
				return userName;
			}
		}
		return null;
	}
	
	
	
}
