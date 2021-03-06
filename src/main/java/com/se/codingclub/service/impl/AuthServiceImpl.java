package com.se.codingclub.service.impl;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Repository;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.se.codingclub.entity.User;
import com.se.codingclub.service.AuthService;


@Repository
public class AuthServiceImpl implements AuthService{

	@Override
	public String encode(String rawPassword) {
		// TODO Auto-generated method stub
		String salt =BCrypt.gensalt(10);
		return BCrypt.hashpw(rawPassword, salt);
	}

	@Override
	public boolean matches(String rawPassword, String encodedPassword) {
		// TODO Auto-generated method stub
		if (encodedPassword == null || encodedPassword.length() == 0) {
			return false;
		}
	
		return BCrypt.checkpw(rawPassword, encodedPassword);
	}

	@Override
	public String generateTokenLogin(String username, String role, int user_id) {
		// TODO Auto-generated method stub
		 String token = null;
		    try {
		      // Create HMAC signer
		      JWSSigner signer = new MACSigner(generateShareSecret());
		      JWTClaimsSet.Builder builder = new JWTClaimsSet.Builder();
		      builder.claim("username", username);
		      builder.claim("role", role);
		      builder.claim("user_id", user_id);
		      JWTClaimsSet claimsSet = builder.build();
		      SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
		      // Apply the HMAC protection
		      signedJWT.sign(signer);
		      // Serialize to compact form, produces something like
		      // eyJhbGciOiJIUzI1NiJ9.SGVsbG8sIHdvcmxkIQ.onO9Ihudz3WkiauDO2Uhyuz0Y18UASXlSc1eS0NkWyA
		      token = signedJWT.serialize();
		    } catch (Exception e) {
		      e.printStackTrace();
		    }
		    return token;
	}
	private static byte[] generateShareSecret() {
	    // Generate 256-bit (32-byte) shared secret
	    byte[] sharedSecret = new byte[32];
	    sharedSecret = "11111111111111111111111111111111".getBytes();
	    return sharedSecret;
	}

	@Override
	public User getUserByToken(String token) {
		String username = null;
		int user_id = -1;
		String role = null;
	    try {
	      JWTClaimsSet claims = getClaimsFromToken(token);
	      username = claims.getStringClaim("username");
	      user_id = claims.getIntegerClaim("user_id");
	      role = claims.getStringClaim("role");
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    User user = new User(user_id, username, role);
	    System.out.println(user);
	    return user;
	}
	
	private JWTClaimsSet getClaimsFromToken(String token) {
	    JWTClaimsSet claims = null;
	    try {
	      SignedJWT signedJWT = SignedJWT.parse(token);
	      JWSVerifier verifier = new MACVerifier(generateShareSecret());
	      if (signedJWT.verify(verifier)) {
	        claims = signedJWT.getJWTClaimsSet();
	      }
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    return claims;
	  }
}
