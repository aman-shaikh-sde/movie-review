package com.test.movie_review;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.crypto.SecretKey;
import java.util.Base64;

@SpringBootApplication
public class MovieReviewApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieReviewApplication.class, args);

		SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
		String base64Key = Base64.getEncoder().encodeToString(key.getEncoded());
		System.out.println("Generated Secure Key: " + base64Key);
	}




}
