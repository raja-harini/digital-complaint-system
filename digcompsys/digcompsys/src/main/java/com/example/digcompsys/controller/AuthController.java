package com.example.digcompsys.controller;

import com.example.digcompsys.model.User;
import com.example.digcompsys.repository.UserRepository;
import com.example.digcompsys.security.JwtUtil;
import lombok.*;

import org.springframework.security.authentication.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository; // ✅ ADD THIS

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        // ✅ FETCH USER FROM DB
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // ✅ GENERATE TOKEN WITH ROLE
        String token = jwtUtil.generateToken(
                user.getUserId(),
                user.getEmail(),
                user.getRoleName().name()
        );

        return new AuthResponse(token);
    }
}

@Getter
@Setter
class AuthRequest {

    private String email;
    private String password;
}

@Getter
@AllArgsConstructor
class AuthResponse {

    private String token;
}