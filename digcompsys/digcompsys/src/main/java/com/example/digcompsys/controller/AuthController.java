package com.example.digcompsys.controller;

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

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        String token = jwtUtil.generateToken(request.getEmail());

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