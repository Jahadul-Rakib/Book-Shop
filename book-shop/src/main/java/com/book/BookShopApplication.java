package com.book;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import com.book.entity.User;
import com.book.security.config.SecurityConfig;
import com.book.security.config.SecurityInitializer;
import com.book.security.entity.Role;
import com.book.security.entity.UserRole;
import com.book.security.impl.SecurityUtility;
import com.book.security.repo.UserService;

@ComponentScan(basePackages = {
        "com.book.security.config",
        "com.book.controller",
        "com.book.entity",
        "com.book.security.entity", 
        "com.book.security.impl", 
        "com.book.security.repo"
        })
@SpringBootApplication
@Import(SecurityInitializer.class)
public class BookShopApplication implements CommandLineRunner{
	
	@Autowired
	private UserService userService;
	public static void main(String[] args) {
		SpringApplication.run(BookShopApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User user1 = new User();
		user1.setFirstName("John");
		user1.setLastName("Adams");
		user1.setUsername("n");
		user1.setPassword(SecurityUtility.passwordEncoder().encode("n"));
		user1.setEmail("rakibdiu2015@gmail.com");
		Set<UserRole> userRoles = new HashSet<>();
		Role role1= new Role();
		role1.setRoleId(1);
		role1.setName("ROLE_USER");
		userRoles.add(new UserRole(user1, role1));
		userService.createUser(user1, userRoles);		
	}

}
