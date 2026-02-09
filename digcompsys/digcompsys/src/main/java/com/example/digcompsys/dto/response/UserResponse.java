package com.example.digcompsys.dto.response;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public class UserResponse {
    @NotNull(message = "UserID should not be null")
    private Long userId;
    private String userName;
    private String email;
    @Size(min =8, message = "Password should have minimum 8 characters")
    private String password;
    @NotBlank(message = "Role should be Entered")
    private String roleName;
    private LocalDateTime createdAt;
}
