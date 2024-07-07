package com.spring.learning;

import com.spring.learning.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class LearningApplication {

	public static void main(String[] args) {
		var context = SpringApplication.run(LearningApplication.class, args);
		var userService = context.getBean(UserService.class);
		userService.generateUsers();
		var users = userService.getAllUserProfiles();
		System.out.println(users);
	}

}
