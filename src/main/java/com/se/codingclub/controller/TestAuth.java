package com.se.codingclub.controller;

import org.springframework.security.crypto.bcrypt.BCrypt;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

public class TestAuth {

	 public static String generateTokenLogin(String username) {
		    String token = null;
		    try {
		      // Create HMAC signer
		      JWSSigner signer = new MACSigner(generateShareSecret());
		      JWTClaimsSet.Builder builder = new JWTClaimsSet.Builder();
		      builder.claim("carshop", username);
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
		 public static String encode(String rawPassword) {
				String salt =BCrypt.gensalt(10);
				return BCrypt.hashpw(rawPassword, salt);
		}

		public static boolean matches(String rawPassword, String encodedPassword) {
			if (encodedPassword == null || encodedPassword.length() == 0) {
				return false;
			}

			return BCrypt.checkpw(rawPassword, encodedPassword);
		}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}
