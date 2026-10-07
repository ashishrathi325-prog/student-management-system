package com.ashish.Config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ashish.Entity.StudentEntity;
import com.ashish.Repository.StudentRepository;

@Configuration
public class DataInitializer {
	 @Bean
	    CommandLineRunner createDefaultUser(
	            StudentRepository repo,
	            PasswordEncoder passwordEncoder) {

	        return args -> {

	            if (repo.findByUsername("admin").isEmpty()) {

	                StudentEntity user = new StudentEntity();

	                user.setUsername("admin");
	                user.setPassword(
	                    passwordEncoder.encode("admin123")
	                );
	                user.setRole("ROLE_USER");
	                user.setName("Admin");
	                user.setCourse("BCA");
	                user.setAge(22);

	                repo.save(user);

	                System.out.println("Default user created!");
	                System.out.println("Username: admin");
	                System.out.println("Password: admin123");
	            }
	        };
	    }

}
