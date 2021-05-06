package edu.ap.group10.leapwebapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import edu.ap.group10.leapwebapp.JWT.CustomAuthenticationProvider;

@SpringBootApplication
public class LeapWebappApplication{

	public static void main(String[] args) {
		SpringApplication.run(LeapWebappApplication.class, args);
	}
	
	@Bean public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder(); 
	}

	@Bean public CustomAuthenticationProvider authProvider() {
		return new CustomAuthenticationProvider();
	}
}
