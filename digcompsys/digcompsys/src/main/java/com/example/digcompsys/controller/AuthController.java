package com.example.digcompsys.controller;

import com.example.digcompsys.dto.request.LoginRequest;
import com.example.digcompsys.dto.request.RegisterRequest;
import com.example.digcompsys.dto.response.AuthResponse;
import com.example.digcompsys.model.User;
import com.example.digcompsys.repository.UserRepository;
import com.example.digcompsys.security.CustomUserDetailsService;
import com.example.digcompsys.security.JwtUtil;
import com.example.digcompsys.service.UserService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User authenticate(String email, String password) {

        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return user;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        User user = userService.register(request);
        return ResponseEntity.ok("User registered");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        UserDetails userDetails =
                userDetailsService.loadUserByUsername(request.getEmail());

        String token = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(
                new AuthResponse("Login successful", token)
        );
    }

    @GetMapping("/test")
    public String test(Authentication authentication) {
        authentication.getAuthorities()
                .forEach(a -> log.info("ROLE: {}", a.getAuthority()));
        return "ok";
    }

}
