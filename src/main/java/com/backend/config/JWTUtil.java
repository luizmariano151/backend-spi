package com.backend.config;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JWTUtil {

	private final String TOKEN_SENHA = "75b68a10-e27a-4ab1-9f15-664a027e1958";;

	public String gerarToken(String username) {
		return criarToken(new HashMap<String, Object>(), username);
	}

	public Boolean validarToken(String token, UserDetails userDetails) {
		String username = extrairUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	public String extrairUsername(String token) {
		return extrairClaim(token, Claims::getSubject);
	}

	public Date extrairTempoValidade(String token) {
		return extrairClaim(token, Claims::getExpiration);
	}

	public <T> T extrairClaim(String token, Function<Claims, T> claimsResolver) {
		Claims claims = extrairClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extrairClaims(String token) {
		return Jwts.parser().setSigningKey(TOKEN_SENHA).parseClaimsJws(token).getBody();
	}

	private Boolean isTokenExpired(String token) {
		return extrairTempoValidade(token).before(new Date());
	}

	private String criarToken(Map<String, Object> claims, String subject) {
		String token = Jwts.builder().setClaims(claims).setSubject(subject)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 500 * 60 * 60 * 10))
				.signWith(SignatureAlgorithm.HS512, TOKEN_SENHA).compact();

		return token;
	}

}
