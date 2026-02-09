package com.example.digcompsys;

import com.example.digcompsys.model.User;
import com.example.digcompsys.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class DigcompsysApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigcompsysApplication.class, args);
	}

	@Bean
	CommandLineRunner createAdmin(UserRepository repo, PasswordEncoder encoder) {
		return args -> {

			if (repo.findByEmail("Head@Application.com").isEmpty()) {

				User admin = new User();
				admin.setUserName("Admin");
				admin.setEmail("Head@Application.com");
				admin.setPassword(encoder.encode("a1d2m3i4n"));
				admin.setRoleName(User.Role.ROLE_ADMIN);

				repo.save(admin);

				System.out.println("Default admin user created!");
			}
		};
	}
}
