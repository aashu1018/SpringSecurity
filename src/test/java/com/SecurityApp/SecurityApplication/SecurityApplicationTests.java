package com.SecurityApp.SecurityApplication;

import com.SecurityApp.SecurityApplication.entities.User;
import com.SecurityApp.SecurityApplication.services.JWTService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SecurityApplicationTests {

	@Autowired
	private JWTService jwtService;

	@Test
	void contextLoads() {

		User user = new User(2L, "john@wick.com", "1234", "John");

		String token = jwtService.generateToken(user);
		System.out.println(token);

		Long id = jwtService.getUserIdFromToken(token);
		System.out.println(id);
	}

}
