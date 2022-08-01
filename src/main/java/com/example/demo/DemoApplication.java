package com.example.demo;

import com.example.demo.domain.Role;
import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner run (UserService userService){
		return args -> {
			userService.saveRole(new Role(null, "ROLE_USER"));
			userService.saveRole(new Role(null, "ROLE_MANAGER"));
			userService.saveRole(new Role(null, "ROLE_ADMIN"));
			userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

			userService.saveUser(new User(null, "김어드민","admin_kim","1234",new ArrayList<>()));
			userService.saveUser(new User(null, "김매니저","manager_kim","1234",new ArrayList<>()));
			userService.saveUser(new User(null, "김유저","user_kim","1234",new ArrayList<>()));


			userService.addRoleToUser("admin_kim","ROLE_ADMIN");
			userService.addRoleToUser("admin_kim","ROLE_USER");

			userService.addRoleToUser("manager_kim","ROLE_MANAGER");
			userService.addRoleToUser("manager_kim","ROLE_USER");

			userService.addRoleToUser("user_kim","ROLE_USER");


		};
	}

}
