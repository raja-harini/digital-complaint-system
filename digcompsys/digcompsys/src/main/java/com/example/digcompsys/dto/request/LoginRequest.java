package com.example.digcompsys.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
public class LoginRequest {
    @Email
    private String email;
    @NotBlank
    private String password;
//    @NotBlank
//    private String roleName;
}
