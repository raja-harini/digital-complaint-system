package com.example.digcompsys.config;

import com.example.digcompsys.model.User;
import com.example.digcompsys.model.RoleName;
import com.example.digcompsys.repository.UserRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminInitializer {

    @Bean
    CommandLineRunner createAdminUser(UserRepository userRepository,
                                      PasswordEncoder passwordEncoder) {
        return args -> {

            if (userRepository.findByRoleName(RoleName.ADMIN).isEmpty()) {

                User user = new User();
                user.setUserName("Admin");
                user.setPassword(passwordEncoder.encode("a1d2m3i4n"));
                user.setEmail("Head@system.com");
                user.setPhone("9786534201");
                user.setRoleName(RoleName.ADMIN);

                userRepository.save(user);

                System.out.println("Default ADMIN user created.");
            }
        };
    }
}