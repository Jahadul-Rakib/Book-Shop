package com.book;

import com.book.config.security.permission.Permission;
import com.book.entity.UserPermission;
import com.book.service.RoleService;
import com.book.service.UserPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.book.entity.User;
import com.book.entity.Role;
import com.book.impl.SecurityUtility;
import com.book.repository.UserService;
@ComponentScan(basePackages = {
        "com.book.config.security",
        "com.book.config.beans",
        "com.book.controller",
		"com.book.entity",
		"com.book.service",
		"com.book.impl",
        "com.book.repository"
        })
@SpringBootApplication
public class BookShopApplication implements CommandLineRunner{
	
	@Autowired
	private UserService userService;
	@Autowired
    private RoleService roleService;
	@Autowired
    UserPermissionService userPermissionService;

    public static void main(String[] args) {
		SpringApplication.run(BookShopApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
        initializer();
	}

	private void initializer () throws Exception {
        //Creating roles
        Role role1 = new Role();
        role1.setName("ROLE_USER");
        roleService.saveRole(role1);

        Role role2 = new Role();
        role2.setName("ROLE_ADMIN");
        roleService.saveRole(role2);

        // Creating Users
        System.out.println("Creating User 1...");
        User user1 = new User();
		user1.setFirstName("Jahadul");
		user1.setLastName("Rakib");
		user1.setUsername("jahadul_rakib");
		user1.setPassword(SecurityUtility.passwordEncoder().encode("secret"));
		user1.setEmail("rakibdiu2015@gmail.com");
		role2.getUsers().add(user1);
        user1.setRole(role2);
		userService.createUser(user1);

        for (Permission value : Permission.values()) {
            UserPermission userPermission = new UserPermission();
            userPermission.setUsername(user1.getUsername());
            userPermission.setPermission(value);
            userPermissionService.save(userPermission);
        }

        System.out.println("Creating User 2...");
        User user2 = new User();
        user2.setFirstName("Syed Mainul");
        user2.setLastName("Hasan");
        user2.setUsername("mainul35");
        user2.setPassword(SecurityUtility.passwordEncoder().encode("secret"));
        user2.setEmail("mainuls18@gmail.com");
        role2.getUsers().add(user2);
        user2.setRole(role2);
        userService.createUser(user2);

        for (Permission value : Permission.values()) {
            if (!value.equals(Permission.USER_ONLY)) {
                UserPermission userPermission = new UserPermission();
                userPermission.setUsername(user2.getUsername());
                userPermission.setPermission(value);
                userPermissionService.save(userPermission);
            }
        }
        
        System.out.println("Creating User 3...");
        User user3 = new User();
        user3.setFirstName("Jahadul");
        user3.setLastName("Hasan");
        user3.setUsername("jahadul");
        user3.setPassword(SecurityUtility.passwordEncoder().encode("secret"));
        user3.setEmail("tanveer@gmail.com");
        role1.getUsers().add(user3);
        user3.setRole(role1);
        userService.createUser(user3);

        UserPermission userPermission = new UserPermission();
        userPermission.setUsername(user3.getUsername());
        userPermission.setPermission(Permission.VIEW_BOOKS);
        userPermissionService.save(userPermission);
    }
}
