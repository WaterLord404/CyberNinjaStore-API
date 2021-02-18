package com.techshop.security.filter.jwt;

import static com.techshop.security.common.SecurityConstants.EXPIRATION_TIME;
import static com.techshop.security.common.SecurityConstants.SECRET;

import java.sql.Date;

import javax.crypto.SecretKey;

import com.techshop.security.model.entity.User;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;


public class JWTTokenProvider {

	private static SecretKey key;

	public static String generateToken(User user) {
		return Jwts.builder()
				.setHeaderParam(Header.TYPE, Header.JWT_TYPE)
				.setSubject(user.getId().toString())
				.setId(user.getId().toString()).setIssuedAt(new Date(System.currentTimeMillis()))
				.claim("updateTime", user.getUpdateTime())// Example
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(getKey(), SignatureAlgorithm.HS512)
				.compact();
	}

	public static boolean validateToken(String token) {
		boolean valid = false;
		try {
			Jwts.parser().setSigningKey(getKey()).parseClaimsJws(token);
			valid = true;
		} catch (Exception e) {
			// ToDo report on error type
		}
		return valid;
	}

	public static SecretKey getKey() {
		if (key == null) {
			key = Keys.hmacShaKeyFor(SECRET.getBytes());
		}
		return key;
	}

}
